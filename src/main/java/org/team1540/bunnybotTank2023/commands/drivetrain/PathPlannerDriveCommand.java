package org.team1540.bunnybotTank2023.commands.drivetrain;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.server.PathPlannerServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
public class PathPlannerDriveCommand extends SequentialCommandGroup {
    public PathPlannerDriveCommand(Drivetrain drivetrain, PathPlannerTrajectory trajectory, boolean resetToPath) {
        PathPlannerServer.sendActivePath(trajectory.getStates());
        Command ramseteCommand = drivetrain.getPathCommand(trajectory, resetToPath);
        addRequirements(drivetrain);
        addCommands(ramseteCommand);
    }
}
