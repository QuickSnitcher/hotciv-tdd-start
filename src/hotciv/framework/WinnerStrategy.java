package hotciv.framework;

import hotciv.standard.GameImpl;

/**
 * Created by simon on 23-11-2015.
 */
public interface WinnerStrategy {

    public Player checkWinner(Game game);

    public void increaseAttackCounter(Game game);
}
