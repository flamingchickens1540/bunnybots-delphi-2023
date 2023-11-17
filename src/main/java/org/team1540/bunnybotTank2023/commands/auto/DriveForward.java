package org.team1540.bunnybotTank2023.commands.auto;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.commands.drivetrain.PathPlannerDriveCommand;
import org.team1540.bunnybotTank2023.utils.AutoCommand;

public class DriveForward extends AutoCommand {
    public DriveForward(Drivetrain drivetrain, boolean resetToPath) {
        addCommands(
            getPathPlannerDriveCommand(drivetrain, "OnwardsToVictory", new PathConstraints(5.0, 3.0), false)
        );
    }


}
