package org.team1540.bunnybotTank2023.commands.indexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import org.team1540.bunnybotTank2023.Constants;
import org.team1540.bunnybotTank2023.io.indexer.IndexerIO;
import org.team1540.bunnybotTank2023.io.indexer.IndexerInputsAutoLogged;

public class Indexer extends SubsystemBase {
    private final IndexerIO io;
    private final IndexerInputsAutoLogged inputs = new IndexerInputsAutoLogged();
    public Indexer(IndexerIO io) {
        this.io = io;
    }
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Intake", inputs);
    }

    public void setTopSpeed(double speed) {
        io.setTopPercent(speed);
    }

    public void setBottomSpeed(double speed) {
        io.setBottomPercent(speed);
    }

    public void stop() {
        setTopSpeed(0);
        setBottomSpeed(0);
    }
}
