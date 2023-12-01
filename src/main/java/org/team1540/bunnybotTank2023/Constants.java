package org.team1540.bunnybotTank2023;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
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

    public static class ShooterConstants {
        public static final int LEADER_ID = 20;
        public static final int FOLLOWER_ID = 21;

        public static final double KP = 0.7; // TODO: 11/30/2023 tuned, doesn't work well for values < 1500 rpm
        public static final double KI = 0.0;
        public static final double KD = 0;
        public static final double KS = 0.19;
        public static final double KV = 0.1149;

        public static final double ERROR_TOLERANCE_RPM = 30; // TODO: 11/28/2023 tune

        public static final double MOI = 0.0014924622;

        public static final TalonFXConfiguration MOTOR_CONFIG = new TalonFXConfiguration();
        static {
            MotorOutputConfigs outputConfigs = new MotorOutputConfigs();
            outputConfigs.Inverted = InvertedValue.CounterClockwise_Positive;
            outputConfigs.NeutralMode = NeutralModeValue.Coast;

            CurrentLimitsConfigs currentLimitConfigs = new CurrentLimitsConfigs();
            currentLimitConfigs.SupplyCurrentLimitEnable = true;
            currentLimitConfigs.SupplyCurrentLimit = 40;
            currentLimitConfigs.SupplyCurrentThreshold = 60;
            currentLimitConfigs.SupplyTimeThreshold = 0.1;

            Slot0Configs pidConfigs = new Slot0Configs();
            pidConfigs.kP = KP;
            pidConfigs.kI = KI;
            pidConfigs.kD = KD;
            pidConfigs.kS = KS;
            pidConfigs.kV = KV;

            MOTOR_CONFIG.MotorOutput = outputConfigs;
            MOTOR_CONFIG.CurrentLimits = currentLimitConfigs;
            MOTOR_CONFIG.Slot0 = pidConfigs;
        }
    }
}
