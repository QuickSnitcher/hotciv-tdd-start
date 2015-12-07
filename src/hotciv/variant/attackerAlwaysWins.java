package hotciv.variant;

import hotciv.framework.CombatStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

/**
 * Created by simon on 03-12-2015.
 */
public class attackerAlwaysWins implements CombatStrategy {
    @Override
    public boolean attackerWins(Game game, Position attacker, Position defender) {
        return true;
    }
}
