package h01;

import fopbot.Direction;
import fopbot.Robot;

import java.util.Random;

public class Util {

	private static Random rnd = new Random();

	/**
	 * @param min
	 * @param max
	 * @return a random integer between min and max (both inclusive)
	 */
	public static int getRandomInteger(int min, int max) {
		return min + rnd.nextInt(max - min + 1);
	}

    public static Direction furthestDirection(Robot pacman, Robot chaser) {
        int px = pacman.getX();
        int py = pacman.getY();
        int cx = chaser.getX();
        int cy = chaser.getY();

        int xdelta = Math.abs(px-cx);
        int ydelta = Math.abs(py-cy);

        if (xdelta > ydelta) {
            if (cx > px) return Direction.LEFT;
            else return Direction.RIGHT;
        } else {
            if (cy > py) return Direction.DOWN;
            else return Direction.UP;
        }
    }

}
