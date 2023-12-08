package org.team1540.bunnybotTank2023.io.indexer;

import org.littletonrobotics.junction.AutoLog;

public interface IndexerIO {
    @AutoLog
    class IndexerInputs {
        public double topMotorVelocity = 0;
        public double topMotorAppliedVolts = 0;
        public double topMotorCurrentAmps = 0;
        public double bottomMotorVelocity = 0;
        public double bottomMotorAppliedVolts = 0;
        public double bottomMotorCurrentAmps = 0;
    }
    void updateInputs(IndexerInputs inputs);
    void setTopPercent(double speed);
    void setBottomPercent(double speed);
}
