package org.team1540.bunnybotTank2023.io.turret;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ForwardLimitValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitValue;
import edu.wpi.first.math.geometry.Rotation2d;
import com.ctre.phoenix6.signals.InvertedValue;
import org.team1540.bunnybotTank2023.utils.Conversions;

import static org.team1540.bunnybotTank2023.Constants.*;

public class TurretIOReal implements TurretIO {

    // fields ^-^
    private final TalonFX motor = new TalonFX(TurretConstants.MOTOR_ID);
    private final PositionVoltage positionVoltage = new PositionVoltage(0).withSlot(0);
    private final MotionMagicVoltage MMVoltage = new MotionMagicVoltage(0).withSlot(0);
    private Rotation2d setPosition = new Rotation2d();

    // constructor
    public TurretIOReal() {
        // robot init, set slot 0 gains
        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.kP = TurretConstants.kP;
        slot0Configs.kI = TurretConstants.kI;
        slot0Configs.kD = TurretConstants.kD;

        // Current Limits
        CurrentLimitsConfigs turretCurrentLimit = new CurrentLimitsConfigs();
        turretCurrentLimit.SupplyCurrentLimitEnable = true;
        turretCurrentLimit.SupplyCurrentLimit = 20;
        turretCurrentLimit.SupplyCurrentThreshold = 60;
        turretCurrentLimit.SupplyTimeThreshold = 0.1;

        // Motor Inversion
        MotorOutputConfigs turretOutput = new MotorOutputConfigs();
        turretOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        // Brake Mode
        turretOutput.NeutralMode = NeutralModeValue.Brake;

        MotionMagicConfigs turretMotionMagic = new MotionMagicConfigs();
        turretMotionMagic.MotionMagicAcceleration = TurretConstants.MAX_ACCEL_RPS2;
        turretMotionMagic.MotionMagicCruiseVelocity = TurretConstants.CRUISE_VELOCITY_RPS;

        TalonFXConfiguration motorConfig = new TalonFXConfiguration();
        motorConfig.Slot0 = slot0Configs;
        motorConfig.CurrentLimits = turretCurrentLimit;
        motorConfig.MotorOutput = turretOutput;
        motorConfig.MotionMagic = turretMotionMagic;

        motor.getConfigurator().apply(motorConfig);
    }

    // sets the voltage
    //      note to Luna: this is just bc the turret is hard to move manually, so this helps!
    @Override
    public void setVoltage(double volts) {
        motor.setVoltage(volts);
    }

    @Override
    public void setTurretPosition(Rotation2d position) {
        // using the gear ratio to convert from motor rotations to mechanism rotations
        motor.setControl(MMVoltage.withPosition(Conversions.Rotation2dToMotorRots(position, TurretConstants.gearRatio)));
        setPosition = position;
    }

    @Override
    public void resetEncoder(Rotation2d position) {
        motor.setRotorPosition(Conversions.Rotation2dToMotorRots(position, TurretConstants.gearRatio));
    }

    @Override
    public void configurePID(double kP, double kI, double kD) {
        Slot0Configs pidConfigs = new Slot0Configs();
        motor.getConfigurator().refresh(pidConfigs);
        pidConfigs.kP = kP;
        pidConfigs.kI = kI;
        pidConfigs.kD = kD;
        motor.getConfigurator().apply(pidConfigs);
    }

    @Override
    public void updateInputs(TurretInputs inputs) {
        inputs.motorVelocityRPS = motor.getVelocity().getValue();
        inputs.turretCurrentPositionDegrees = Conversions.motorRotsToRotation2d(motor.getPosition().getValue(), TurretConstants.gearRatio).getDegrees();
        inputs.turretSetPointDegrees = setPosition.getDegrees();
        inputs.motorCurrentAmps = motor.getSupplyCurrent().getValue();
        inputs.motorVoltage = motor.getSupplyVoltage().getValue();
        inputs.forwardLimitSwitch = (motor.getForwardLimit().getValue() == ForwardLimitValue.ClosedToGround);
        inputs.reverseLimitSwitch = (motor.getReverseLimit().getValue() == ReverseLimitValue.ClosedToGround);
    }
}