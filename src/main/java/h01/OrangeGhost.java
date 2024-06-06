package h01;

import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Ghost;
import h01.template.TickBased;

public class OrangeGhost extends Robot implements Ghost, TickBased {
    private Pacman chased;
    public OrangeGhost(int x, int y) {
        super(x,y, RobotFamily.SQUARE_ORANGE);
    }

    public void doMove() {
        int rand = Util.getRandomInteger(0,9);
        if(rand < 3) doRandomMove();
        else {
            while(!isFrontClear()) {
                turnLeft();
            }
            move();
        }

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
