package org.team1540.bunnybotTank2023.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexerIdleCommand extends CommandBase {
    private final Indexer indexer;

    public IndexerIdleCommand(Indexer indexer) {
        this.indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize() {
        indexer.setBottomSpeed(0.25);
        indexer.setTopSpeed(-0.2);
    }
}

