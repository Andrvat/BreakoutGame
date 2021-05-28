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

        SpringLayout layout = new SpringLayout();
        configureInitButtonsPanel(layout);
        setLayout(layout);
        revalidate();
        repaint();
    }

    private void configureInitButtonsPanel(SpringLayout layout) {
        configureStartGameButton();
        layout.putConstraint(SpringLayout.WEST, startButton, ConfigurationsFields.SCREEN_WIDTH.getValue() / 2 - 150, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, startButton, 50, SpringLayout.NORTH, this);
        startButton.setPreferredSize(new Dimension(300, 30));
        add(startButton);


        configureExitGameButton();
        layout.putConstraint(SpringLayout.WEST, aboutButton, ConfigurationsFields.SCREEN_WIDTH.getValue() / 2 - 150, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, aboutButton, 100, SpringLayout.NORTH, this);
        aboutButton.setPreferredSize(new Dimension(300, 30));
        add(aboutButton);

        configureAboutButton();
        layout.putConstraint(SpringLayout.WEST, exitButton, ConfigurationsFields.SCREEN_WIDTH.getValue() / 2 - 150, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, exitButton, 150, SpringLayout.NORTH, this);
        exitButton.setPreferredSize(new Dimension(300, 30));
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
