package org.team1540.bunnybotTank2023.commands.intake;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import org.team1540.bunnybotTank2023.Constants;
import org.team1540.bunnybotTank2023.io.intake.IntakeIO;
import org.team1540.bunnybotTank2023.io.intake.IntakeInputsAutoLogged;

public class Intake extends SubsystemBase {
    private final IntakeIO io;
    private final IntakeInputsAutoLogged inputs = new IntakeInputsAutoLogged();
    public Intake(IntakeIO io) {
        this.io = io;
    }
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Intake", inputs);
    }

    public void changeSolenoidOutput(boolean output) {
        io.setFold(output);
    }

    public void setMotorSpeed(double speed) {
        io.setPercent(speed);
    }

    public void stop() {
        io.setPercent(0);
    }

}
