package org.team1540.bunnybotTank2023.commands.turret;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import org.team1540.bunnybotTank2023.Constants;
import org.team1540.bunnybotTank2023.io.turret.TurretInputsAutoLogged;
import org.team1540.bunnybotTank2023.io.turret.TurretIO;
import org.team1540.bunnybotTank2023.utils.AverageFilter;
import org.team1540.bunnybotTank2023.utils.LoggedTunableNumber;

import static org.team1540.bunnybotTank2023.Constants.*;

public class Turret extends SubsystemBase {
    //fields :)
    private final TurretInputsAutoLogged inputs = new TurretInputsAutoLogged();
    private final TurretIO io;

    private final AverageFilter averageFilter = new AverageFilter(20);

    private final LoggedTunableNumber kP = new LoggedTunableNumber("Turret/kP", TurretConstants.kP);
    private final LoggedTunableNumber kI = new LoggedTunableNumber("Turret/kI", TurretConstants.kI);
    private final LoggedTunableNumber kD = new LoggedTunableNumber("Turret/kD", TurretConstants.kD);

    //constructor
    public Turret(TurretIO io){
        this.io = io;
    }

    //periodic
    public void periodic(){
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Turret", inputs);
        averageFilter.add(inputs.turretCurrentPositionDegrees);

        if (TUNING_MODE && (kP.hasChanged() || kI.hasChanged() || kD.hasChanged())) {
            io.configurePID(kP.get(), kI.get(), kD.get());
        }
    }

    public boolean getForwardLimitSwitch(){
        return inputs.forwardLimitSwitch;
    }

    public boolean getReverseLimitSwitch(){
        return inputs.reverseLimitSwitch;
    }

    public boolean isAtSetpoint() {
        return Math.abs(inputs.turretSetPointDegrees - averageFilter.getAverage()) < 0.5;
    }

    public Rotation2d getPosition() {
        return Rotation2d.fromDegrees(inputs.turretCurrentPositionDegrees);
    }

    public void resetToEncoder(Rotation2d position) {
        io.resetEncoder(position);
    }

    public void autoSetPosition(Rotation2d position){
        averageFilter.clear();
        io.setTurretPosition(position);
    }

    public void setVoltage(double volts){
        io.setVoltage(volts);
    }

    public void stop() {
        setVoltage(0);
    }

}


