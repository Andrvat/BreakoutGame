package launcher;

import model.GameModel;
import view.Swing2DView;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;

public class GameLauncher extends JFrame {

    private final GameModel gameModel;
    private final Swing2DView swing2DView;

    public GameLauncher() {
        gameModel = new GameModel();
        swing2DView = new Swing2DView(gameModel);
        initUserInterface();
    }

    private void initUserInterface() {
        gameModel.addObserver(swing2DView);
        add(swing2DView);

        setTitle("Breakout Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public void startGame() {
        gameModel.launch();
    }
}
