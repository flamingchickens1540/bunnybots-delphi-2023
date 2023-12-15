package org.team1540.bunnybotTank2023.commands.turret;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretSetpointCommand extends CommandBase {
    private final Turret turret;
    private final Rotation2d setpoint;

    public TurretSetpointCommand(Turret turret, Rotation2d setpoint) {
        this.turret = turret;
        this.setpoint = setpoint;
        addRequirements(turret);
    }

    @Override
    public void initialize() {
        turret.autoSetPosition(setpoint);
    }

    @Override
    public boolean isFinished() {
        return turret.isAtSetpoint();
    }
}
