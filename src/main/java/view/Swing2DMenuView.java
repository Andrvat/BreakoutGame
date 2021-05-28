package view;

import launcher.GameLauncher;

import utilities.ConfigurationsFields;
import utilities.PathsDistributor;

import javax.swing.*;
import java.awt.*;

public class Swing2DMenuView extends JPanel {
    private final JButton startButton = new JButton("Start");

    private final JButton exitButton = new JButton("Exit");

    private final JButton aboutButton = new JButton("About");

    private final Image background;

    private final JFrame parentFrame;

    private final SpringLayout layout;

    public Swing2DMenuView(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        background = new ImageIcon(PathsDistributor.getPathToGameElementFromContentRoot("menuBackground")).getImage();
        layout = new SpringLayout();

        configureInitButtonsPanel();
        setLayout(layout);
        revalidate();
        repaint();
    }

    private void configureInitButtonsPanel() {
        configureStartGameButton();
        specifiedPutConstraint(startButton, 50);
        add(startButton);


        configureExitGameButton();
        specifiedPutConstraint(aboutButton, 100);
        add(aboutButton);

        configureAboutButton();
        specifiedPutConstraint(exitButton, 150);
        add(exitButton);
    }

    private void specifiedPutConstraint(JButton button, int yPad) {
        layout.putConstraint(SpringLayout.WEST, button, ConfigurationsFields.SCREEN_WIDTH.getValue() / 4,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, button, yPad, SpringLayout.NORTH, this);
        button.setPreferredSize(new Dimension(ConfigurationsFields.SCREEN_WIDTH.getValue() / 2, 30));
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
                "Andrew Valitov, Novosibirsk State University",
                "About",
                JOptionPane.INFORMATION_MESSAGE));
    }

    private void configureButtonView(JButton button) {
        Font buttonFont = new Font("Rockwell", Font.BOLD, 20);
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
