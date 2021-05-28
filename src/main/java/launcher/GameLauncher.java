package launcher;

import model.GameModel;
import view.Swing2DGameView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        addWindowListenerForOperateClosing();
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    private void addWindowListenerForOperateClosing() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JFrame parentFrame = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.dispose();
                GameMenu gameMenu = new GameMenu();
                gameMenu.showMenu();
            }
        });
    }

    public void startGame() {
        gameModel.launch();
    }
}
