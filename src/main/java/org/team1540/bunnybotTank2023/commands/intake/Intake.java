package org.team1540.bunnybotTank2023.commands.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.bunnybotTank2023.Constants;

public class Intake extends SubsystemBase {

    // initializing solenoids and motor
    private final Solenoid solenoid1 = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.IntakeConstants.SOLENOID_1_PORT_NUMBER);
    private final VictorSPX motor = new VictorSPX(Constants.IntakeConstants.MOTOR_ID); // motor type may change

    public Intake() {
        solenoid1.set(false);
    }

    public void changeOutput() {
        solenoid1.set(!solenoid1.get());
    }

    public void setMotorSpeed(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }

}
