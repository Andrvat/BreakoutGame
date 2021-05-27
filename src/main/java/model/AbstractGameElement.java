package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.security.InvalidParameterException;

public abstract class AbstractGameElement {

    private int xPosition;
    private int yPosition;

    private int imageWidth;
    private int imageHeight;
    private Image image;

    protected void setXPosition(int xPosition) throws InvalidParameterException {
        this.xPosition = xPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    protected void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    protected Rectangle getNewRectangleInstance() {
        return new Rectangle(xPosition, yPosition,
                image.getWidth(null),
                image.getHeight(null));
    }

    protected void installImageDimensionsByOriginalImage() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
}
