package h01.template;

import fopbot.Robot;
import fopbot.RobotFamily;
import fopbot.World;
import h01.*;

import java.awt.Color;
import java.util.*;

/**
 * A {@link GameControllerTemplate} controls the game loop and the {@link Robot}s and checks the win condition.
 */
public abstract class GameControllerTemplate {
    /**
     * The {@link Timer} that controls the game loop.
     */
    private final Timer gameLoopTimer = new Timer();

    /**
     * The {@link GameInputHandler} that handles the input of the user.
     */
    private final GameInputHandler inputHandler = new GameInputHandler();

    /**
     * The total umber of coins in the map.
     */
    protected int totalCoins = 1;

    /**
     * The {@link Robot}s that are controlled by the {@link GameControllerTemplate}.
     */
    protected final ArrayList<Robot> robots = new ArrayList<>();

    /**
     * The {@link Pacman} {@link Robot}.
     */
    protected Robot pacman;

    /**
     * The {@link BlueGhost} {@link Robot}.
     */
    protected Robot blue;

    /**
     * The {@link OrangeGhost} {@link Robot}.
     */
    protected Robot orange;

    /**
     * The {@link PinkGhost} {@link Robot}.
     */
    protected Robot pink;

    /**
     * The {@link RedGhost} {@link Robot}.
     */
    protected Robot red;

    /**
     * A {@link Map} that maps a {@link Robot} to the amount of ticks that have passed since the last tick action.
     */
    private final Map<Robot, Integer> robotTicks = new HashMap<>();

    /**
     * The {@link TimerTask} that is executed every tick.
     */
    private final TimerTask gameLoopTask = new TimerTask() {
        @Override
        public void run() {
            for (final Robot robot : GameControllerTemplate.this.robots) {
                if (!(robot instanceof final TickBased tb)) {
                    continue;
                }
                if (!GameControllerTemplate.this.robotTicks.containsKey(robot)) {
                    GameControllerTemplate.this.robotTicks.put(robot, 0);
                }
                if (GameControllerTemplate.this.robotTicks.get(robot) < tb.getUpdateDelay()) {
                    GameControllerTemplate.this.robotTicks.put(robot, GameControllerTemplate.this.robotTicks.get(robot) + 1);
                    continue;
                }
                GameControllerTemplate.this.robotTicks.put(robot, 0);
                // do tick action
                if (robot instanceof final Pacman r) {
                    r.handleKeyInput(
                        GameControllerTemplate.this.inputHandler.getDirection()
                    );
                } else if (robot instanceof final Ghost r) {
                    r.doMove();
                }
            }
            // check win condition
            if(checkWinCondition()) stopGame(true);
            if(checkLoseCondition()) stopGame(false);
        }
    };

    /**
     * Gets the {@link h01.Pacman} {@link Robot}.
     *
     * @return the {@link h01.Pacman} {@link Robot}
     */
    public Robot getPacman() {
        return pacman;
    }

    /**
     * Gets the {@link BlueGhost} {@link Robot}.
     *
     * @return the {@link BlueGhost} {@link Robot}
     */
    public Robot getBlue() {
        return blue;
    }

    /**
     * Gets the {@link OrangeGhost} {@link Robot}.
     *
     * @return the {@link OrangeGhost} {@link Robot}
     */
    public Robot getOrange() {
        return orange;
    }

    /**
     * Gets the {@link PinkGhost} {@link Robot}.
     *
     * @return the {@link PinkGhost} {@link Robot}
     */
    public Robot getPink() {
        return pink;
    }

    /**
     * Gets the {@link RedGhost} {@link Robot}.
     *
     * @return the {@link RedGhost} {@link Robot}
     */
    public Robot getRed() {
        return red;
    }

    /**
     * Starts the game loop.
     */
    public void startGame() {
        System.out.println("Starting game...");
        this.gameLoopTimer.scheduleAtFixedRate(this.gameLoopTask, 0, 200);
    }

    /**
     * Stops the game loop.
     */
    public void stopGame(boolean won) {
        this.gameLoopTimer.cancel();

        for (int i = 0; i < World.getHeight(); i++) {
            for (int j = 0; j < World.getWidth(); j++) {
                World.getGlobalWorld().setFieldColor(i, j, won?Color.GREEN:Color.RED);
            }
        }
    }

    /**
     * Sets up the game.
     */
    protected void setup() {
        setupWorld();
        loadImages();
        setupRobots();
        this.inputHandler.install();
    }

    private void loadImages() {
        World.getGlobalWorld().setAndLoadRobotImagesById(RobotFamily.SQUARE_YELLOW.getIdentifier(),
            Main.class.getResourceAsStream("/pacman.svg"),
            Main.class.getResourceAsStream("/pacman.svg"), 270, 270);

        World.getGlobalWorld().setAndLoadRobotImagesById(RobotFamily.SQUARE_BLUE.getIdentifier(),
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


    }

    /**
     * Initializes the {@link World} and adds the {@link Robot}s to it.
     */
    public void setupWorld() {
        World.setSize(9, 9);
        World.setDelay(0);
        World.setVisible(true);

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

        World.putCoins(0,0,1);

        World.getGlobalWorld().setFieldColor(4, 4, Color.YELLOW);
    }

    /**
     * Adds the {@link Robot}s to the {@link World}.
     */
    public void setupRobots() {
        this.robots.add(pacman = new Pacman(4,3));
        this.robots.add(blue = new BlueGhost(4,4));
        this.robots.add(orange = new OrangeGhost(4,4));
        this.robots.add(pink = new PinkGhost(4,4));
        this.robots.add(red = new RedGhost(4,4, pacman));
    }

    /**
     * Checks the win condition.
     *
     * @return Returns true if the game is won.
     */
    public abstract boolean checkWinCondition();

    /**
     * Checks the lose condition.
     *
     * @return Returns true if the game is lost.
     */
    public boolean checkLoseCondition() {
        boolean gameDone = pacman.getX() == orange.getX() && pacman.getY() == orange.getY();

        if (pacman.getX() == blue.getX() && pacman.getY() == blue.getY()) {
            gameDone = true;
        }

        if (pacman.getX() == pink.getX() && pacman.getY() == pink.getY()) {
            gameDone = true;
        }

        if (pacman.getX() == red.getX() && pacman.getY() == red.getY()) {
            gameDone = true;
        }
        return gameDone;
    }
}
