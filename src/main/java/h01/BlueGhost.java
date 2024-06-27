package h01;

import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;

public class BlueGhost extends Robot implements Ghost, TickBased {

    public BlueGhost(int x, int y) {
        super(x,y, Families.GHOST_BLUE);
    }

    public void doMove() {
        turnRight();
        while(!isFrontClear()) {
            turnLeft();
        }
        move();
    }

    private void turnRight() {
        for (int i = 0; i < 3; i++) {
            turnLeft();
        }
    }
}
