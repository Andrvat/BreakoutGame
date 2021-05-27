public enum ConfigurationsFields {
    SCREEN_WIDTH(300),
    SCREEN_HEIGHT(400),
    BOTTOM_EDGE(390),
    TOTAL_BRICKS_NUMBER(30),
    INIT_RACKET_X_LOCATION(200),
    INIT_RACKET_Y_LOCATION(360),
    INIT_BALL_X_LOCATION(230),
    INIT_BALL_Y_LOCATION(355),
    INIT_TIMER_PERIOD(10),
    BRICK_ORIGINAL_IMAGE_WIDTH(40),
    BRICK_ORIGINAL_IMAGE_HEIGHT(10);

    private final int value;

    ConfigurationsFields(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
