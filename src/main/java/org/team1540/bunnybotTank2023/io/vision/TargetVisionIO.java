package org.team1540.bunnybotTank2023.io.vision;

import org.littletonrobotics.junction.AutoLog;
import org.team1540.bunnybotTank2023.utils.vision.LEDState;

public interface TargetVisionIO {
    @AutoLog
    class VisionInputs {
        public double captureLatencyMs = 0;
        public double pipelineLatencyMS = 0;
        public double parseLatencyMs = 0;
        public double totalLatencyMs = 0;
    }

    void updateInputs(VisionInputs inputs);

    void setLEDState(LEDState ledState);
}
