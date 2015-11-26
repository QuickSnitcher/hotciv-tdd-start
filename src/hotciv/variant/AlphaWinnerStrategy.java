package hotciv.variant;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;

/**
 * Created by simon on 23-11-2015.
 */
public class AlphaWinnerStrategy implements WinnerStrategy {


    @Override
    public Player checkWinner(Game game) {
        if(game.getAge() >= -3000){
            return Player.RED;
        }
        return null;
    }
}
