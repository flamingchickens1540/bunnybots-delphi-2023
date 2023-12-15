package org.team1540.bunnybotTank2023.io.vision;

import org.littletonrobotics.junction.AutoLog;

import java.util.ArrayList;

public interface LimelightIO {
    public double[][] allTargets = {};
    public ArrayList<double[]> validTargets = new ArrayList<>();

    @AutoLog
    public static class LimelightIOInputs{
        public boolean validTarget = false;

        public double captureLatencyMs = 0.0;
        public double pipelineLatencyMs = 0.0;
        public double parseLatencyMs = 0.0;
        public double totalLatencyMs = 0.0;
        public double timestampMs = 0.0;
        public double pipeline = 0;
    }

    default void updateInputs(LimelightIOInputs inputs) {}

    default double[][] getAllTargets() {
        return new double[0][0];
    }
    default ArrayList<double[]> getValidTargets() {
        return new ArrayList<>();
    }

}
