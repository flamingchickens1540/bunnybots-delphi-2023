package org.team1540.bunnybotTank2023.io.turret;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ForwardLimitValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.ReverseLimitValue;
import edu.wpi.first.math.geometry.Rotation2d;
import org.team1540.bunnybotTank2023.Constants;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.signals.InvertedValue;

public class TurretIOReal implements TurretIO {

    // fields ^-^
    private final TalonFX motor = new TalonFX(Constants.TurretConstants.MOTOR_ID);
    private final PositionVoltage positionVoltage = new PositionVoltage(0).withSlot(0);
    private Rotation2d setPosition = new Rotation2d();

    // constructor
    TurretIOReal() {
        // robot init, set slot 0 gains
        var slot0Configs = new Slot0Configs();
        slot0Configs.kP = Constants.TurretConstants.kP;
        slot0Configs.kI = Constants.TurretConstants.kI;
        slot0Configs.kD = Constants.TurretConstants.kD;

        motor.getConfigurator().apply(slot0Configs, 0.050);

        positionVoltage.Slot = 0;

        // Current Limits
        CurrentLimitsConfigs turretCurrentLimit = new CurrentLimitsConfigs();
        turretCurrentLimit.SupplyCurrentLimitEnable = true;
        turretCurrentLimit.SupplyCurrentLimit = 20;
        turretCurrentLimit.SupplyCurrentThreshold = 60;
        turretCurrentLimit.SupplyTimeThreshold = 0.1;

        // Motor Inversion
        MotorOutputConfigs turretOutput = new MotorOutputConfigs();
        turretOutput.Inverted = InvertedValue.Clockwise_Positive;

        // Brake Mode
        turretOutput.NeutralMode = NeutralModeValue.Brake;
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
        motor.setControl(positionVoltage.withPosition(position.getRotations()/Constants.TurretConstants.gearRatio));
        setPosition = position;
    }

    @Override
    public void updateInputs(TurretInputs inputs) {
        inputs.motorVelocityRPM = motor.getVelocity().getValue();
        inputs.motorCurrentPositionDegrees = motor.getPosition().getValue() * 360;
        inputs.SetPointDegrees = setPosition.getRotations() * 360;
        inputs.motorCurrentAmps = motor.getSupplyCurrent().getValue();
        inputs.motorVoltage = motor.getSupplyVoltage().getValue();
        inputs.forwardLimitSwitch = (motor.getForwardLimit().getValue() == ForwardLimitValue.ClosedToGround);
        inputs.reverseLimitSwitch = (motor.getReverseLimit().getValue() == ReverseLimitValue.ClosedToGround);
    }
}