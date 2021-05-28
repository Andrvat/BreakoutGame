package view;

import launcher.GameLauncher;
import utilities.ConfigurationsFields;
import utilities.PathsDistributor;

import javax.swing.*;
import java.awt.*;

public class Swing2DMenuView extends JPanel {
    private final JButton startButton = new JButton("Start the game");

    private final JButton exitButton = new JButton("Stop the game");

    private final JButton aboutButton = new JButton("About");

    private final Image background;

    private final JFrame parentFrame;

    public Swing2DMenuView(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        background = new ImageIcon(PathsDistributor.getPathToMenuBackgroundImageFromContentRoot()).getImage();

        configureInitButtonsPanel();
        revalidate();
        repaint();
    }

    private void configureInitButtonsPanel() {
        configureStartGameButton();
        add(startButton);

        configureExitGameButton();
        add(aboutButton);

        configureAboutButton();
        add(exitButton);
    }

    private void configureStartGameButton() {
        configureButtonView(startButton);
        startButton.addActionListener(e -> {
            parentFrame.dispose();
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncher.setVisible(true);
            gameLauncher.startGame();
        });
    }

    private void configureExitGameButton() {
        configureButtonView(exitButton);
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
        configureButtonView(aboutButton);
        aboutButton.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "RU NSU ANDRVAT",
                "About",
                JOptionPane.INFORMATION_MESSAGE));
    }

    private void configureButtonView(JButton button) {
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        button.setBackground(Color.LIGHT_GRAY);
        button.setOpaque(true);
        button.setFont(buttonFont);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background, -background.getWidth(null) / 2, 0, this);
    }
}
