package hotciv.variant;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;

/**
 * Created by simon on 06-12-2015.
 */
public class FirstToThree implements WinnerStrategy {

    private int playerBlueAttacks = 0;
    private int playerRedAttacks = 0;

    @Override
    public Player checkWinner(Game game) {
        if (game.getPlayerInTurn() == Player.RED){
            playerRedAttacks++;
        }
        else if (game.getPlayerInTurn() == Player.BLUE){
            playerBlueAttacks++;
        }
        if (playerBlueAttacks == 3){
            return Player.BLUE;
        }
        else if (playerRedAttacks == 3){
            return Player.RED;
        }
        return null;
    }
}
