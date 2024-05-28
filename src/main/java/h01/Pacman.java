package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;

import static fopbot.Direction.*;

public class Pacman extends Robot {
    public Pacman(int x, int y) {
        super(x,y, RobotFamily.SQUARE_YELLOW);
    }

    public void handleKeyInput(int k) {
        if (k < 0 || k > 3) {
            return;
        }

        if(k == 0) {
            turnDirection(LEFT);
        } else if (k == 1) {
            turnDirection(UP);
        } else if (k == 2) {
            turnDirection(RIGHT);
        } else {
            turnDirection(DOWN);
        }

        if(isFrontClear()) move();
    }

    private void turnDirection(Direction dir) {
        while (getDirection() != dir)
            turnLeft();
    }
}
