package org.team1540.bunnybotTank2023.utils;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.commands.drivetrain.PathPlannerDriveCommand;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AutoCommand extends SequentialCommandGroup {

    public Command getPathPlannerDriveCommand(Drivetrain drivetrain, String pathName, PathConstraints constraints, boolean resetToPath) {
        PathPlannerTrajectory trajectory = PathPlanner.loadPath(pathName, constraints);
        return new PathPlannerDriveCommand(drivetrain, trajectory, resetToPath);
    }

    public List<Command> getPathPlannerDriveCommandGroup(
            Drivetrain drivetrain,
            String pathName,
            PathConstraints[] constraints,
            boolean resetToPath) {
        List<PathPlannerTrajectory> trajectories = PathPlanner.loadPathGroup(pathName, Arrays.asList(constraints));
        LinkedList<Command> commands = new LinkedList<>();
        commands.addLast(new PathPlannerDriveCommand(drivetrain, trajectories.get(0), resetToPath));
        for (int i = 1; i < trajectories.size(); i++) {
            commands.addLast(new PathPlannerDriveCommand(drivetrain, trajectories.get(i), false));
        }
        return commands;
    }
}
