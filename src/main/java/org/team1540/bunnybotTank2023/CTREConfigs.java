package org.team1540.bunnybotTank2023;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class CTREConfigs {
    public static final TalonFXConfiguration shooterMotorConfig = new TalonFXConfiguration();

    static {
        /* Shooter motor config */
        MotorOutputConfigs shooterOutput = new MotorOutputConfigs();
        shooterOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        shooterOutput.NeutralMode = NeutralModeValue.Coast;

        CurrentLimitsConfigs shooterCurrentLimit = new CurrentLimitsConfigs();
        shooterCurrentLimit.SupplyCurrentLimitEnable = true;
        shooterCurrentLimit.SupplyCurrentLimit = 40;
        shooterCurrentLimit.SupplyCurrentThreshold = 60;
        shooterCurrentLimit.SupplyTimeThreshold = 0.1;

        Slot0Configs shooterPID = new Slot0Configs();
        shooterPID.kP = Constants.ShooterConstants.KP;
        shooterPID.kI = Constants.ShooterConstants.KI;
        shooterPID.kD = Constants.ShooterConstants.KD;
        shooterPID.kS = Constants.ShooterConstants.KS;
        shooterPID.kV = Constants.ShooterConstants.KV;

        shooterMotorConfig.MotorOutput = shooterOutput;
        shooterMotorConfig.CurrentLimits = shooterCurrentLimit;
        shooterMotorConfig.Slot0 = shooterPID;
    }
}
