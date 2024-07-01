package h01;

import fopbot.Direction;
import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;
import h01.template.Util;

public class RedGhost extends Robot implements Ghost, TickBased {
    private final Robot chased;

    public RedGhost(int x, int y, Robot chased) {
        super(x,y, Families.GHOST_RED);
        this.chased = chased;
    }

    public void doMove() {
        turnDirection(Util.furthestDirection(chased, this));
        while (!isFrontClear()) turnLeft();
        move();
    }

    private void turnDirection(Direction dir) {
        while (getDirection() != dir)
            turnLeft();
    }

}
