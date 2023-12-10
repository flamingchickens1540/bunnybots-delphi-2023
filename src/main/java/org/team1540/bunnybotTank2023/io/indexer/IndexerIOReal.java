package org.team1540.bunnybotTank2023.io.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import org.team1540.bunnybotTank2023.Constants;

public class IndexerIOReal implements IndexerIO {
    private final CANSparkMax topMotor1 = new CANSparkMax(Constants.IndexerConstants.TOP_MOTOR_1_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax topMotor2 = new CANSparkMax(Constants.IndexerConstants.TOP_MOTOR_2_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax bottomMotor = new CANSparkMax(Constants.IndexerConstants.BOTTOM_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    public IndexerIOReal() {
        topMotor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        topMotor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
        bottomMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        topMotor1.setSecondaryCurrentLimit(30);
        topMotor2.setSecondaryCurrentLimit(30);
        bottomMotor.setSecondaryCurrentLimit(30);

        topMotor2.follow(topMotor1);

        topMotor1.setInverted(false);
        topMotor2.setInverted(false);
        bottomMotor.setInverted(false);

        // which of these motors needs to be inverted?
    }

    @Override
    public void updateInputs(IndexerInputs inputs) {
        inputs.topMotor1AppliedVolts = topMotor1.getAppliedOutput();
        inputs.topMotor1Velocity = topMotor1.get();
        inputs.topMotor1CurrentAmps = topMotor1.getOutputCurrent();

        inputs.topMotor2AppliedVolts = topMotor2.getAppliedOutput();
        inputs.topMotor2Velocity = topMotor2.get();
        inputs.topMotor2CurrentAmps = topMotor2.getOutputCurrent();

        inputs.bottomMotorAppliedVolts = bottomMotor.getAppliedOutput();
        inputs.bottomMotorVelocity = bottomMotor.get();
        inputs.bottomMotorCurrentAmps = bottomMotor.getOutputCurrent();
    }

    @Override
    public void setTopPercent(double speed) {
        topMotor1.set(speed);
    }

    @Override
    public void setBottomPercent(double speed) {
        bottomMotor.set(speed);
    }
}
