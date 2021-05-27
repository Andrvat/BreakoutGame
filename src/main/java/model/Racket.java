package model;

import model.AbstractGameElement;
import utilities.ConfigurationsFields;
import utilities.Moveable;
import utilities.PathsDistributor;

import javax.swing.ImageIcon;

public class Racket extends AbstractGameElement implements Moveable {

    private int directionalStep;

    public Racket() {
        initRacketSettings();
    }

    private void initRacketSettings() {
        loadOriginalImage();
        installImageDimensionsByOriginalImage();
        installInitialRacketPosition();
    }

    private void loadOriginalImage() {
        setImage(new ImageIcon(PathsDistributor.getPathToRacketImageFromContentRoot()).getImage());
    }

    private void installInitialRacketPosition() {
        setXPosition(ConfigurationsFields.INIT_RACKET_X_LOCATION.getValue());
        setYPosition(ConfigurationsFields.INIT_RACKET_Y_LOCATION.getValue());
    }

    public void setDirectionalStep(int directionalStep) {
        this.directionalStep = directionalStep;
    }

    @Override
    public void makeMove() {
        setXPosition(getXPosition() + directionalStep);

        if (BallPositionsManager.hasLeftBorderReached(getXPosition(), 0)) {
            leaveRacketInPlace(0);
        }

        if (BallPositionsManager.hasRightBorderReached(getXPosition(),
                ConfigurationsFields.SCREEN_WIDTH.getValue() - getImageWidth())) {
            leaveRacketInPlace(ConfigurationsFields.SCREEN_WIDTH.getValue() - getImageWidth());
        }
    }

    private void leaveRacketInPlace(int placeXCords) {
        setXPosition(placeXCords);
    }
}
