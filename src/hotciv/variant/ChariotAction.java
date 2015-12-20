package hotciv.variant;

import hotciv.framework.ActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.ThetaConstants;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by simon on 17-12-2015.
 */
public class ChariotAction implements ActionStrategy {

    private ActionStrategy actionStrategy;

    public ChariotAction(ActionStrategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    @Override
    public void performAction(Position p, GameImpl game) {
        if (game.getUnitAt(p).getTypeString().equals(ThetaConstants.CHARIOT)){
            ((UnitImpl)game.getUnitAt(p)).setFortify();
        }else{
            actionStrategy.performAction(p, game);
        }
    }
}
