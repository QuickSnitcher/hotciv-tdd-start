package hotciv.variant;

import hotciv.framework.ActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

/**
 * Created by simon on 17-12-2015.
 */
public class SettlerAction implements ActionStrategy {
    @Override
    public void performAction(Position p, GameImpl game) {
        if (game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)){
            Player cityOwner = game.getUnitAt(p).getOwner();
            game.getUnitMap().remove(p);
            game.getCityMap().put(p, new CityImpl(cityOwner));
        }
    }
}
