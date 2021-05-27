import javax.swing.ImageIcon;
import java.security.InvalidParameterException;

public class Ball extends AbstractGameElement implements Moveable {

    private int xDirection;
    private int yDirection;

    public Ball() {
        initBallSettings();
    }

    private void initBallSettings() {
        initCoordinateDirections();
        loadOriginalImage();
        installImageDimensionsByOriginalImage();
        installInitialBallPosition();
    }

    private void initCoordinateDirections() {
        xDirection = 1;
        yDirection = -1;
    }

    private void loadOriginalImage() {
        setImage(new ImageIcon(PathsDistributor.getPathToBallImageFromContentRoot()).getImage());
    }

    private void installInitialBallPosition() {
        setXPosition(ConfigurationsFields.INIT_BALL_X_LOCATION.getValue());
        setYPosition(ConfigurationsFields.INIT_BALL_Y_LOCATION.getValue());
    }

    public void setXDirection(int newXDirection) throws InvalidParameterException {
        if (BallPositionsManager.isNewDirectionValid(newXDirection)) {
            xDirection = newXDirection;
        } else {
            throw new InvalidParameterException("Trying to set invalid by X ball direction");
        }
    }

    public void setYDirection(int newYDirection) {
        if (BallPositionsManager.isNewDirectionValid(newYDirection)) {
            yDirection = newYDirection;
        } else {
            throw new InvalidParameterException("Trying to set invalid by Y ball direction");
        }
    }

    public int getYDirection() {
        return yDirection;
    }

    @Override
    public void makeMove() {
        setXPosition(getXPosition() + xDirection);
        setYPosition(getYPosition() + yDirection);

        if (BallPositionsManager.hasLeftBorderReached(getXPosition(), 0)) {
            setXDirection(BallPositionsManager.POSITIVE_DIRECTION);
        }

        if (BallPositionsManager.hasRightBorderReached(getXPosition(),
                ConfigurationsFields.SCREEN_WIDTH.getValue() - getImageWidth())) {
            setXDirection(BallPositionsManager.NEGATIVE_DIRECTION);
        }

        if (BallPositionsManager.hasTopBorderReached(getYPosition(), 0)) {
            setYDirection(BallPositionsManager.POSITIVE_DIRECTION);
        }
    }
}
