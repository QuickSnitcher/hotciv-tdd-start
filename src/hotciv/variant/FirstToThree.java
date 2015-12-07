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
    private Player winner = null;

    @Override
    public Player checkWinner(Game game) {
        return winner;
    }

    public void increaseAttackCounter(Game game){
        if (game.getPlayerInTurn().equals(Player.RED)){
            playerRedAttacks++;
        }
        if (game.getPlayerInTurn().equals(Player.BLUE)){
            playerBlueAttacks++;
        }
        if (playerBlueAttacks >= 3){
            winner = Player.BLUE;

        }
        if (playerRedAttacks >= 3){
            winner = Player.RED;

        }
    }

    public int getPlayerRedAttacks(){
        return playerRedAttacks;
    }
}
