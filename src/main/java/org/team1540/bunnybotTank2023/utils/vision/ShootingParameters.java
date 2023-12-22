package org.team1540.bunnybotTank2023.utils.vision;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;


public class ShootingParameters {
    public final Rotation2d turretAngle;
    public final double distanceMeters;

    // probably incorrect math, might not use
    public ShootingParameters(Translation2d fieldToTargetMeters, Pose2d robotPoseMeters) {
        distanceMeters = fieldToTargetMeters.getDistance(robotPoseMeters.getTranslation());
        Translation2d deltaTranslation = robotPoseMeters.getTranslation().minus(fieldToTargetMeters);
        turretAngle = Rotation2d.fromRadians(Math.atan2(deltaTranslation.getX(), deltaTranslation.getY())).plus(robotPoseMeters.getRotation());
    }
}
