package org.team1540.bunnybotTank2023.io.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.SPI;

import static org.team1540.bunnybotTank2023.Constants.*;

public class DrivetrainIOReal implements DrivetrainIO {
    private final CANSparkMax frontLeft = new CANSparkMax(DrivetrainConstants.FRONT_LEFT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax frontRight = new CANSparkMax(DrivetrainConstants.FRONT_RIGHT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax backLeft = new CANSparkMax(DrivetrainConstants.BACK_LEFT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax backRight = new CANSparkMax(DrivetrainConstants.BACK_RIGHT_ID, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final RelativeEncoder leftEncoder = frontLeft.getEncoder();
    private final RelativeEncoder rightEncoder = frontRight.getEncoder();

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

    public DrivetrainIOReal() {
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

        frontLeft.enableVoltageCompensation(12);
        frontRight.enableVoltageCompensation(12);
    }

    @Override
    public void setVoltage(double leftVolts, double rightVolts) {
        frontLeft.setVoltage(leftVolts);
        frontRight.setVoltage(rightVolts);
    }

    @Override
    public void updateInputs(DrivetrainInputs inputs) {
        inputs.leftPositionRots = leftEncoder.getPosition();
        inputs.leftVelocityRPM = leftEncoder.getVelocity();
        inputs.leftAppliedVolts = frontLeft.getAppliedOutput() * frontLeft.getBusVoltage();
        inputs.leftCurrentAmps[0] = frontLeft.getOutputCurrent();
        inputs.leftCurrentAmps[1] = backLeft.getOutputCurrent();

        inputs.rightPositionRots = rightEncoder.getPosition();
        inputs.rightVelocityRPM = leftEncoder.getVelocity();
        inputs.rightAppliedVolts = frontRight.getAppliedOutput() * frontRight.getBusVoltage();
        inputs.rightCurrentAmps[0] = frontRight.getOutputCurrent();
        inputs.rightCurrentAmps[1] = backRight.getOutputCurrent();

        inputs.gyroYawRad = gyro.getRotation2d().getRadians();
    }
}
