package org.team1540.bunnybotTank2023.io.turret;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface TurretIO {
    @AutoLog
    class TurretInputs{
        // fields:
        public double currentPosition = 0;
        public double setPointRotations = 0;
        public double voltage = 0;
        public double current = 0;
        public double velocity = 0;
    }

    void setVoltage(double volts);

    void setTurretPosition(Rotation2d position);

    void updateInputs(TurretInputs inputs);
}

