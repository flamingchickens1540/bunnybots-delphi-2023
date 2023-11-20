package org.team1540.bunnybotTank2023.io.drivetrain;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import org.team1540.bunnybotTank2023.utils.Conversions;

import static org.team1540.bunnybotTank2023.Constants.*;

public class DrivetrainIOSim implements DrivetrainIO {
    private final DifferentialDrivetrainSim sim = new DifferentialDrivetrainSim(
            DCMotor.getNEO(2),
            DrivetrainConstants.GEAR_RATIO,
            DrivetrainConstants.MOI,
            DrivetrainConstants.MASS,
            DrivetrainConstants.WHEEL_DIAMETER / 2,
            DrivetrainConstants.TRACK_WIDTH,
            null
    );

    private double leftAppliedVolts = 0;
    private double rightAppliedVolts = 0;

    @Override
    public void setVoltage(double leftVolts, double rightVolts) {
        leftAppliedVolts = MathUtil.clamp(leftVolts, -12, 12);
        rightAppliedVolts = MathUtil.clamp(rightVolts, -12, 12);
        sim.setInputs(leftAppliedVolts, rightAppliedVolts);
    }

    @Override
    public void updateInputs(DrivetrainInputs inputs) {
        sim.update(0.02);

        inputs.leftPositionRots = Conversions.metersToMotorRots(sim.getLeftPositionMeters(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO);
        inputs.leftVelocityRPM = Conversions.MPStoRPM(sim.getLeftVelocityMetersPerSecond(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO);
        inputs.leftAppliedVolts = leftAppliedVolts;
        inputs.leftCurrentAmps[0] = sim.getLeftCurrentDrawAmps();

        inputs.rightPositionRots = Conversions.metersToMotorRots(sim.getRightPositionMeters(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO);
        inputs.rightVelocityRPM = Conversions.MPStoRPM(sim.getRightVelocityMetersPerSecond(), DrivetrainConstants.WHEEL_DIAMETER, DrivetrainConstants.GEAR_RATIO);
        inputs.rightAppliedVolts = rightAppliedVolts;
        inputs.rightCurrentAmps[0] = sim.getRightCurrentDrawAmps();

        inputs.gyroYawRad = sim.getHeading().getRadians();
    }
}
