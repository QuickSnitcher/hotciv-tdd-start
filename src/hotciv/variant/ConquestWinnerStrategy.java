package hotciv.variant;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.CityImpl;

/**
 * Created by simon on 23-11-2015.
 */
public class ConquestWinnerStrategy implements WinnerStrategy{
    private int blue = 0;
    private int red = 0;

    @Override
    public Player checkWinner(Game game) {
        for (CityImpl city : game.getCityMap().values()) {
            if (city.getOwner() == Player.BLUE) {
                blue++;
            }
            if (city.getOwner() == Player.RED){
                red++;
            }
            }
        if (red == 0){
            return Player.BLUE;
        }
        if (blue == 0){
            return Player.RED;
        }
        else{
            red = 0;
            blue = 0;
        return null;
    }}

    @Override
    public void increaseAttackCounter(Game game){

    }
}
