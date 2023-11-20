package org.team1540.bunnybotTank2023;

import edu.wpi.first.math.util.Units;

public final class Constants {
    public static final Mode currentMode = Mode.SIM;

    public enum Mode {
        /** Running on real robot */
        REAL,

        /** Running on simulated robot */
        SIM,

        /** Replaying from a log file */
        REPLAY
    }

    public static final double DEADZONE_RADIUS = 0.1;

    public static class DrivetrainConstants {
        public static final double GEAR_RATIO = 6.11;
        public static final double WHEEL_DIAMETER = Units.inchesToMeters(3.973); // The drivetrain wheels have slightly different diameters, so we take the average
        public static final double TRACK_WIDTH = Units.inchesToMeters(22.75);

        public static final double MASS = Units.lbsToKilograms(118); // TODO: 11/18/2023 omg its tem 118 teh robnots
        public static final double MOI = 2.54; // TODO: 11/18/2023 omg its frc tem 254 teh chezy pofs

        // PID constants
        public static final double VELOCITY_KP = 3.2925;
        public static final double VELOCITY_KI = 0;
        public static final double VELOCITY_KD = 0;

        // FF constants
        public static final double KS = 0.650;
        public static final double KV = 2.81;
        public static final double KA = 0.224;

        public static final int DRIVETRAIN_MOTOR_CURRENT_LIMIT = 40;

        public static final int FRONT_LEFT_ID = 1;
        public static final int FRONT_RIGHT_ID = 2;
        public static final int BACK_LEFT_ID = 3;
        public static final int BACK_RIGHT_ID = 4;
    }
}
