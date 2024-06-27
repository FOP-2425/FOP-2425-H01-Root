package h01.template;


import fopbot.Robot;
import fopbot.World;

import java.util.ArrayList;

/**
 * A {@link Pellet} is a {@link Robot} that represents a pellet in the game.
 */
public class Pellet extends Robot {
    public Pellet(int x, int y) {
        super(x, y, Families.PELLET);
        World.putCoins(x,y,1);
        allPellets.add(this);
    }

    public static ArrayList<Pellet> allPellets = new ArrayList<>();

    public static int remainingPellets() {
        return allPellets.size();
    }
}
