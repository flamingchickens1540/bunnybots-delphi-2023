package org.team1540.bunnybotTank2023;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public final class Constants {
    // Allow PID constants to be tuned from a dashboard input or not
    public static final boolean TUNING_MODE = false;

    // Simulation mode, irrelevant for code running on physical robot
    public static final SimulationMode simulationMode = SimulationMode.SIM;

    public enum SimulationMode {

        /** Running on simulated robot */
        SIM,

        /** Replaying from a log file */
        REPLAY
    }

    public static final double DEADZONE_RADIUS = 0.1;

    public static class DrivetrainConstants {
        public static final double GEAR_RATIO = 6.11;
        public static final double WHEEL_DIAMETER = Units.inchesToMeters(4); // The drivetrain wheels have slightly different diameters, so we take the average
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

    public static class IntakeConstants {
        public static final int SOLENOID_1_PORT_NUMBER = 4;
        public static final int SOLENOID_2_PORT_NUMBER = 7;
        public static final int MOTOR_ID = 5;
    }

    public static class IndexerConstants {
        public static final int TOP_MOTOR_1_ID = 6;
        public static final int TOP_MOTOR_2_ID = 7;
        public static final int BOTTOM_MOTOR_ID = 8;
    }

    public static class ShooterConstants {
        public static final int LEADER_ID = 20;
        public static final int FOLLOWER_ID = 21;

        public static final double KP = 0.4; // TODO: 11/30/2023 tuned, doesn't work well for values < 1000 rpm
        public static final double KI = 0.05;
        public static final double KD = 0;
        public static final double KS = 0;
        public static final double KV = 0.113;

        public static final double ERROR_TOLERANCE_RPM = 30;
        public static final double SHOOTER_ACTIVE_RPM = 3218;

        public static final double MOI = 0.0014924622;
    }

    public static class TurretConstants{
        public static final int MOTOR_ID = 10; //TODO: MAKE SURE THIS CAN ID WORKS!!!
        public static final double kP = 3.636;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double gearRatio = 166.66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666667;

        public static final double CRUISE_VELOCITY_RPS = 100;
        public static final double MAX_ACCEL_RPS2 = 600;

        public static final Rotation2d FORWARD_LIMIT_POSITION = Rotation2d.fromDegrees(66.064);
        public static final Rotation2d REVERSE_LIMIT_POSITION = Rotation2d.fromDegrees(-180);
    }
}
