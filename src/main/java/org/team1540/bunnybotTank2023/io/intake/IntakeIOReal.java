package org.team1540.bunnybotTank2023.io.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import org.team1540.bunnybotTank2023.Constants;

public class IntakeIOReal implements IntakeIO {
    private final Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.IntakeConstants.SOLENOID_1_PORT_NUMBER);
    private final VictorSPX motor = new VictorSPX(Constants.IntakeConstants.MOTOR_ID);
    public IntakeIOReal() {
        solenoid.set(false);
    }
    @Override
    public void setFold(boolean fold) {
        solenoid.set(fold);
    }
    @Override
    public void setPercent(double percent) {
        motor.set(ControlMode.PercentOutput, percent);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }
}
