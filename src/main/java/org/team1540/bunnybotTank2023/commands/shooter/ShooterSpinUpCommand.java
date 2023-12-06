package org.team1540.bunnybotTank2023.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterSpinUpCommand extends CommandBase {
    private final Shooter shooter;
    private final double setpoint;

    public ShooterSpinUpCommand(Shooter shooter, double rpm) {
        this.shooter = shooter;
        this.setpoint = rpm;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setVelocity(setpoint);
    }

    @Override
    public boolean isFinished() {
        return shooter.isAtSetpoint();
    }
}
