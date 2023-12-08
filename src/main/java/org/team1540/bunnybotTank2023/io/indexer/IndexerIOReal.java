package org.team1540.bunnybotTank2023.io.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import org.team1540.bunnybotTank2023.Constants;

public class IndexerIOReal implements IndexerIO {
    private final CANSparkMax topMotor = new CANSparkMax(Constants.IndexerConstants.TOP_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax bottomMotor = new CANSparkMax(Constants.IndexerConstants.BOTTOM_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    public IndexerIOReal() {
        topMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        bottomMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    @Override
    public void updateInputs(IndexerInputs inputs) {
        inputs.topMotorAppliedVolts = topMotor.getAppliedOutput();
        inputs.topMotorVelocity = topMotor.get();
        inputs.topMotorCurrentAmps = topMotor.getOutputCurrent();

        inputs.bottomMotorAppliedVolts = bottomMotor.getAppliedOutput();
        inputs.bottomMotorVelocity = bottomMotor.get();
        inputs.bottomMotorCurrentAmps = bottomMotor.getOutputCurrent();
    }

    @Override
    public void setTopPercent(double speed) {
        topMotor.set(speed);
    }

    @Override
    public void setBottomPercent(double speed) {
        bottomMotor.set(speed);
    }
}
