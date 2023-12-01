package org.team1540.bunnybotTank2023.io.shooter;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

import static org.team1540.bunnybotTank2023.Constants.*;

public class ShooterIOSim implements ShooterIO {
    private final FlywheelSim sim = new FlywheelSim(DCMotor.getFalcon500(2), 1, ShooterConstants.MOI);

    private final PIDController pid = new PIDController(ShooterConstants.KP, ShooterConstants.KI, ShooterConstants.KD);
    private final SimpleMotorFeedforward feedforward =
            new SimpleMotorFeedforward(ShooterConstants.KS, ShooterConstants.KV);

    private double appliedVolts = 0;
    private boolean isClosedLoop = false;
    private double closedLoopSetpoint = 0;

    @Override
    public void updateInputs(ShooterInputs inputs) {
        if (isClosedLoop) {
            appliedVolts =
                    pid.calculate(sim.getAngularVelocityRPM() * 60, closedLoopSetpoint)
                    + feedforward.calculate(closedLoopSetpoint);
            setVoltage(appliedVolts);
        }

        inputs.velocityRPM = sim.getAngularVelocityRPM();
        inputs.appliedVolts = appliedVolts;
        inputs.currentAmps = sim.getCurrentDrawAmps();
    }

    @Override
    public void setVoltage(double volts) {
        isClosedLoop = false;
        appliedVolts = MathUtil.clamp(volts, -12, 12);
        sim.setInputVoltage(appliedVolts);
    }

    @Override
    public void setVelocity(double speedRPM) {
        isClosedLoop = true;
        closedLoopSetpoint = speedRPM / 60;
    }

    @Override
    public void configurePID(double kP, double kI, double kD) {
        pid.setPID(kP, kI, kD);
    }
}
