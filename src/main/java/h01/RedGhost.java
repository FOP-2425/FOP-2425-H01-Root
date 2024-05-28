package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;

public class RedGhost extends Robot {
    private Pacman chased;

    public RedGhost(int x, int y, Pacman chased) {
        super(x,y, RobotFamily.SQUARE_RED);
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
