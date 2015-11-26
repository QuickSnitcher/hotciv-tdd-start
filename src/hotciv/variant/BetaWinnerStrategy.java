package hotciv.variant;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinnerStrategy;

/**
 * Created by simon on 23-11-2015.
 */
public class BetaWinnerStrategy implements WinnerStrategy{
    @Override
    public Player checkWinner(Game game) {
        if (game.getCityAt(new Position(1,1)).getOwner().equals(game.getCityAt(new Position(4,1)).getOwner())){
            return game.getCityAt(new Position(1,1)).getOwner();
        }else{
        return null;
    }}
}
