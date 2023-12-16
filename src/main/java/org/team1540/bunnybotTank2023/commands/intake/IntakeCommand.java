package org.team1540.bunnybotTank2023.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase {
    private final Intake intake;

    public IntakeCommand(Intake intake) {
        this.intake = intake;
    }

    @Override
    public void initialize() {
        intake.setFold(false);
        intake.setMotorSpeed(1);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setFold(true);
        intake.setMotorSpeed(0.5);
    }
}
