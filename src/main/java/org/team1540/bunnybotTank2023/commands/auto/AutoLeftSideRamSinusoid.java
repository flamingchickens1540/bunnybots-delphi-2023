package org.team1540.bunnybotTank2023.commands.auto;

import com.pathplanner.lib.PathConstraints;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import org.team1540.bunnybotTank2023.commands.auto.utilSequences.AutoAimShoot5;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.commands.indexer.IndexerIdleCommand;
import org.team1540.bunnybotTank2023.commands.turret.Turret;
import org.team1540.bunnybotTank2023.commands.indexer.Indexer;
import org.team1540.bunnybotTank2023.commands.shooter.Shooter;

import org.team1540.bunnybotTank2023.commands.turret.TurretZeroSequenceCommand;
import org.team1540.bunnybotTank2023.io.vision.Limelight;
import org.team1540.bunnybotTank2023.utils.AutoCommand;

import java.util.List;

public class AutoLeftSideRamSinusoid extends AutoCommand {
    public AutoLeftSideRamSinusoid(Drivetrain drivetrain, Indexer indexer, Turret turret, Shooter shooter, Limelight limelight) {
        List<Command> pathCommands = getPathPlannerDriveCommandGroup(
                drivetrain,
                "LeftSideRamSinusoid",
                new PathConstraints[]{new PathConstraints(4, 2), new PathConstraints(3, 2)},
                true
        );

        addCommands(
                Commands.parallel(
                        pathCommands.get(0),
                        new TurretZeroSequenceCommand(turret).asProxy()
                ),
                pathCommands.get(1)
        );
    }
}
