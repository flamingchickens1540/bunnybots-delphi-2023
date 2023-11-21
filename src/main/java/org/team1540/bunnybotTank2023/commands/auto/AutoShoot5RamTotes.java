package org.team1540.bunnybotTank2023.commands.auto;

import com.pathplanner.lib.PathConstraints;
import edu.wpi.first.wpilibj2.command.Command;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.utils.AutoCommand;

import java.util.List;

public class AutoShoot5RamTotes extends AutoCommand {
    public AutoShoot5RamTotes(Drivetrain drivetrain) {
        List<Command> pathCommands = getPathPlannerDriveCommandGroup(
                drivetrain,
                "Shoot5RamTotes",
                new PathConstraints[] {new PathConstraints(5, 2)},
                true
        );
        System.out.println(pathCommands);
        addCommands(
                pathCommands.get(0),
                pathCommands.get(1),
                pathCommands.get(2)
        );
    }
}
