package org.team1540.bunnybotTank2023;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;

public final class Constants {
    public static final double DEADZONE_RADIUS = 0.1;

    public static class DrivetrainConstants {
        public static final double GEAR_RATIO = 6.11;
        public static final double WHEEL_DIAMETER = Units.inchesToMeters(3.973); // The drivetrain wheels have slightly different diameters, so we take the average
        public static final double TRACK_WIDTH = Units.inchesToMeters(22.75);
        public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH);

        public static final int DRIVETRAIN_MOTOR_CURRENT_LIMIT = 40;

        public static final int FRONT_LEFT_ID = 1;
        public static final int FRONT_RIGHT_ID = 2;
        public static final int BACK_LEFT_ID = 3;
        public static final int BACK_RIGHT_ID = 4;
    }
}
