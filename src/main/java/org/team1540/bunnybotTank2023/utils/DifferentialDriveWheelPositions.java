package org.team1540.bunnybotTank2023.utils;

import java.util.Objects;

public class DifferentialDriveWheelPositions {
    /** Distance traveled by the left side of the drivetrain */
    public double leftDistanceMeters;

    /** Distance traveled by the right side of the drivetrain */
    public double rightDistanceMeters;

    public DifferentialDriveWheelPositions(double leftDistanceMeters, double rightDistanceMeters) {
        this.leftDistanceMeters = leftDistanceMeters;
        this.rightDistanceMeters = rightDistanceMeters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DifferentialDriveWheelPositions that = (DifferentialDriveWheelPositions) o;
        return Math.abs(that.leftDistanceMeters - leftDistanceMeters) < 1E-9
                && Math.abs(that.rightDistanceMeters - rightDistanceMeters) < 1E-9;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftDistanceMeters, rightDistanceMeters);
    }
}
