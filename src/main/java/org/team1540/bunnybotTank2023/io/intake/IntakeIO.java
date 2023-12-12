package org.team1540.bunnybotTank2023.io.intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
    @AutoLog
    class IntakeInputs {
        public double motorVelocity = 0;
        public double motorAppliedVolts = 0;
        public double motorCurrentAmps = 0;
        public boolean folded = false;
    }

    void updateInputs(IntakeInputs inputs);

    void setPercent(double percent);

    void setFold(boolean fold);
}
