package view;

import controller.RacketController;
import model.Brick;
import model.Model;
import utilities.ConfigurationsFields;
import utilities.Observer;

import javax.swing.*;
import java.awt.*;

public class Swing2DView extends JPanel implements Observer {
    private final Model model;

    private String message;

    public Swing2DView(Model model) {
        this.model = model;
        buildInitialScreen();
    }

    private void buildInitialScreen() {
        addKeyListener(new RacketController(model.getRacket()));
        setFocusable(true);
        setPreferredSize(new Dimension(
                ConfigurationsFields.SCREEN_WIDTH.getValue(),
                ConfigurationsFields.SCREEN_HEIGHT.getValue()));
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (model.hasGameStarted()) {
            drawElements(graphics2D);
        } else {
            drawInfoAboutGameFinished(graphics2D);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawElements(Graphics2D graphics2D) {
        graphics2D.drawImage(model.getBall().getImage(),
                model.getBall().getXPosition(),
                model.getBall().getYPosition(),
                model.getBall().getImageWidth(),
                model.getBall().getImageHeight(),
                this);

        graphics2D.drawImage(model.getRacket().getImage(),
                model.getRacket().getXPosition(),
                model.getRacket().getYPosition(),
                model.getRacket().getImageWidth(),
                model.getRacket().getImageHeight(),
                this);

        for (int i = 0; i < model.getTotalBricksNumber(); i++) {
            Brick brick = model.getNextBrick();
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
        graphics2D.drawString(message,
                (ConfigurationsFields.SCREEN_WIDTH.getValue() - fontMetrics.stringWidth(message)) / 2,
                ConfigurationsFields.SCREEN_WIDTH.getValue() / 2);
    }

    @Override
    public void update(String message) {
        this.message = message;
        repaint();
    }
}
