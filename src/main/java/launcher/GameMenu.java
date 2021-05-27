package launcher;

import utilities.ConfigurationsFields;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {
    private final JButton startButton = new JButton("Start the game") {{
        setPreferredSize(new Dimension(ConfigurationsFields.SCREEN_WIDTH.getValue() - 50, 30));
    }};

    private final JButton exitButton = new JButton("Stop the game") {{
        setPreferredSize(new Dimension(ConfigurationsFields.SCREEN_WIDTH.getValue() - 50, 30));
    }};

    private final JButton aboutButton = new JButton("About") {{
        setPreferredSize(new Dimension(ConfigurationsFields.SCREEN_WIDTH.getValue() - 50, 30));
    }};

    private final JPanel buttonsPanel = new JPanel();

    public GameMenu() {
        initUserInterface();
    }

    private void initUserInterface() {
        setTitle("Breakout Menu");

        configureInitButtonsPanel();
        add(buttonsPanel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(
                ConfigurationsFields.SCREEN_WIDTH.getValue(),
                ConfigurationsFields.SCREEN_HEIGHT.getValue()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public void showMenu() {
        setVisible(true);
    }

    private void configureInitButtonsPanel() {
        configureStartGameButton();
        buttonsPanel.add(startButton);

        configureExitGameButton();
        buttonsPanel.add(aboutButton);

        configureAboutButton();
        buttonsPanel.add(exitButton);
    }

    private void configureStartGameButton() {
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setOpaque(true);
        startButton.setFont(buttonFont);
        startButton.addActionListener(e -> {
            this.dispose();
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncher.setVisible(true);
            gameLauncher.startGame();
        });
    }

    private void configureExitGameButton() {
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setOpaque(true);
        exitButton.setFont(buttonFont);
        exitButton.addActionListener(e -> {
            int chosenIndex = JOptionPane.showConfirmDialog(this,
                    "Are you sure?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION);
            if (hasOkOptionChosen(chosenIndex)) {
                System.exit(0);
            }
        });
    }

    private boolean hasOkOptionChosen(int chosenIndex) {
        return chosenIndex == 0;
    }

    private void configureAboutButton() {
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        aboutButton.setBackground(Color.LIGHT_GRAY);
        aboutButton.setOpaque(true);
        aboutButton.setFont(buttonFont);
        aboutButton.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "RU NSU ANDRVAT",
                "About",
                JOptionPane.INFORMATION_MESSAGE));
    }


    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        gameMenu.showMenu();
    }
}
