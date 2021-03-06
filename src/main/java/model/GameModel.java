package model;

import utilities.ConfigurationsFields;
import utilities.Observer;

import javax.swing.Timer;
import java.awt.*;
import java.util.ArrayList;

public class GameModel {

    private Timer eventTimer;

    private String message;

    private Ball ball;

    private Racket racket;

    private final ArrayList<Brick> bricks = new ArrayList<>();
    private int ringListBrickIndex = 0;
    private int totalBricksNumber = 0;

    private boolean hasGameStarted;

    private final ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public GameModel() {
        initGameElements();
    }

    public void launch() {
        setReadyGoState();
        launchTimer();
    }

    private void setReadyGoState() {
        message = "GO!";
        notifyObservers();

        hasGameStarted = true;
        message = null;
    }

    private void initGameElements() {
        ball = new Ball();
        racket = new Racket();

        initBricks();
    }

    private void initBricks() {
        BrickType[] brickTypes = BrickType.values();
        int currentBrickTypeIndex = 0;
        totalBricksNumber = 0;
        for (int i = 0; i < 8; i++) {
            if (isNeedToChangeBrickTypeToNext(i)) {
                currentBrickTypeIndex++;
            }

            for (int j = 0; j < 13; j++) {
                bricks.add(new Brick(
                        j * ConfigurationsFields.BRICK_ORIGINAL_IMAGE_WIDTH.getValue() + 30,
                        i * ConfigurationsFields.BRICK_ORIGINAL_IMAGE_HEIGHT.getValue() + 50,
                        brickTypes[currentBrickTypeIndex]));
                totalBricksNumber++;
            }
        }
    }

    private boolean isNeedToChangeBrickTypeToNext(int i) {
        return i != 0 && i % 2 == 0;
    }

    private void launchTimer() {
        eventTimer = new Timer(ConfigurationsFields.INIT_TIMER_PERIOD.getValue(), e -> doGameCycle());
        eventTimer.start();
    }

    private void doGameCycle() {
        ball.makeMove();
        racket.makeMove();
        handleCurrentStepActions();
        notifyObservers();
    }

    private void stopGame() {
        hasGameStarted = false;
        eventTimer.stop();
        notifyObservers();
    }

    public Racket getRacket() {
        return racket;
    }

    public Ball getBall() {
        return ball;
    }

    public boolean hasGameStarted() {
        return hasGameStarted;
    }

    public int getTotalBricksNumber() {
        return totalBricksNumber;
    }

    public Brick getNextBrick() {
        Brick brick = bricks.get(ringListBrickIndex);
        ringListBrickIndex++;
        if (ringListBrickIndex >= totalBricksNumber) {
            ringListBrickIndex = 0;
        }
        return brick;
    }

    private void handleCurrentStepActions() {
        if (hasBottomEdgeReached()) {
            message = "Game Over!";
            stopGame();
        }

        if (hasPlayerWon()) {
            message = "Victory!";
            stopGame();
        }

        if ((ball.getNewRectangleInstance()).intersects(racket.getNewRectangleInstance())) {
            redirectBallToNewSideAfterIntersectionWithRacket();
        }

        for (int i = 0; i < totalBricksNumber; i++) {
            Brick brick = bricks.get(i);
            if ((ball.getNewRectangleInstance()).intersects(brick.getNewRectangleInstance())) {
                redirectBallToNewSideAfterIntersectionWithBrick(brick);
                speedUpBallIfNecessary(brick);
                brick.setDestroyed();
            }
        }
    }

    private boolean hasBottomEdgeReached() {
        return ball.getNewRectangleInstance().getMaxY() > ConfigurationsFields.BOTTOM_EDGE.getValue();
    }

    private boolean hasPlayerWon() {
        int destroyedBricks = 0;
        for (int i = 0; i < totalBricksNumber; i++) {
            Brick brick = bricks.get(i);
            if (brick.hasDestroyed()) {
                destroyedBricks++;
            }
        }

        return destroyedBricks == totalBricksNumber;
    }

    private void speedUpBallIfNecessary(Brick brick) {
        BrickType brickType = brick.getType();
        if (brickType == BrickType.ORANGE && brickType.hasNeverDestroyed()) {
            brickType.setDestroyedAtLeastOnce();
            eventTimer.setDelay(eventTimer.getDelay() - 1);
        }
        if (brickType == BrickType.RED && brickType.hasNeverDestroyed()) {
            brickType.setDestroyedAtLeastOnce();
            eventTimer.setDelay(eventTimer.getDelay() - 1);
        }
    }

    private void redirectBallToNewSideAfterIntersectionWithRacket() {
        int racketLeftPositionByX = (int) racket.getNewRectangleInstance().getMinX();
        int ballLeftPositionByX = (int) ball.getNewRectangleInstance().getMinX();

        /*
         * ATTENTION!
         * The values depends on the width of the original image racket width
         */
        int racketFirstPartRightPositionByX = racketLeftPositionByX + 8;
        int racketSecondPartRightPositionByX = racketLeftPositionByX + 16;
        int racketThirdPartRightPositionByX = racketLeftPositionByX + 24;
        int racketFourthPartRightPositionByX = racketLeftPositionByX + 32;

        if (BallPositionsManager.hasBallGotToRacketSpecifiedPart(ballLeftPositionByX, racketFirstPartRightPositionByX)) {
            redirectBallToNorthWest();
        } else if (BallPositionsManager.hasBallGotToRacketSpecifiedPart(ballLeftPositionByX, racketSecondPartRightPositionByX)) {
            redirectBallToWestInRelationToY();
        } else if (BallPositionsManager.hasBallGotToRacketSpecifiedPart(ballLeftPositionByX, racketThirdPartRightPositionByX)) {
            redirectBallToNorth();
        } else if (BallPositionsManager.hasBallGotToRacketSpecifiedPart(ballLeftPositionByX, racketFourthPartRightPositionByX)) {
            redirectBallToEastInRelationToY();
        } else if (BallPositionsManager.hasBallGotToRacketSpecifiedPart(racketFourthPartRightPositionByX, ballLeftPositionByX)) {
            redirectBallToEast();
        }
    }

    private void redirectBallToNorthWest() {
        ball.setXDirection(BallPositionsManager.NEGATIVE_DIRECTION);
        ball.setYDirection(BallPositionsManager.NEGATIVE_DIRECTION);
    }

    private void redirectBallToWestInRelationToY() {
        ball.setXDirection(BallPositionsManager.NEGATIVE_DIRECTION);
        ball.setYDirection(BallPositionsManager.NEGATIVE_DIRECTION * ball.getYDirection());
    }

    private void redirectBallToNorth() {
        ball.setXDirection(BallPositionsManager.ZERO_DIRECTION);
        ball.setYDirection(BallPositionsManager.NEGATIVE_DIRECTION * 2);
    }

    private void redirectBallToEastInRelationToY() {
        ball.setXDirection(BallPositionsManager.POSITIVE_DIRECTION);
        ball.setYDirection(BallPositionsManager.NEGATIVE_DIRECTION * ball.getYDirection());
    }

    private void redirectBallToEast() {
        ball.setXDirection(BallPositionsManager.POSITIVE_DIRECTION);
        ball.setYDirection(BallPositionsManager.NEGATIVE_DIRECTION);
    }

    private void redirectBallToNewSideAfterIntersectionWithBrick(Brick brick) {
        int ballLeftPosition = (int) ball.getNewRectangleInstance().getMinX();
        int ballTopPosition = (int) ball.getNewRectangleInstance().getMinY();
        int ballHeight = (int) ball.getNewRectangleInstance().getHeight();
        int ballWidth = (int) ball.getNewRectangleInstance().getWidth();

        Point ballLeftTopCornerPoint = new Point(ballLeftPosition + ballWidth + 1, ballTopPosition);
        Point ballRightTopCornerPoint = new Point(ballLeftPosition - 1, ballTopPosition);
        Point ballLeftBottomCornerPoint = new Point(ballLeftPosition, ballTopPosition - 1);
        Point ballRightBottomCornerPoint = new Point(ballLeftPosition, ballTopPosition + ballHeight + 1);

        if (!brick.hasDestroyed()) {
            if (brick.getNewRectangleInstance().contains(ballLeftTopCornerPoint)) {
                ball.setXDirection(BallPositionsManager.NEGATIVE_DIRECTION); // to the west
            } else if (brick.getNewRectangleInstance().contains(ballRightTopCornerPoint)) {
                ball.setXDirection(BallPositionsManager.POSITIVE_DIRECTION); // to the east
            }

            if (brick.getNewRectangleInstance().contains(ballLeftBottomCornerPoint)) {
                ball.setYDirection(BallPositionsManager.POSITIVE_DIRECTION); // to the north
            } else if (brick.getNewRectangleInstance().contains(ballRightBottomCornerPoint)) {
                ball.setYDirection(BallPositionsManager.NEGATIVE_DIRECTION); // to the south
            }
        }
    }
}
