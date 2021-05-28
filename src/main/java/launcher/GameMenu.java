package launcher;

import utilities.ConfigurationsFields;
import view.Swing2DMenuView;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {


    private final Swing2DMenuView menuView;

    public GameMenu() {
        menuView = new Swing2DMenuView(this);
        initUserInterface();
    }

    private void initUserInterface() {
        setTitle("Breakout Menu");

        add(menuView);

        setPreferredSize(new Dimension(
                ConfigurationsFields.SCREEN_WIDTH.getValue(),
                ConfigurationsFields.SCREEN_HEIGHT.getValue()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        pack();
    }

    public void showMenu() {
        setVisible(true);
    }


    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        gameMenu.showMenu();
    }
}
