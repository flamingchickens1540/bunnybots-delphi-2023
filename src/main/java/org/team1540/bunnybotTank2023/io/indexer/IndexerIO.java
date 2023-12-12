package org.team1540.bunnybotTank2023.io.indexer;

import org.littletonrobotics.junction.AutoLog;

public interface IndexerIO {
    @AutoLog
    class IndexerInputs {
        public double topMotor1Velocity = 0;
        public double topMotor1AppliedVolts = 0;
        public double topMotor1CurrentAmps = 0;
        public double topMotor2Velocity = 0;
        public double topMotor2AppliedVolts = 0;
        public double topMotor2CurrentAmps = 0;
        public double bottomMotorVelocity = 0;
        public double bottomMotorAppliedVolts = 0;
        public double bottomMotorCurrentAmps = 0;
    }
    void updateInputs(IndexerInputs inputs);
    void setTopPercent(double speed);
    void setBottomPercent(double speed);
}
