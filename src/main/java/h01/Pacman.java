package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Controllable;
import h01.template.TickBased;

import static fopbot.Direction.*;

public class Pacman extends Robot implements Controllable, TickBased {
    public Pacman(int x, int y) {
        super(x,y, RobotFamily.SQUARE_YELLOW);
    }

    public void handleKeyInput(int k) {

        System.out.println("Pacman Update");
        if (k < 0 || k > 3) {
            return;
        }

        if(k == 0) {
            turnDirection(UP);
        } else if (k == 1) {
            turnDirection(RIGHT);
        } else if (k == 2) {
            turnDirection(DOWN);
        } else {
            turnDirection(LEFT);
        }

        if(isFrontClear()) move();
        if(isOnACoin()) pickCoin();

    }

    private void turnDirection(Direction dir) {
        while (getDirection() != dir)
            turnLeft();
    }
}
