package org.team1540.bunnybotTank2023.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexerCommand extends CommandBase {
    private final Indexer indexer;

    public IndexerCommand(Indexer indexer) {
        this.indexer = indexer;
    }

    @Override
    public void initialize() {
        indexer.setBottomSpeed(0.75);
        indexer.setTopSpeed(0.75);
    }

    @Override
    public void end(boolean interrupted) {
        indexer.stop();
    }
}
