package model;

import utilities.PathsDistributor;

import javax.swing.ImageIcon;

public class Brick extends AbstractGameElement {

    private boolean hasDestroyed;

    private final BrickType type;

    public Brick(int xPosition, int yPosition, BrickType type) {
        this.type = type;
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
        setImage(new ImageIcon(PathsDistributor.getPathToBrickImageFromContentRoot(type)).getImage());
    }

    public BrickType getType() {
        return type;
    }

    public boolean hasDestroyed() {
        return hasDestroyed;
    }

    void setDestroyed() {
        hasDestroyed = true;
    }
}
