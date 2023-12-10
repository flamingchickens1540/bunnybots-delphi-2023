package org.team1540.bunnybotTank2023.io.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import org.team1540.bunnybotTank2023.Constants;

import javax.naming.ldap.Control;

public class IntakeIOReal implements IntakeIO {
    private final Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.IntakeConstants.SOLENOID_PORT_NUMBER);
    private final VictorSPX motor = new VictorSPX(Constants.IntakeConstants.MOTOR_ID);
    public IntakeIOReal() {
        solenoid.set(false);
        motor.setNeutralMode(NeutralMode.Brake);
        motor.setInverted(false);
    }
    @Override
    public void setFold(boolean fold) {
        solenoid.set(fold);
    }

    @Override
    public void updateInputs(IntakeInputs inputs) {
        inputs.folded = solenoid.get();
        inputs.motorAppliedVolts = motor.getMotorOutputVoltage();
        inputs.motorCurrentAmps = motor.getMotorOutputPercent();
        inputs.motorVelocity = motor.getActiveTrajectoryVelocity();
    }

    @Override
    public void setPercent(double percent) {
        motor.set(ControlMode.PercentOutput, percent);
    }
}
