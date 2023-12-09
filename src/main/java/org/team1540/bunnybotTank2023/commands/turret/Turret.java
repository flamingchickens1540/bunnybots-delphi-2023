package org.team1540.bunnybotTank2023.commands.turret;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
    public enum MachineState {
        AUTO, MANUAL, ZERO
    }

    //fields
    private MachineState machineState;
}

 /* Turret:
Motor - talonFX = falcon (falcon runs 1000 times per sec)
Running a PID on said motor

The turret will spin and have a camera.
Camera should be able to see buckets - red and blue buckets
	red and blue are different teams, you want hit other team’s buckets with balls to get pts

You want as many possible bunnies in your zone at the end of auto and at the end of the game

Turret should be able to automatically go to a position (auto set position)
Driver should be able to Manually control the turret
Zero the turret at the beginning of the match

Auto set position (requires PID, will take a position as an argument)
Manual control (WILL NOT use PID, take direct input from controller)
Zero and limits (set the motor voltage to something low, run until hits the limit, then from there know the position)
	ideally the direct front of the robot is 0, to the left of that is a negative value until the limit, to the right is a positive value until the limit.

Use a state machine system for this: (use enum: https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html)
	set point/auto state
	manual state
	zeroing state

Check the state in periodic, also in periodic switch state


Motor encoder is relative,
Turret will have limit switches, which allow zeroing to happen */
