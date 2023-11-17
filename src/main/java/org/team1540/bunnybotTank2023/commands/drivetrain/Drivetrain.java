package org.team1540.bunnybotTank2023.commands.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPRamseteCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.*;

import org.team1540.bunnybotTank2023.RamseteConfig;
import org.team1540.bunnybotTank2023.utils.Conversions;
import org.team1540.bunnybotTank2023.utils.DifferentialDriveWheelPositions;

import static org.team1540.bunnybotTank2023.Constants.*;


public class Drivetrain extends SubsystemBase{

    //Initialize Motors
    private final CANSparkMax frontLeft = new CANSparkMax(DrivetrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax frontRight = new CANSparkMax(DrivetrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax backLeft = new CANSparkMax(DrivetrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax backRight = new CANSparkMax(DrivetrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);

    private final AHRS gyro;
    private final DifferentialDrivePoseEstimator poseEstimator;

    public Drivetrain(AHRS gyro) {
        // Motor configs
        backRight.follow(frontRight);
        backLeft.follow(frontLeft);
        frontLeft.setIdleMode(CANSparkMax.IdleMode.kBrake);
        frontRight.setIdleMode(CANSparkMax.IdleMode.kBrake);
        backLeft.setIdleMode(CANSparkMax.IdleMode.kBrake);
        backRight.setIdleMode(CANSparkMax.IdleMode.kBrake);
        frontRight.setInverted(true);
        backRight.setInverted(true);

        //Current Limits
        frontLeft.setSmartCurrentLimit(DrivetrainConstants.DRIVETRAIN_MOTOR_CURRENT_LIMIT);
        frontRight.setSmartCurrentLimit(DrivetrainConstants.DRIVETRAIN_MOTOR_CURRENT_LIMIT);
        backLeft.setSmartCurrentLimit(DrivetrainConstants.DRIVETRAIN_MOTOR_CURRENT_LIMIT);
        backRight.setSmartCurrentLimit(DrivetrainConstants.DRIVETRAIN_MOTOR_CURRENT_LIMIT);

        this.gyro = gyro;
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
        DifferentialDriveWheelPositions wheelPositions = getWheelPositions();
        poseEstimator.update(getYaw(), wheelPositions.leftDistanceMeters, wheelPositions.rightDistanceMeters);
    }

    //Speed between -1 and 1
    public void drive(double leftInput, double rightInput) {
        frontLeft.set(leftInput);
        frontRight.set(rightInput);
    }

    public void setVoltage(double leftVolts, double rightVolts) {
        frontLeft.setVoltage(leftVolts);
        frontRight.setVoltage(rightVolts);
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
                        RamseteConfig.leftPID,
                        RamseteConfig.rightPID,
                        this::setVoltage,
                        this
                )
        );
    }

    public Rotation2d getYaw() {
        return gyro.getRotation2d();
    }

    public DifferentialDriveWheelPositions getWheelPositions() {
        return new DifferentialDriveWheelPositions(
                Conversions.motorRotsToMeters(frontLeft.getEncoder().getPosition(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO),
                Conversions.motorRotsToMeters(frontRight.getEncoder().getPosition(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO)
        );
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                Conversions.RPMtoMPS(frontLeft.getEncoder().getVelocity(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO),
                Conversions.RPMtoMPS(frontRight.getEncoder().getVelocity(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO)
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
