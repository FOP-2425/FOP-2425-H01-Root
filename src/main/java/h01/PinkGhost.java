package h01;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;
import h01.template.Util;

/**
 * The {@link PinkGhost} is a {@link Robot} that looks like a pink ghost.
 * It tries to move in a random direction.
 */
public class PinkGhost extends Robot implements Ghost, TickBased {
    /**
     * Creates a new {@link PinkGhost} at the given position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public PinkGhost(int x, int y) {
        super(x, y, Families.GHOST_PINK);
    }

    /**
     * Finda how in many directions the ghost can move and then turns a random
     * amount of times to the left.
     * Then moves in the first direction that is clear.
     */
    @Override
    @StudentImplementationRequired
    public void doMove() {
        int freeLanes = 0;
        for (int i = 0; i < 4; i++) {
            turnLeft();
            if (isFrontClear()) {
                freeLanes++;
            }
        }

        int rand = Util.getRandomInteger(0, freeLanes);
        for (int i = 0; i < rand; i++) {
            turnLeft();
        }
        while (!isFrontClear()) {
            turnLeft();
        }
        move();
    }
}
