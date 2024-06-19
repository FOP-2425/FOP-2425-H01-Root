package h01;

import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Ghost;
import h01.template.TickBased;

public class OrangeGhost extends Robot implements Ghost, TickBased {
    public OrangeGhost(int x, int y) {
        super(x,y, RobotFamily.SQUARE_ORANGE);
    }

    private boolean leftTurnNext = false;

    public void doMove() {
        if(isFrontClear()) {
            move();
            return;
        }
        else {
            while (!isFrontClear()) {
                if (leftTurnNext) {
                    turnLeft();
                } else {
                    turnLeft();
                    turnLeft();
                    turnLeft();
                }
            }
        }
        leftTurnNext = !leftTurnNext;
    }
}
