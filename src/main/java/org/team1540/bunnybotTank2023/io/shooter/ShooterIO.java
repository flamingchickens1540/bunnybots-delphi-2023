package org.team1540.bunnybotTank2023.io.shooter;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
    @AutoLog
    class ShooterInputs {
        public double velocityRPM = 0;
        public double appliedVolts = 0;
        public double currentAmps = 0;
    }

    void setVelocity(double speedRPM);

    void setVoltage(double volts);

    void updateInputs(ShooterInputs inputs);

    void configurePID(double kP, double kI, double kD);
}
