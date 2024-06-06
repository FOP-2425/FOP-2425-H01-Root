package h01;

import h01.template.GameControllerTemplate;

public class GameController extends GameControllerTemplate {
    public GameController() {
        setup();
    }
    @Override
    public boolean checkWinCondition() {
        return pacman.getNumberOfCoins() == totalCoins;
    }
}
