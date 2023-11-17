package org.team1540.bunnybotTank2023.utils;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.commands.drivetrain.PathPlannerDriveCommand;

public abstract class AutoCommand extends SequentialCommandGroup {

    public Command getPathPlannerDriveCommand(Drivetrain drivetrain, String pathName, PathConstraints constraints, boolean resetToPath) {
        PathPlannerTrajectory trajectory = PathPlanner.loadPath(pathName, constraints);
        //TODO: Figure out if this is right
        return new PathPlannerDriveCommand(drivetrain, trajectory, resetToPath);
    }


}
