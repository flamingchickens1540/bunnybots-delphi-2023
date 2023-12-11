package org.team1540.bunnybotTank2023.io.shooter;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import org.team1540.bunnybotTank2023.CTREConfigs;

import static org.team1540.bunnybotTank2023.Constants.*;

public class ShooterIOReal implements ShooterIO{
    private final TalonFX leftMotor = new TalonFX(ShooterConstants.LEADER_ID);
    private final TalonFX rightMotor = new TalonFX(ShooterConstants.FOLLOWER_ID);

    private final StatusSignal<Double> velocity = leftMotor.getVelocity();
    private final StatusSignal<Double> busVoltage = leftMotor.getSupplyVoltage();
    private final StatusSignal<Double> dutyCycle = leftMotor.getDutyCycle();
    private final StatusSignal<Double> current = leftMotor.getSupplyCurrent();

    private final VelocityVoltage velocityControlRequest = new VelocityVoltage(0).withSlot(0);
    private final VoltageOut voltageControlRequest = new VoltageOut(0);

    public ShooterIOReal() {
        leftMotor.getConfigurator().apply(CTREConfigs.shooterMotorConfig);
        rightMotor.getConfigurator().apply(CTREConfigs.shooterMotorConfig);
        rightMotor.setControl(new Follower(ShooterConstants.LEADER_ID, true));
    }

    @Override
    public void updateInputs(ShooterInputs inputs) {
        inputs.velocityRPM = velocity.refresh().getValue() * 60;
        inputs.appliedVolts = busVoltage.refresh().getValue() * dutyCycle.refresh().getValue();
        inputs.currentAmps = current.refresh().getValue();
    }

    @Override
    public void setVoltage(double volts) {
        leftMotor.setControl(voltageControlRequest.withOutput(volts));
    }

    @Override
    public void setVelocity(double speedRPM) {
        leftMotor.setControl(velocityControlRequest.withVelocity(speedRPM / 60));
    }

    @Override
    public void configurePID(double kP, double kI, double kD) {
        Slot0Configs pidConfigs = new Slot0Configs();
        leftMotor.getConfigurator().refresh(pidConfigs);
        pidConfigs.kP = kP;
        pidConfigs.kI = kI;
        pidConfigs.kD = kD;
        leftMotor.getConfigurator().apply(pidConfigs);
    }
}
