package h01;

import fopbot.Direction;
import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;

public class RedGhost extends Robot implements Ghost, TickBased {
    private Robot chased;

    public RedGhost(int x, int y, Robot chased) {
        super(x,y, Families.GHOST_RED);
        this.chased = chased;
    }

    public void doMove() {
        int rand = Util.getRandomInteger(0,9);
        if(rand < 3) doRandomMove();
        else {
            turnDirection(Util.furthestDirection(chased, this));
            while (!isFrontClear()) turnLeft();
            move();
        }

        System.out.println("Red Ghost Update");
    }

    private void turnDirection(Direction dir) {
        while (getDirection() != dir)
            turnLeft();
    }

    public void doRandomMove() {
        int freeLanes = 0;
        for (int i = 0; i < 4; i++) {
            turnLeft();
            if(isFrontClear()) freeLanes++;
        }

        int rand = Util.getRandomInteger(0,freeLanes);
        for (int i = 0; i < rand; i++) {
            turnLeft();
        }
        while(!isFrontClear()) {
            turnLeft();
        }
        move();
    }
}
