package hotciv.variant;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by simon on 23-11-2015.
 */
public class GammaCivActionStrategy implements ActionStrategy {



    @Override
    public void performAction(Position p, GameImpl game) {

        if (game.getUnitAt(p).getTypeString() == GameConstants.SETTLER){
            Player cityOwner = game.getUnitAt(p).getOwner();
            game.getUnitMap().remove(p);
            game.getCityMap().put(p, new CityImpl(cityOwner));
        }
        else if (game.getUnitAt(p).getTypeString() == GameConstants.ARCHER){
            ((UnitImpl)game.getUnitAt(p)).setDefensiveStrength();
        }



        }
}
