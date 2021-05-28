package model;

public class BallPositionsManager {
    public static int POSITIVE_DIRECTION = 2;
    public static int ZERO_DIRECTION = 0;
    public static int NEGATIVE_DIRECTION = -2;

    public static boolean hasLeftBorderReached(int currentX, int leftBorder) {
        return currentX <= leftBorder;
    }

    public static boolean hasRightBorderReached(int currentX, int rightBorder) {
        return currentX >= rightBorder;
    }

    public static boolean hasTopBorderReached(int currentY, int topBorder) {
        return currentY <= topBorder;
    }

    public static boolean hasBottomBorderReached(int currentY, int bottomBorder) {
        return currentY >= bottomBorder;
    }

    public static boolean hasBallGotToRacketSpecifiedPart(int ballLeftPositionByX, int racketSpecifiedPartRightPositionByX) {
        return ballLeftPositionByX < racketSpecifiedPartRightPositionByX;
    }

}
