package org.team1540.bunnybotTank2023.utils.vision;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.function.Supplier;

public class TrackedTarget {
    public final Translation2d robotToTarget;
    public final Translation2d fieldToTarget;

    private final Supplier<Pose2d> poseSupplier;

    public TrackedTarget(Rotation2d targetAngle, double distance, Supplier<Pose2d> poseSupplier) {
        robotToTarget = new Translation2d(distance, targetAngle);
        fieldToTarget = poseSupplier.get().getTranslation().plus(robotToTarget);
        this.poseSupplier = poseSupplier;
    }

    public ShootingParameters getShootingParameters() {
        return new ShootingParameters(fieldToTarget, poseSupplier.get());
    }
}
