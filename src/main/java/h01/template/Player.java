package h01.template;

import fopbot.Robot;
import fopbot.RobotFamily;

import java.util.Optional;

public class Player extends Robot {
    public Player(int x, int y, RobotFamily family) {
        super(x, y, family);
    }

    public boolean isOnAPellet() {
        return isOnACoin();
    }

    public void pickPellet() {
        pickCoin();
        Pellet.allPellets.stream().filter(pellet -> (pellet.getX() == getX() && pellet.getY() == getY())).findFirst().ifPresent(pellet -> {pellet.turnOff(); Pellet.allPellets.remove(pellet);});
    }
}
