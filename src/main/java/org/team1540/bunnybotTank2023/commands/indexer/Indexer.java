package org.team1540.bunnybotTank2023.commands.indexer;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.bunnybotTank2023.Constants;

public class Indexer extends SubsystemBase {
    private final CANSparkMax topNEO = new CANSparkMax(Constants.IndexerConstants.TOP_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax bottomNEO = new CANSparkMax(Constants.IndexerConstants.BOTTOM_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
}
