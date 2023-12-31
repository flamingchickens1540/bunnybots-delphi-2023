package org.team1540.bunnybotTank2023.utils;

import java.util.LinkedList;

/**
 * Class for applying a rolling average filter to a stream of numerical data.
 * Useful for detecting stabilization of measurements or filtering noise from
 * a dataset
 */
public class AverageFilter {
    private final LinkedList<Double> items = new LinkedList<>();
    private double sum = 0;
    private final int size;

    public AverageFilter(int size) {
        this.size = size;
    }

    public void clear() {
        items.clear();
        sum = 0;
    }

    public void add(double value) {
        items.addLast(value);
        sum += value;
        if (items.size() > this.size) {
            sum -= items.removeFirst();
        }
    }

    public double getAverage() {
        return sum/(double) items.size();
    }
}
