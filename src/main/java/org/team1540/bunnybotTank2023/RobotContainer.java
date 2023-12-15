// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1540.bunnybotTank2023;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.littletonrobotics.junction.networktables.LoggedDashboardInput;
import org.littletonrobotics.junction.networktables.LoggedDashboardNumber;
import org.team1540.bunnybotTank2023.commands.auto.AutoShoot5RamTotes;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.commands.drivetrain.TankdriveCommand;
import org.team1540.bunnybotTank2023.commands.indexer.Indexer;
import org.team1540.bunnybotTank2023.commands.indexer.IndexerCommand;
import org.team1540.bunnybotTank2023.commands.shooter.Shooter;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainIOSim;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainIOReal;
import org.team1540.bunnybotTank2023.io.indexer.IndexerIOReal;
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
    Indexer indexer;

    // Controllers
    CommandXboxController driver = new CommandXboxController(0);
    CommandXboxController copilot = new CommandXboxController(1);

    LoggedDashboardNumber shooterSpeed = new LoggedDashboardNumber("Shooter/setpoint");

    public RobotContainer() {
        if (Robot.isReal()) {
            // Initialize subsystems with hardware IO
            drivetrain = new Drivetrain(new DrivetrainIOReal());
            shooter = new Shooter(new ShooterIOReal());
            indexer = new Indexer(new IndexerIOReal());
        } else {
            // Initialize subsystems with simulation IO
            drivetrain = new Drivetrain(new DrivetrainIOSim());
            shooter = new Shooter(new ShooterIOSim());
            indexer = null;
        }
        setDefaultCommands();
        configureButtonBindings();
    }
    
    
    /** Use this method to define your trigger->command mappings. */
    private void configureButtonBindings() {
        driver.a()
                .whileTrue(new InstantCommand(() -> shooter.setVelocity(shooterSpeed.get()))
                        .andThen(new IndexerCommand(indexer)))
                .onFalse(new InstantCommand(() -> {
                    shooter.stop();
                    indexer.stop();
                }));

        driver.b().whileTrue(new IndexerCommand(indexer));
    }

    private void setDefaultCommands() {
//        drivetrain.setDefaultCommand(new TankdriveCommand(drivetrain, driver));
        shooter.setDefaultCommand(new StartEndCommand(
                () -> shooter.setVelocity(ShooterConstants.SHOOTER_IDLE_RPM),
                () -> {},
                shooter
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
