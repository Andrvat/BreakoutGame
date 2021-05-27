package launcher;

import model.Model;
import view.Swing2DView;

import javax.swing.JFrame;

public class GameLauncher extends JFrame {

    public GameLauncher() {
        initUserInterface();
    }

    private void initUserInterface() {
        Model breakoutModel = new Model();
        Swing2DView swing2DView = new Swing2DView(breakoutModel);

        breakoutModel.addObserver(swing2DView);
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
    }
}
