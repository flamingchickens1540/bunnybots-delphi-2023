package org.team1540.bunnybotTank2023.commands.shooter;

import edu.wpi.first.wpilibj2.command.*;
import org.team1540.bunnybotTank2023.commands.indexer.Indexer;

public class ShootSequenceCommand extends SequentialCommandGroup {
    public ShootSequenceCommand(Shooter shooter, Indexer indexer, double shooterVelocityRPM) {
        addCommands(
                new InstantCommand(() -> {
                    shooter.setVelocity(shooterVelocityRPM);
                    indexer.setBottomSpeed(0.5);
                }),
                new WaitUntilCommand(shooter::isAtSetpoint),
                new InstantCommand(() -> indexer.setTopSpeed(1)),
                new WaitCommand(0.1),
                new InstantCommand(() -> indexer.setTopSpeed(-0.2)),
                new WaitCommand(0.5)
        );
        addRequirements(shooter, indexer);
    }
}
