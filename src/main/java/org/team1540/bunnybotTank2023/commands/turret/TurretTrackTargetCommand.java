package org.team1540.bunnybotTank2023.commands.turret;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.bunnybotTank2023.io.vision.Limelight;

public class TurretTrackTargetCommand extends CommandBase {
    private final Turret turret;
    private final Limelight limelight;

    public TurretTrackTargetCommand(Turret turret, Limelight limelight) {
        this.turret = turret;
        this.limelight = limelight;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        Rotation2d turretSetpoint = Rotation2d.fromDegrees(limelight.getTx()).plus(turret.getPosition());
        if (limelight.getTv()) turret.autoSetPosition(turretSetpoint);
    }

    @Override
    public void execute() {
        Rotation2d turretSetpoint = Rotation2d.fromDegrees(limelight.getTx()).plus(turret.getPosition());
        if (limelight.getTv() && turret.isAtSetpoint()) turret.autoSetPosition(turretSetpoint);
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }
}
