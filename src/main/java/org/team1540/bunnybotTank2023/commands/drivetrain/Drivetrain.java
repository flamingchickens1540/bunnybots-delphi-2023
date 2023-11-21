package org.team1540.bunnybotTank2023.commands.drivetrain;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPRamseteCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.*;

import org.littletonrobotics.junction.Logger;
import org.team1540.bunnybotTank2023.RamseteConfig;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainIO;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainInputsAutoLogged;
import org.team1540.bunnybotTank2023.utils.Conversions;
import org.team1540.bunnybotTank2023.utils.DifferentialDriveWheelPositions;

import static org.team1540.bunnybotTank2023.Constants.*;


public class Drivetrain extends SubsystemBase{
    private final DrivetrainIO io;
    private final DrivetrainInputsAutoLogged inputs = new DrivetrainInputsAutoLogged();
    private final DifferentialDrivePoseEstimator poseEstimator;

    public Drivetrain(DrivetrainIO io) {
        this.io = io;
        PPRamseteCommand.setLoggingCallbacks(
                trajectory -> Logger.getInstance().recordOutput("odometry/activeTrajectory", trajectory),
                targetPose -> Logger.getInstance().recordOutput("odometry/targetPose", targetPose),
                null,
                null
        );
        poseEstimator = new DifferentialDrivePoseEstimator(
                RamseteConfig.driveKinematics,
                getYaw(),
                getWheelPositions().leftDistanceMeters,
                getWheelPositions().rightDistanceMeters,
                new Pose2d()
        );
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Drivetrain", inputs);

        DifferentialDriveWheelPositions wheelPositions = getWheelPositions();
        poseEstimator.update(getYaw(), wheelPositions.leftDistanceMeters, wheelPositions.rightDistanceMeters);
        Logger.getInstance().recordOutput("odometry/robotPose", getPose());
    }

    //Speed between -1 and 1
    public void drive(double leftInput, double rightInput) {
        io.setVoltage(
                leftInput * 12.0,
                rightInput * 12.0
        );
    }

    public void setVoltage(double leftVolts, double rightVolts) {
        io.setVoltage(leftVolts, rightVolts);
    }

    public void stop() {
        drive(0,0);
    }

    public Command getPathCommand(PathPlannerTrajectory trajectory, boolean resetToPath) {
        return Commands.sequence(
                new InstantCommand(() -> {
                    if (resetToPath) resetOdometry(trajectory.getInitialPose());
                }),
                new PPRamseteCommand(
                        trajectory,
                        this::getPose,
                        RamseteConfig.ramseteController,
                        RamseteConfig.feedforward,
                        RamseteConfig.driveKinematics,
                        this::getWheelSpeeds,
                        new PIDController(DrivetrainConstants.VELOCITY_KP, DrivetrainConstants.VELOCITY_KI, DrivetrainConstants.VELOCITY_KD),
                        new PIDController(DrivetrainConstants.VELOCITY_KP, DrivetrainConstants.VELOCITY_KI, DrivetrainConstants.VELOCITY_KD),
                        this::setVoltage,
                        this
                )
        );
    }

    public Rotation2d getYaw() {
        return Rotation2d.fromRadians(inputs.gyroYawRad);
    }

    public DifferentialDriveWheelPositions getWheelPositions() {
        return new DifferentialDriveWheelPositions(
                Conversions.motorRotsToMeters(inputs.leftPositionRots, DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO),
                Conversions.motorRotsToMeters(inputs.rightPositionRots, DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO)
        );
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                Conversions.RPMtoMPS(inputs.leftVelocityRPM, DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO),
                Conversions.RPMtoMPS(inputs.rightVelocityRPM, DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO)
        );
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }

    public void resetOdometry(Pose2d pose) {
        DifferentialDriveWheelPositions wheelPositions = getWheelPositions();
        poseEstimator.resetPosition(getYaw(), wheelPositions.leftDistanceMeters, wheelPositions.rightDistanceMeters, pose);
    }
}
