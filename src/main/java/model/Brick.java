package model;

import model.AbstractGameElement;
import utilities.PathsDistributor;

import javax.swing.ImageIcon;

public class Brick extends AbstractGameElement {

    private boolean hasDestroyed;

    public Brick(int xPosition, int yPosition) {
        initBrickSettings(xPosition, yPosition);
    }

    private void initBrickSettings(int xPosition, int yPosition) {
        hasDestroyed = false;

        setXPosition(xPosition);
        setYPosition(yPosition);

        loadOriginalImage();
        installImageDimensionsByOriginalImage();
    }

    private void loadOriginalImage() {
        setImage(new ImageIcon(PathsDistributor.getPathToBrickImageFromContentRoot()).getImage());
    }

    public boolean hasDestroyed() {
        return hasDestroyed;
    }

    void setDestroyed(boolean hasDestroyed) {
        this.hasDestroyed = hasDestroyed;
    }
}
