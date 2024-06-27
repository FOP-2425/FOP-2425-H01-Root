package h01;

import h01.template.GameControllerTemplate;
import h01.template.Pellet;

public class GameController extends GameControllerTemplate {
    public GameController() {
        setup();
    }
    @Override
    public boolean checkWinCondition() {
        return Pellet.remainingPellets() == 0;
    }
}
