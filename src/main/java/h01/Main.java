package h01;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import fopbot.RobotFamily;
import fopbot.World;

/**
 * Main entry point in executing the program.
 */
public class Main {

    public static Timer keyTimer = new Timer();
    public static Timer ghostTimer = new Timer();
    public static Timer gameTimer = new Timer();
    public static int totalCoins = 1;

    private static HashMap<Integer, Boolean> keysPressed;

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        keysPressed = new HashMap<>();
        for (int k = KeyEvent.VK_LEFT; k <= KeyEvent.VK_DOWN; k++) {
            keysPressed.put(k, false);
        }

        System.out.println("Hello World!");
        World.setSize(9, 9);
        World.setVisible(true);
        World.setDelay(0);

        World.getGlobalWorld().getGuiPanel().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                for (int k = KeyEvent.VK_LEFT; k <= KeyEvent.VK_DOWN; k++) {
                    if (e.getKeyCode() == k) {
                        keysPressed.put(k, true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                for (int k = KeyEvent.VK_LEFT; k <= KeyEvent.VK_DOWN; k++) {
                    if (e.getKeyCode() == k) {
                        keysPressed.put(k, false);
                    }
                }
            }
        });

        World.getGlobalWorld().getGuiPanel().setFocusable(true);
        World.getGlobalWorld().getGuiPanel().requestFocusInWindow();

        // Aus irgendeinem Grund funktioniert das hier nicht. Meckert mit pngs. Mit svgs
        // kein Meckern aber machen tut er trotzdem nix.

        World.getGlobalWorld().setAndLoadRobotImagesById(RobotFamily.SQUARE_YELLOW.getIdentifier(),
                Main.class.getResourceAsStream("/pacman.svg"),
                Main.class.getResourceAsStream("/pacman.svg"), 270, 270);
        World.getGlobalWorld().setAndLoadRobotImagesById(
                RobotFamily.SQUARE_BLUE.getIdentifier(),
                Main.class.getResourceAsStream("/ghost_blue.svg"),
                Main.class.getResourceAsStream("/ghost_blue.svg"), 0, 0);

        World.getGlobalWorld().setAndLoadRobotImagesById(RobotFamily.SQUARE_ORANGE.getIdentifier(),
                Main.class.getResourceAsStream("/ghost_orange.svg"),
                Main.class.getResourceAsStream("/ghost_orange.svg"), 0, 0);

        World.getGlobalWorld().setAndLoadRobotImagesById(RobotFamily.SQUARE_RED.getIdentifier(),
                Main.class.getResourceAsStream("/ghost_red.svg"),
                Main.class.getResourceAsStream("/ghost_red.svg"), 0, 0);

        World.getGlobalWorld().setAndLoadRobotImagesById(RobotFamily.SQUARE_PURPLE.getIdentifier(),
                Main.class.getResourceAsStream("/ghost_pink.svg"),
                Main.class.getResourceAsStream("/ghost_pink.svg"), 0, 0);

        createBoard();

        Pacman pacman = new Pacman(4, 3);

        BlueGhost blue = new BlueGhost(4, 4);
        OrangeGhost orange = new OrangeGhost(4, 4);
        PinkGhost pink = new PinkGhost(4, 4);
        RedGhost red = new RedGhost(4, 4, pacman);

        keyTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int keyPressed = -1;
                for (int k = KeyEvent.VK_LEFT; k <= KeyEvent.VK_DOWN; k++) {
                    if (keysPressed.get(k)) {
                        // key is pressed
                        if (keyPressed == -1) {
                            // first key found
                            keyPressed = k;
                        } else {
                            // multiple keys found
                            keyPressed = -1;
                            break;
                        }
                    }
                }

                if (keyPressed == -1) {
                    pacman.handleKeyInput(keyPressed);
                } else {
                    pacman.handleKeyInput(keyPressed - KeyEvent.VK_LEFT);
                    // KeyEvent.VK_LEFT = 0
                    // KeyEvent.VK_UP = 1
                    // KeyEvent.VK_RIGHT = 2
                    // KeyEvent.VK_DOWN = 3
                }
            }
        }, 0, 100);

        ghostTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                orange.doMove();
                blue.doMove();
                pink.doMove();
                red.doMove();
            }
        }, 0, 300);

        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                boolean gameDone = false;
                if (pacman.getX() == orange.getX() && pacman.getY() == orange.getY()) {
                    gameDone = true;
                }

                if (pacman.getX() == blue.getX() && pacman.getY() == blue.getY()) {
                    gameDone = true;
                }

                if (pacman.getX() == pink.getX() && pacman.getY() == pink.getY()) {
                    gameDone = true;
                }

                if (pacman.getX() == red.getX() && pacman.getY() == red.getY()) {
                    gameDone = true;
                }

                if (gameDone || pacman.getNumberOfCoins() == totalCoins) {
                    keyTimer.cancel();
                    ghostTimer.cancel();
                    gameTimer.cancel();
                }

            }
        }, 0, 10);

    }

    private static void createBoard() {
        int ls = 0;

        World.placeVerticalWall(0, 3);
        World.placeVerticalWall(0, 4);
        World.placeVerticalWall(0, 5);

        World.placeVerticalWall(1, 2);
        World.placeVerticalWall(1, 4);
        World.placeVerticalWall(1, 6);

        World.placeVerticalWall(2, 2);
        World.placeVerticalWall(2, 3);
        World.placeVerticalWall(2, 5);
        World.placeVerticalWall(2, 6);

        World.placeVerticalWall(3, 4);
        World.placeVerticalWall(3, 7);

        World.placeVerticalWall(4, 4);
        World.placeVerticalWall(4, 1);

        World.placeVerticalWall(5, 2);
        World.placeVerticalWall(5, 3);
        World.placeVerticalWall(5, 5);
        World.placeVerticalWall(5, 6);

        World.placeVerticalWall(6, 2);
        World.placeVerticalWall(6, 4);
        World.placeVerticalWall(6, 6);

        World.placeVerticalWall(7, 3);
        World.placeVerticalWall(7, 4);
        World.placeVerticalWall(7, 5);

        World.placeHorizontalWall(1, 0);
        World.placeHorizontalWall(1, 1);
        World.placeHorizontalWall(1, 6);
        World.placeHorizontalWall(1, 7);

        World.placeHorizontalWall(2, 0);
        World.placeHorizontalWall(2, 7);

        World.placeHorizontalWall(3, 0);
        World.placeHorizontalWall(3, 2);
        World.placeHorizontalWall(3, 5);

        World.placeHorizontalWall(4, 1);
        World.placeHorizontalWall(4, 3);
        World.placeHorizontalWall(4, 6);

        World.placeHorizontalWall(5, 2);
        World.placeHorizontalWall(5, 5);
        World.placeHorizontalWall(5, 7);

        World.placeHorizontalWall(6, 0);
        World.placeHorizontalWall(6, 7);

        World.placeHorizontalWall(7, 0);
        World.placeHorizontalWall(7, 1);
        World.placeHorizontalWall(7, 6);
        World.placeHorizontalWall(7, 7);
    }
}
