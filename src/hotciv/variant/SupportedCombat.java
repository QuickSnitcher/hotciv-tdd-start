package hotciv.variant;

import hotciv.framework.CombatStrategy;
import hotciv.framework.DieRollStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.Utility;

/**
 * Created by simon on 03-12-2015.
 */
public class SupportedCombat implements CombatStrategy {
    private int terrain;
    private int support;
    private int unitStr;
    private int attackingStrength;
    private int defensiveStrength;
    private DieRollStrategy dieRollStrategy1;
    private DieRollStrategy dieRollStrategy2;
    private int attackingResult;
    private int defensiveResult;

    public SupportedCombat(DieRollStrategy dieRollStrategy1, DieRollStrategy dieRollStrategy2){
        this.dieRollStrategy1 = dieRollStrategy1;
        this.dieRollStrategy2 = dieRollStrategy2;
    }

    @Override
    public boolean attackerWins(Game game, Position attacker, Position defender) {
        unitStr = game.getUnitAt(attacker).getAttackingStrength();
        terrain = Utility.getTerrainFactor(game, attacker);
        support = Utility.getFriendlySupport(game, attacker, game.getUnitAt(attacker).getOwner());
        attackingStrength = (unitStr + support) * terrain;
        attackingResult = attackingStrength * dieRollStrategy1.dieRoll();
        unitStr = game.getUnitAt(defender).getDefensiveStrength();
        terrain = Utility.getTerrainFactor(game, defender);
        support = Utility.getFriendlySupport(game, defender, game.getUnitAt(defender).getOwner());
        defensiveStrength = (unitStr + support) * terrain;
        defensiveResult = defensiveStrength * dieRollStrategy2.dieRoll();
        if (attackingResult > defensiveResult){
            return true;
        }
        else {
            return false;
        }
    }

    public int getAttackingResult(){
        return attackingResult;
    }

    public int getDefensiveResult() {
        return defensiveResult;
    }

    public int getAttackingStrength(){
        return attackingStrength;
    }
    public int getDefensiveStrength(){
        return defensiveStrength;
    }
    public int getUnitStr(){
        return unitStr;
    }
    public int getTerrain(){
        return terrain;
    }
    public int getSupport(){
        return support;
    }
}
