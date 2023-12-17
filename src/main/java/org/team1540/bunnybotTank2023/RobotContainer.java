// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.team1540.bunnybotTank2023;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;
import org.team1540.bunnybotTank2023.commands.auto.*;
import org.team1540.bunnybotTank2023.commands.drivetrain.ArcadeDriveCommand;
import org.team1540.bunnybotTank2023.commands.drivetrain.Drivetrain;
import org.team1540.bunnybotTank2023.commands.indexer.Indexer;
import org.team1540.bunnybotTank2023.commands.indexer.IndexerCommand;
import org.team1540.bunnybotTank2023.commands.indexer.IndexerIdleCommand;
import org.team1540.bunnybotTank2023.commands.intake.Intake;
import org.team1540.bunnybotTank2023.commands.intake.IntakeCommand;
import org.team1540.bunnybotTank2023.commands.shooter.ShootSequenceCommand;
import org.team1540.bunnybotTank2023.commands.shooter.Shooter;
import org.team1540.bunnybotTank2023.commands.turret.*;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainIOSim;
import org.team1540.bunnybotTank2023.io.drivetrain.DrivetrainIOReal;
import org.team1540.bunnybotTank2023.io.indexer.IndexerIOReal;
import org.team1540.bunnybotTank2023.io.intake.IntakeIOReal;
import org.team1540.bunnybotTank2023.io.shooter.ShooterIOReal;
import org.team1540.bunnybotTank2023.io.shooter.ShooterIOSim;
import org.team1540.bunnybotTank2023.io.turret.TurretIOReal;
import org.team1540.bunnybotTank2023.io.vision.Limelight;
import org.team1540.bunnybotTank2023.io.vision.LimelightIOReal;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // Subsystems
    final Drivetrain drivetrain;
    final Shooter shooter;
    final Indexer indexer;
    final Intake intake;
    final Turret turret;
    final Limelight limelight;

    final LoggedDashboardChooser<Command> autoChooser = new LoggedDashboardChooser<>("AutoChooser");

    // Controllers
    CommandXboxController driver = new CommandXboxController(0);
    CommandXboxController copilot = new CommandXboxController(1);

    public RobotContainer() {
        if (Robot.isReal()) {
            // Initialize subsystems with hardware IO
            drivetrain = new Drivetrain(new DrivetrainIOReal());
            shooter = new Shooter(new ShooterIOReal());
            indexer = new Indexer(new IndexerIOReal());
            intake = new Intake(new IntakeIOReal());
            turret = new Turret(new TurretIOReal());
            limelight = new Limelight(new LimelightIOReal());
        } else {
            // Initialize subsystems with simulation IO
            drivetrain = new Drivetrain(new DrivetrainIOSim());
            shooter = new Shooter(new ShooterIOSim());
            indexer = null;
            intake = null;
            turret = null;
            limelight = null;
        }

        initAutoChooser();
        configureButtonBindings();
    }
    
    
    /** Use this method to define your trigger->command mappings. */
    private void configureButtonBindings() {
        copilot.a().whileTrue(
                Commands.parallel(
                        new IntakeCommand(intake),
                        new IndexerCommand(indexer)
                )
        );
        copilot.b().whileTrue(new IntakeCommand(intake, -1));

        copilot.y().whileTrue(new TurretZeroSequenceCommand(turret));
        copilot.x().whileTrue(new InstantCommand(intake::stop));
        copilot.leftBumper().whileTrue(new TurretSetpointCommand(turret, Rotation2d.fromDegrees(0)));
        copilot.rightTrigger().onTrue(new ShootSequenceCommand(shooter, indexer, Constants.ShooterConstants.SHOOTER_ACTIVE_RPM));
        copilot.leftTrigger().whileTrue(new TurretTrackTargetCommand(turret, limelight));

//        copilot.x().onTrue(new InstantCommand(() -> intake.setFold(false))).onFalse(new InstantCommand(() -> intake.setFold(true)));
    }

    public void setTeleopDefaultCommands() {
        drivetrain.setDefaultCommand(new ArcadeDriveCommand(drivetrain, driver));
        shooter.setDefaultCommand(new StartEndCommand(
                shooter::stop,
                () -> {},
                shooter
        ));
        turret.setDefaultCommand(new TurretManualCommand(turret, copilot));
        indexer.setDefaultCommand(new IndexerIdleCommand(indexer));
        intake.setDefaultCommand(new StartEndCommand(
                () -> intake.setMotorSpeed(0.3),
                () -> {},
                intake
        ));
    }

    public void setAutoDefaultCommands() {
        drivetrain.removeDefaultCommand();
        turret.removeDefaultCommand();
//        indexer.setDefaultCommand(new IndexerIdleCommand(indexer));
//        intake.setDefaultCommand(new StartEndCommand(
//                () -> intake.setMotorSpeed(0.5),
//                () -> {},
//                intake
//        ));
        indexer.removeDefaultCommand();
        intake.removeDefaultCommand();
    }

    private void initAutoChooser() {
        autoChooser.addDefaultOption("ZeroTurret", new TurretZeroSequenceCommand(turret));
        autoChooser.addOption("DoNothing", new InstantCommand());
        autoChooser.addOption("AutoTaxi", new AutoTaxi(drivetrain, turret));
        autoChooser.addOption("LeftSideRamTotes", new AutoLeftSideRamTotes(drivetrain, turret));
        autoChooser.addOption("RightSideRamTotes", new AutoRightSideRamTotes(drivetrain, turret));
        autoChooser.addOption("LeftSideRamSinusoid", new AutoLeftSideRamSinusoid(drivetrain, turret));
        autoChooser.addOption("RightSideRamSinusoid", new AutoRightSideRamSinusoid(drivetrain, turret));
        autoChooser.addOption("TaxiShoot5", new AutoTaxiShoot5(drivetrain, indexer, turret, shooter, limelight));
    }

    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return autoChooser.get();
    }
}
