//TODO: GEAR RATIOS (Zach doesn't want to deal with them right now)
package org.team1540.bunnybotTank2023.io.turret;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface TurretIO {
    @AutoLog
    class TurretInputs{
        // fields:
        public double motorCurrentPositionDegrees = 0;
        public double SetPointDegrees = 0;
        public double motorVoltage = 0;
        public double motorCurrentAmps = 0;
        public double motorVelocityRPM = 0;
        public boolean forwardLimitSwitch = false;
        public boolean reverseLimitSwitch = false;
    }

    void setVoltage(double volts);

    void setTurretPosition(Rotation2d position);

    void updateInputs(TurretInputs inputs);
}

