import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    private Timer eventTimer;
    private String finalMessage;
    private Ball ball;
    private Racket racket;
    private final ArrayList<Brick> bricks = new ArrayList<>();
    private int totalBricksNumber;
    private boolean hasGameStarted = true;

    public Board() {
        initBoardSettings();
    }

    private void initBoardSettings() {
        initGameElements();
        addKeyListener(new RacketController(racket));
        setFocusable(true);
        setPreferredSize(new Dimension(
                ConfigurationsFields.SCREEN_WIDTH.getValue(),
                ConfigurationsFields.SCREEN_HEIGHT.getValue()));
        repaint();
        launchTimer();
    }

    private void initGameElements() {
        ball = new Ball();
        racket = new Racket();

        totalBricksNumber = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks.add(new Brick(
                        j * ConfigurationsFields.BRICK_ORIGINAL_IMAGE_WIDTH.getValue() + 30,
                        i * ConfigurationsFields.BRICK_ORIGINAL_IMAGE_HEIGHT.getValue() + 50));
                totalBricksNumber++;
            }
        }
    }

    private void launchTimer() {
        eventTimer = new Timer(ConfigurationsFields.INIT_TIMER_PERIOD.getValue(), e -> doGameCycle());
        eventTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (hasGameStarted) {
            drawElements(graphics2D);
        } else {
            drawInfoAboutGameFinished(graphics2D);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawElements(Graphics2D graphics2D) {
        graphics2D.drawImage(ball.getImage(),
                ball.getXPosition(),
                ball.getYPosition(),
                ball.getImageWidth(),
                ball.getImageHeight(),
                this);

        graphics2D.drawImage(racket.getImage(),
                racket.getXPosition(),
                racket.getYPosition(),
                racket.getImageWidth(),
                racket.getImageHeight(),
                this);

        for (int i = 0; i < totalBricksNumber; i++) {
            Brick brick = bricks.get(i);
            if (!brick.hasDestroyed()) {
                graphics2D.drawImage(brick.getImage(),
                        brick.getXPosition(),
                        brick.getYPosition(),
                        brick.getImageWidth(),
                        brick.getImageHeight(),
                        this);
            }
        }
    }

    private void drawInfoAboutGameFinished(Graphics2D graphics2D) {
        Font font = new Font("Verdana", Font.BOLD, 20);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(font);
        graphics2D.drawString(finalMessage,
                (ConfigurationsFields.SCREEN_WIDTH.getValue() - fontMetrics.stringWidth(finalMessage)) / 2,
                ConfigurationsFields.SCREEN_WIDTH.getValue() / 2);
    }

    private void doGameCycle() {
        ball.makeMove();
        racket.makeMove();
        handleCurrentStepActions();
        repaint();
    }

    private void stopGame() {
        hasGameStarted = false;
        eventTimer.stop();
    }

    private void handleCurrentStepActions() {
        if (hasBottomEdgeReached()) {
            finalMessage = "Game Over";
            stopGame();
        }

        if (hasPlayerWon()) {
            finalMessage = "Victory";
            stopGame();
        }

        if ((ball.getNewRectangleInstance()).intersects(racket.getNewRectangleInstance())) {
            redirectBallToNewSideAfterIntersectionWithRacket();
        }

        for (int i = 0; i < totalBricksNumber; i++) {
            Brick brick = bricks.get(i);
            if ((ball.getNewRectangleInstance()).intersects(brick.getNewRectangleInstance())) {
                redirectBallToNewSideAfterIntersectionWithBrick(brick);
                brick.setDestroyed(true);
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
        ball.setYDirection(BallPositionsManager.NEGATIVE_DIRECTION);
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
