package launcher;

import model.Model;
import view.Swing2DView;

import javax.swing.JFrame;
import java.awt.*;

public class GameLauncher extends JFrame {

    private final Model model;
    private final Swing2DView swing2DView;

    public GameLauncher() {
        model = new Model();
        swing2DView = new Swing2DView(model);
        initUserInterface();
    }

    public void startGame() {
        model.launch();
    }

    private void initUserInterface() {
        model.addObserver(swing2DView);
        add(swing2DView);

        setTitle("Breakout Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        GameLauncher launcher = new GameLauncher();
        launcher.setVisible(true);
        launcher.startGame();
    }
}
