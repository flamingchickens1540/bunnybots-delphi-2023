package org.team1540.bunnybotTank2023.commands.drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import org.team1540.bunnybotTank2023.Constants;

public class TankdriveCommand extends CommandBase {
    private final Drivetrain drivetrain;

    private final CommandXboxController xBoxController;

    private final SlewRateLimiter leftRateLimiter = new SlewRateLimiter(4);
    private final SlewRateLimiter rightRateLimiter = new SlewRateLimiter(4);

    public TankdriveCommand(Drivetrain drivetrain, CommandXboxController xBoxController) {
        this.drivetrain = drivetrain;
        this.xBoxController = xBoxController;
        addRequirements(drivetrain);
    }

    public void execute() {
        double leftInput = -xBoxController.getLeftY();
        double rightInput = -xBoxController.getRightY();
        leftInput = leftRateLimiter.calculate(MathUtil.applyDeadband(leftInput, Constants.DEADZONE_RADIUS));
        rightInput = rightRateLimiter.calculate(MathUtil.applyDeadband(rightInput, Constants.DEADZONE_RADIUS));

        drivetrain.drive(leftInput, rightInput);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }

    @Override
    public boolean isFinished() {
      return false;
    }

}
