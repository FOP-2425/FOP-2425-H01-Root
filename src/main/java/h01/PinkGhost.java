package h01;

import fopbot.Robot;
import fopbot.RobotFamily;

public class PinkGhost extends Robot {
    public PinkGhost(int x, int y) {
        super(x,y, RobotFamily.SQUARE_PURPLE);
    }

    public void doMove() {
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
