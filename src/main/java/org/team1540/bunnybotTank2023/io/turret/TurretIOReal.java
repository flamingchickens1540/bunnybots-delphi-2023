package org.team1540.bunnybotTank2023.io.turret;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import org.team1540.bunnybotTank2023.Constants;

public class TurretIOReal implements TurretIO{
    // fields ^-^
    private final TalonFX motor = new TalonFX(Constants.TurretConstants.MOTOR_ID);
    final PositionVoltage positionVoltage = new PositionVoltage(0);
    private Rotation2d setPosition = new Rotation2d();

    // constructor
    TurretIOReal(){
    // robot init, set slot 0 gains
        var slot0Configs = new Slot0Configs();
        slot0Configs.kP = Constants.TurretConstants.kP;
        slot0Configs.kI = Constants.TurretConstants.kI;
        slot0Configs.kD = Constants.TurretConstants.kD;

        motor.getConfigurator().apply(slot0Configs, 0.050);

        positionVoltage.Slot = 0;
    }

    // sets the voltage
        // note to Luna: this is just bc the turret is hard to move manually, so this helps!
    @Override
    public void setVoltage(double volts) {
        motor.setVoltage(volts);
    }


    public void setTurretPosition(Rotation2d position){
        motor.setControl(positionVoltage.withPosition(position.getRotations()));
        setPosition = position;
    }

    @Override
    public void updateInputs(TurretInputs inputs) {
        inputs.velocity = motor.getVelocity().getValue();
        inputs.currentPosition = motor.getPosition().getValue();
        inputs.setPointRotations = setPosition.getRotations();
        inputs.current = motor.getSupplyCurrent().getValue();
        inputs.voltage = motor.getSupplyVoltage().getValue();
    }
}
