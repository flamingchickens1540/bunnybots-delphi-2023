package org.team1540.bunnybotTank2023.utils.vision;

public enum LEDState {
    /* Pipeline default */
    PIPELINE(0),

    /* LEDs off */
    OFF(1),

    /* Blink LEDs */
    BLINK(2),

    /* LEDs on */
    ON(3);

    private final int value;

    LEDState(int value) {
        this.value = value;
    }
}
