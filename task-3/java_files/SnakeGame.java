import java.awt.EventQueue;
import javax.swing.JFrame;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        initializeUI();
    }
    
    private void initializeUI() {
        add(new GameBoard());

        setResizable(false);
        pack();

        setTitle("Snake Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame gameFrame = new SnakeGame();
            gameFrame.setVisible(true);
        });
    }
}
