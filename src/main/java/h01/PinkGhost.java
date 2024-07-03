package h01;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;
import h01.template.Util;

public class PinkGhost extends Robot implements Ghost, TickBased {
    public PinkGhost(int x, int y) {
        super(x, y, Families.GHOST_PINK);
    }

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
