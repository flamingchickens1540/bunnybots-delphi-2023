package org.team1540.bunnybotTank2023.commands.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import org.team1540.bunnybotTank2023.io.shooter.ShooterIO;
import org.team1540.bunnybotTank2023.io.shooter.ShooterInputsAutoLogged;
import org.team1540.bunnybotTank2023.utils.AverageFilter;
import org.team1540.bunnybotTank2023.utils.LoggedTunableNumber;

import static org.team1540.bunnybotTank2023.Constants.*;

public class Shooter extends SubsystemBase {

    private final LoggedTunableNumber kP = new LoggedTunableNumber("Shooter/kP", ShooterConstants.KP);
    private final LoggedTunableNumber kI = new LoggedTunableNumber("Shooter/kI", ShooterConstants.KI);
    private final LoggedTunableNumber kD = new LoggedTunableNumber("Shooter/kD", ShooterConstants.KD);

    private final ShooterIO io;
    private final ShooterInputsAutoLogged inputs = new ShooterInputsAutoLogged();

    private final AverageFilter averageFilter = new AverageFilter(20); // TODO: 11/28/2023 tune filter size

    private double setpoint = 0;
    private boolean isClosedLoop = false;

    public Shooter(ShooterIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Shooter", inputs);

        if (TUNING_MODE && (kP.hasChanged() || kI.hasChanged() || kD.hasChanged())) {
            io.configurePID(kP.get(), kI.get(), kD.get());
        }

        averageFilter.add(inputs.velocityRPM);
        Logger.getInstance().recordOutput("Shooter/SetpointRPM", setpoint);
        Logger.getInstance().recordOutput("Shooter/FilteredVelocity", averageFilter.getAverage());
    }

    public void setVelocity(double speedRPM) {
        setpoint = speedRPM;
        isClosedLoop = true;
        averageFilter.clear();
        io.setVelocity(speedRPM);
    }

    public void setVoltage(double volts) {
        isClosedLoop = false;
        io.setVoltage(volts);
    }

    public void stop() {
        setVoltage(0);
    }

    public boolean isAtSetpoint() {
        if (!isClosedLoop) return false;
        return Math.abs(averageFilter.getAverage() - setpoint) < ShooterConstants.ERROR_TOLERANCE_RPM;
    }
}
