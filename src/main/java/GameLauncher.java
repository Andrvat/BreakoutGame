import javax.swing.JFrame;

public class GameLauncher extends JFrame {

    public GameLauncher() {
        initUI();
    }

    private void initUI() {

        add(new Board());
        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        var game = new GameLauncher();
        game.setVisible(true);
    }
}
