package org.team1540.bunnybotTank2023.commands.drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import org.team1540.bunnybotTank2023.Constants;

public class TankdriveCommand extends CommandBase {
    private final Drivetrain drivetrain;

    private final CommandXboxController xBox;

    public TankdriveCommand(Drivetrain subsystem, CommandXboxController xBox) {
        drivetrain = subsystem;
        this.xBox = xBox;
        addRequirements(subsystem);
    }

    public void execute() {
        double leftInput = xBox.getLeftY();
        double rightInput = xBox.getRightY();
        MathUtil.applyDeadband(leftInput, Constants.DEADZONE_RADIUS);
        MathUtil.applyDeadband(rightInput, Constants.DEADZONE_RADIUS);

        drivetrain.drive(leftInput, true);
        drivetrain.drive(rightInput, false);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
      return false;
    }

}
