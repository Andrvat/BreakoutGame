package view;

import controller.RacketController;
import model.Brick;
import model.GameModel;
import utilities.ConfigurationsFields;
import utilities.Observer;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Swing2DView extends JPanel implements Observer {
    private final GameModel gameModel;

    private String message;

    public Swing2DView(GameModel gameModel) {
        this.gameModel = gameModel;
        buildInitialScreen();
    }

    private void buildInitialScreen() {
        addKeyListener(new RacketController(gameModel.getRacket()));
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

        if (message != null && message.equals("GO!")) {
            showCurrentMessageOnScreen(graphics2D);
            try {
                sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            Toolkit.getDefaultToolkit().sync();
            return;
        }

        if (gameModel.hasGameStarted()) {
            drawElements(graphics2D);
        } else {
            showCurrentMessageOnScreen(graphics2D);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawElements(Graphics2D graphics2D) {
        graphics2D.drawImage(gameModel.getBall().getImage(),
                gameModel.getBall().getXPosition(),
                gameModel.getBall().getYPosition(),
                gameModel.getBall().getImageWidth(),
                gameModel.getBall().getImageHeight(),
                this);

        graphics2D.drawImage(gameModel.getRacket().getImage(),
                gameModel.getRacket().getXPosition(),
                gameModel.getRacket().getYPosition(),
                gameModel.getRacket().getImageWidth(),
                gameModel.getRacket().getImageHeight(),
                this);

        for (int i = 0; i < gameModel.getTotalBricksNumber(); i++) {
            Brick brick = gameModel.getNextBrick();
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

    private void showCurrentMessageOnScreen(Graphics2D graphics2D) {
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
