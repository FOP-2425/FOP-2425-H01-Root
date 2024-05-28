package h01;

import fopbot.Robot;
import fopbot.RobotFamily;

public class BlueGhost extends Robot {
    private boolean leftTurn = false;

    public BlueGhost(int x, int y) {
        super(x,y,RobotFamily.SQUARE_BLUE);
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
