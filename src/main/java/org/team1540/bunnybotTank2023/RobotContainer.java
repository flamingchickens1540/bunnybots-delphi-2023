// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1540.bunnybotTank2023;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.team1540.bunnybotTank2023.commands.auto.AutoShoot5RamTotes;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.commands.drivetrain.TankdriveCommand;
import org.team1540.bunnybotTank2023.commands.shooter.Shooter;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainIOSim;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainIOReal;
import org.team1540.bunnybotTank2023.io.shooter.ShooterIOReal;
import org.team1540.bunnybotTank2023.io.shooter.ShooterIOSim;

import static org.team1540.bunnybotTank2023.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // Subsystems
    Drivetrain drivetrain;
    Shooter shooter;

    // Controllers
    CommandXboxController driver = new CommandXboxController(0);
    CommandXboxController copilot = new CommandXboxController(1);

    public RobotContainer() {
        if (Robot.isReal()) {
            // Initialize subsystems with hardware IO
            drivetrain = new Drivetrain(new DrivetrainIOReal());
            shooter = new Shooter(new ShooterIOReal());
        } else {
            // Initialize subsystems with simulation IO
            drivetrain = new Drivetrain(new DrivetrainIOSim());
            shooter = new Shooter(new ShooterIOSim());
        }
        setDefaultCommands();
        configureButtonBindings();
    }
    
    
    /** Use this method to define your trigger->command mappings. */
    private void configureButtonBindings() {
    }

    private void setDefaultCommands() {
        drivetrain.setDefaultCommand(new TankdriveCommand(drivetrain, driver));
        shooter.setDefaultCommand(new StartEndCommand(
                () -> shooter.setVelocity(ShooterConstants.SHOOTER_IDLE_RPM),
                () -> shooter.stop()
        ));
    }


    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return new AutoShoot5RamTotes(drivetrain);
    }
}
