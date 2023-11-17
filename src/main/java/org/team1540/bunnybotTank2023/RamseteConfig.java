package org.team1540.bunnybotTank2023;

import com.pathplanner.lib.PathConstraints;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

import static org.team1540.bunnybotTank2023.Constants.*;

public class RamseteConfig {
    // Kinematics
    public static final DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(
            DrivetrainConstants.TRACK_WIDTH
    );

    // PID controllers
    public static final PIDController leftPID = new PIDController(
            DrivetrainConstants.VELOCITY_KP,
            DrivetrainConstants.VELOCITY_KI,
            DrivetrainConstants.VELOCITY_KD
    );
    public static final PIDController rightPID = new PIDController(
            DrivetrainConstants.VELOCITY_KP,
            DrivetrainConstants.VELOCITY_KI,
            DrivetrainConstants.VELOCITY_KD
    );

    // Feedforward
    public static final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(
            DrivetrainConstants.KS,
            DrivetrainConstants.KV,
            DrivetrainConstants.KA
    );

    // Ramsete controller
    public static final double RAMSETE_B = 2;
    public static final double RAMSETE_ZETA = 0.7;
    public static final RamseteController ramseteController = new RamseteController(RAMSETE_B, RAMSETE_ZETA);
}
