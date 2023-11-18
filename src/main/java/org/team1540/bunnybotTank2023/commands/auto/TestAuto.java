package org.team1540.bunnybotTank2023.commands.auto;

import com.pathplanner.lib.PathConstraints;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.utils.AutoCommand;

public class TestAuto extends AutoCommand {
    public TestAuto(Drivetrain drivetrain) {
        addCommands(
            getPathPlannerDriveCommand(drivetrain,"Forward1Meter", new PathConstraints(4, 2), true)
        );
    }
}
