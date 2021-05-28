package utilities;

public enum ConfigurationsFields {
    SCREEN_WIDTH(600),
    SCREEN_HEIGHT(500),
    BOTTOM_EDGE(490),
    INIT_RACKET_X_LOCATION(300),
    INIT_RACKET_Y_LOCATION(460),
    INIT_BALL_X_LOCATION(300),
    INIT_BALL_Y_LOCATION(470),
    INIT_TIMER_PERIOD(10),
    BRICK_ORIGINAL_IMAGE_WIDTH(40),
    BRICK_ORIGINAL_IMAGE_HEIGHT(10);

    private final int value;

    ConfigurationsFields(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
