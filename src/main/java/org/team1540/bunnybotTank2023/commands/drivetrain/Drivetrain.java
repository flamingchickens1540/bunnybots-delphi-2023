package org.team1540.bunnybotTank2023.commands.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import org.team1540.bunnybotTank2023.Constants;



public class Drivetrain extends SubsystemBase{

    //Initialize Motors
    private final CANSparkMax frontLeft = new CANSparkMax(Constants.DrivetrainConstants.DRIVETRAIN_MOTOR_1_ID, MotorType.kBrushless);
    private final CANSparkMax frontRight = new CANSparkMax(Constants.DrivetrainConstants.DRIVETRAIN_MOTOR_2_ID, MotorType.kBrushless);
    private final CANSparkMax backLeft = new CANSparkMax(Constants.DrivetrainConstants.DRIVETRAIN_MOTOR_2_ID, MotorType.kBrushless);
    private final CANSparkMax backRight = new CANSparkMax(Constants.DrivetrainConstants.DRIVETRAIN_MOTOR_2_ID, MotorType.kBrushless);

    public Drivetrain() {
        backRight.follow(frontRight);
        backLeft.follow(frontLeft);
        frontLeft.setIdleMode(CANSparkMax.IdleMode.kBrake);
        frontRight.setIdleMode(CANSparkMax.IdleMode.kBrake);
        backLeft.setIdleMode(CANSparkMax.IdleMode.kBrake);
        backRight.setIdleMode(CANSparkMax.IdleMode.kBrake);

        //Current Limits
        frontLeft.setSmartCurrentLimit(40);
        frontRight.setSmartCurrentLimit(40);
        backLeft.setSmartCurrentLimit(40);
        backRight.setSmartCurrentLimit(40);
    }

    //Speed between -1 and 1
    public void drive(double leftInput, double rightInput) {
        frontLeft.set(leftInput);
        frontRight.set(rightInput);
    }
}
