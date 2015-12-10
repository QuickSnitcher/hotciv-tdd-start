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


    @Override
    public Player checkWinner(Game game) {
        int blue = 0;
        int red = 0;
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

        return null;
    }}




    @Override
    public void increaseAttackCounter(Game game){

    }
}
