package org.team1540.bunnybotTank2023.io.drivetrain;

import org.littletonrobotics.junction.AutoLog;

public interface DrivetrainIO {
    @AutoLog
    class DrivetrainInputs{
        public double leftPositionRots = 0;
        public double leftVelocityRPM = 0;
        public double leftAppliedVolts = 0;
        public double[] leftCurrentAmps = new double[2];

        public double rightPositionRots = 0;
        public double rightVelocityRPM = 0;
        public double rightAppliedVolts = 0;
        public double[] rightCurrentAmps = new double[2];

        public double gyroYawRad = 0;
    }

    void setVoltage(double leftVolts, double rightVolts);

    void updateInputs(DrivetrainInputs inputs);
}
