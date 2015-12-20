package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by Liam on 09-11-2015.
 */
public class UnitImpl implements Unit {
    private String type;
    private Player owner;
    private int moveCount = 1;
    private boolean fortified = false;
    private int def;
    private int att;
    private int defensiveStr = 3;


    public UnitImpl(String type, Player owner, int att, int def) {
        this.type = type;
        this.owner = owner;
        this.att = att;
        this.def = def;
    }

    @Override
    public String getTypeString() {

        return type;
    }

    @Override
    public Player getOwner() {

        return owner;
    }

    public void setMoveCount(int updateCount) {

        moveCount = updateCount;

    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    public boolean checkFortify() {
        return fortified;
    }

    public void setFortify() {
        if (fortified == false) {

            fortified = true;
        } else {
            fortified = false;

        }
    }

    @Override
    public int getDefensiveStrength() {
        if (fortified) {
            return def * 2;
        } else {
            return def;
        }

    }

    @Override
    public int getAttackingStrength() {
        return att;
    }
}
