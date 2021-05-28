package launcher;

import model.GameModel;
import view.Swing2DGameView;

import javax.swing.JFrame;

public class GameLauncher extends JFrame {

    private final GameModel gameModel;
    private final Swing2DGameView swing2DGameView;

    public GameLauncher() {
        gameModel = new GameModel();
        swing2DGameView = new Swing2DGameView(gameModel);
        initUserInterface();
    }

    private void initUserInterface() {
        gameModel.addObserver(swing2DGameView);
        add(swing2DGameView);

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
