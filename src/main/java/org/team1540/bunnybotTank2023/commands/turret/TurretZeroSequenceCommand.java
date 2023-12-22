package org.team1540.bunnybotTank2023.commands.turret;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

import static org.team1540.bunnybotTank2023.Constants.*;

public class TurretZeroSequenceCommand extends SequentialCommandGroup {
    public TurretZeroSequenceCommand(Turret turret) {
        addCommands(
                new InstantCommand(() -> turret.setVoltage(2)),
                new WaitUntilCommand(turret::getForwardLimitSwitch),
                new InstantCommand(() -> turret.resetToEncoder(TurretConstants.FORWARD_LIMIT_POSITION)),
                new TurretSetpointCommand(turret, Rotation2d.fromDegrees(0)).asProxy()
        );
        addRequirements(turret);
    }
}
