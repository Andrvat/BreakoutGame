package controller;

import model.BallPositionsManager;
import model.Racket;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RacketController extends KeyAdapter {
    private final Racket associateRacket;

    public RacketController(Racket associateRacket) {
        this.associateRacket = associateRacket;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeyRelease(e);
    }

    private void handleKeyRelease(KeyEvent event) {
        associateRacket.setDirectionalStep(BallPositionsManager.ZERO_DIRECTION);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e);
    }

    private void handleKeyPress(KeyEvent event) {
        int keyCode = event.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            associateRacket.setDirectionalStep(BallPositionsManager.NEGATIVE_DIRECTION - 1);
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            associateRacket.setDirectionalStep(BallPositionsManager.POSITIVE_DIRECTION + 1);
        }
    }
}
