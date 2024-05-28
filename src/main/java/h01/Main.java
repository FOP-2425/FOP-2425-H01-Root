package h01;

import fopbot.Robot;
import fopbot.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        World.setSize(9, 9);
        World.setVisible(true);
        World.setDelay(0);
        World.getGlobalWorld().getGuiPanel().setFocusable(true);
        World.getGlobalWorld().getGuiPanel().requestFocusInWindow();


/*  Aus irgendeinem Grund funktioniert das hier nicht. Meckert mit pngs. Mit svgs kein Meckern aber machen tut er trotzdem nix.
        try {

            World.getGlobalWorld().setAndLoadRobotImages(Pacman.class, new FileInputStream(new File("res/pacman.png")),
                new FileInputStream(new File("res/pacman.png")), 270, 270);
            World.getGlobalWorld().setAndLoadRobotImages(BlueGhost.class,
                new FileInputStream(new File("res/ghost_blue.png")),
                new FileInputStream(new File("res/ghost_blue.png")), 0, 0);

            World.getGlobalWorld().setAndLoadRobotImages(OrangeGhost.class,
                new FileInputStream(new File("res/ghost_orange.png")),
                new FileInputStream(new File("res/ghost_orange.png")), 0, 0);

            World.getGlobalWorld().setAndLoadRobotImages(RedGhost.class,
                new FileInputStream(new File("res/ghost_red.png")),
                new FileInputStream(new File("res/ghost_red.png")), 0, 0);

            World.getGlobalWorld().setAndLoadRobotImages(PinkGhost.class,
                new FileInputStream(new File("res/ghost_pink.png")),
                new FileInputStream(new File("res/ghost_pink.png")), 0, 0);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
 */
        createBoard();

        Robot Pacman = new Pacman(4,4);
    }

    private static void createBoard() {
        int ls = 0;

        World.placeVerticalWall(0,3);
        World.placeVerticalWall(0,4);
        World.placeVerticalWall(0,5);

        World.placeVerticalWall(1,2);
        World.placeVerticalWall(1,4);
        World.placeVerticalWall(1,6);

        World.placeVerticalWall(2,2);
        World.placeVerticalWall(2,3);
        World.placeVerticalWall(2,5);
        World.placeVerticalWall(2,6);

        World.placeVerticalWall(3,4);
        World.placeVerticalWall(3,7);

        World.placeVerticalWall(4,4);
        World.placeVerticalWall(4,1);

        World.placeVerticalWall(5,2);
        World.placeVerticalWall(5,3);
        World.placeVerticalWall(5,5);
        World.placeVerticalWall(5,6);

        World.placeVerticalWall(6,2);
        World.placeVerticalWall(6,4);
        World.placeVerticalWall(6,6);

        World.placeVerticalWall(7,3);
        World.placeVerticalWall(7,4);
        World.placeVerticalWall(7,5);


        World.placeHorizontalWall(1,0);
        World.placeHorizontalWall(1,1);
        World.placeHorizontalWall(1,6);
        World.placeHorizontalWall(1,7);

        World.placeHorizontalWall(2,0);
        World.placeHorizontalWall(2,7);

        World.placeHorizontalWall(3,0);
        World.placeHorizontalWall(3,2);
        World.placeHorizontalWall(3,5);

        World.placeHorizontalWall(4,1);
        World.placeHorizontalWall(4,3);
        World.placeHorizontalWall(4,6);

        World.placeHorizontalWall(5,2);
        World.placeHorizontalWall(5,5);
        World.placeHorizontalWall(5,7);

        World.placeHorizontalWall(6,0);
        World.placeHorizontalWall(6,7);

        World.placeHorizontalWall(7,0);
        World.placeHorizontalWall(7,1);
        World.placeHorizontalWall(7,6);
        World.placeHorizontalWall(7,7);
    }
}
