package org.team1540.bunnybotTank2023.commands.turret;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.team1540.bunnybotTank2023.Constants;

public class TurretManualCommand extends CommandBase {
    private final Turret turret;
    private final CommandXboxController controller;

    public TurretManualCommand(Turret turret, CommandXboxController controller) {
        this.turret = turret;
        this.controller = controller;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.setVoltage(-MathUtil.applyDeadband(controller.getRightX(), Constants.DEADZONE_RADIUS) * 12.0);
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }
}
