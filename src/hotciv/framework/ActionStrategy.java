package hotciv.framework;

import hotciv.standard.GameImpl;

/**
 * Created by simon on 23-11-2015.
 */
public interface ActionStrategy {

    public void performAction(Position p, GameImpl game);


}
