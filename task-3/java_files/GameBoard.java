import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int MAX_DOTS = 900;
    private final int RANDOM_POSITION = 29;
    private final int GAME_SPEED = 140;

    private final int[] xPositions = new int[MAX_DOTS];
    private final int[] yPositions = new int[MAX_DOTS];

    private int currentDots;
    private int appleX;
    private int appleY;

    private boolean movingLeft = false;
    private boolean movingRight = true;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean inGame = true;

    private Timer gameTimer;
    private Image snakeBody;
    private Image apple;
    private Image snakeHead;

    public GameBoard() {
        initializeBoard();
    }
    
    private void initializeBoard() {
        addKeyListener(new KeyInputAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        loadImages();
        startGame();
    }

    private void loadImages() {
        ImageIcon bodyIcon = new ImageIcon("src/resources/dot.png");
        snakeBody = bodyIcon.getImage();

        ImageIcon appleIcon = new ImageIcon("src/resources/apple.png");
        apple = appleIcon.getImage();

        ImageIcon headIcon = new ImageIcon("src/resources/head.png");
        snakeHead = headIcon.getImage();
    }

    private void startGame() {
        currentDots = 3;

        for (int i = 0; i < currentDots; i++) {
            xPositions[i] = 50 - i * DOT_SIZE;
            yPositions[i] = 50;
        }
        
        locateApple();

        gameTimer = new Timer(GAME_SPEED, this);
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }
    
    private void drawGame(Graphics g) {
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);

            for (int i = 0; i < currentDots; i++) {
                if (i == 0) {
                    g.drawImage(snakeHead, xPositions[i], yPositions[i], this);
                } else {
                    g.drawImage(snakeBody, xPositions[i], yPositions[i], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            displayGameOver(g);
        }        
    }

    private void displayGameOver(Graphics g) {
        String message = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (BOARD_WIDTH - metrics.stringWidth(message)) / 2, BOARD_HEIGHT / 2);
    }

    private void checkApple() {
        if ((xPositions[0] == appleX) && (yPositions[0] == appleY)) {
            currentDots++;
            locateApple();
        }
    }

    private void move() {
        for (int i = currentDots; i > 0; i--) {
            xPositions[i] = xPositions[i - 1];
            yPositions[i] = yPositions[i - 1];
        }

        if (movingLeft) {
            xPositions[0] -= DOT_SIZE;
        }
        if (movingRight) {
            xPositions[0] += DOT_SIZE;
        }
        if (movingUp) {
            yPositions[0] -= DOT_SIZE;
        }
        if (movingDown) {
            yPositions[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        for (int i = currentDots; i > 0; i--) {
            if ((i > 4) && (xPositions[0] == xPositions[i]) && (yPositions[0] == yPositions[i])) {
                inGame = false;
            }
        }

        if (yPositions[0] >= BOARD_HEIGHT || yPositions[0] < 0 || xPositions[0] >= BOARD_WIDTH || xPositions[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            gameTimer.stop();
        }
    }

    private void locateApple() {
        int randomPosition = (int) (Math.random() * RANDOM_POSITION);
        appleX = randomPosition * DOT_SIZE;

        randomPosition = (int) (Math.random() * RANDOM_POSITION);
        appleY = randomPosition * DOT_SIZE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    private class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!movingRight)) {
                movingLeft = true;
                movingUp = false;
                movingDown = false;
            }
            if ((key == KeyEvent.VK_RIGHT) && (!movingLeft)) {
                movingRight = true;
                movingUp = false;
                movingDown = false;
            }
            if ((key == KeyEvent.VK_UP) && (!movingDown)) {
                movingUp = true;
                movingRight = false;
                movingLeft = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!movingUp)) {
                movingDown = true;
                movingRight = false;
                movingLeft = false;
            }
        }
    }
}
