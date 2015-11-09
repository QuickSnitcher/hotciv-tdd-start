package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by Liam on 09-11-2015.
 */
public class UnitImpl implements Unit {
    private String type;
    public UnitImpl(String type){
        this.type = type;
    }
    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
