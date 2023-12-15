package org.team1540.bunnybotTank2023.commands.turret;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import org.team1540.bunnybotTank2023.io.turret.TurretInputsAutoLogged;
import org.team1540.bunnybotTank2023.io.turret.TurretIO;

public class Turret extends SubsystemBase {
    //fields :)
    private final TurretInputsAutoLogged inputs = new TurretInputsAutoLogged();
    private final TurretIO io;

    //constructor
    public Turret(TurretIO io){
        this.io = io;
    }

    //periodic
    public void periodic(){
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Turret", inputs);
    }

    public boolean getForwardLimitSwitch(){
        return inputs.forwardLimitSwitch;
    }

    public boolean getReverseLimitSwitch(){
        return inputs.reverseLimitSwitch;
    }

    public void autoSetPosition(Rotation2d position){
        io.setTurretPosition(position);
    }

    public void setVoltage(double volts){
        io.setVoltage(volts);
    }

}


