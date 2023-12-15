package org.team1540.bunnybotTank2023.io.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.team1540.bunnybotTank2023.utils.vision.LEDState;

public class Limelight implements TargetVisionIO {
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    @Override
    public void updateInputs(VisionInputs inputs) {
    }

    @Override
    public void setLEDState(LEDState ledState) {

    }
}
