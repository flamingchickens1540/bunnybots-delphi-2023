package org.team1540.bunnybotTank2023.commands.auto.utilSequences;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.bunnybotTank2023.commands.indexer.Indexer;
import org.team1540.bunnybotTank2023.commands.shooter.ShootSequenceCommand;
import org.team1540.bunnybotTank2023.commands.shooter.Shooter;
import org.team1540.bunnybotTank2023.commands.turret.Turret;
import org.team1540.bunnybotTank2023.commands.turret.TurretTrackTargetCommand;
import org.team1540.bunnybotTank2023.io.vision.Limelight;

import static org.team1540.bunnybotTank2023.Constants.*;

public class AutoAimShoot5 extends SequentialCommandGroup {
    public AutoAimShoot5(Turret turret, Shooter shooter, Indexer indexer, Limelight limelight) {
        addCommands(
                Commands.deadline(
                        // Trust me, not scuffed at all ;D
                        new SequentialCommandGroup(
                                new ShootSequenceCommand(shooter, indexer, ShooterConstants.SHOOTER_ACTIVE_RPM),
                                new ShootSequenceCommand(shooter, indexer, ShooterConstants.SHOOTER_ACTIVE_RPM),
                                new ShootSequenceCommand(shooter, indexer, ShooterConstants.SHOOTER_ACTIVE_RPM),
                                new ShootSequenceCommand(shooter, indexer, ShooterConstants.SHOOTER_ACTIVE_RPM),
                                new ShootSequenceCommand(shooter, indexer, ShooterConstants.SHOOTER_ACTIVE_RPM)
                        ),
                        new TurretTrackTargetCommand(turret, limelight)
                ),
                new InstantCommand(shooter::stop)
        );
    }
}
