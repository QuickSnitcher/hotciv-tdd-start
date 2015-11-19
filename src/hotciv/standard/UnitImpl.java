package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by Liam on 09-11-2015.
 */
public class UnitImpl implements Unit {
    private String type;
    private Player owner;
    private int moveCount = 1;

    public UnitImpl(String type, Player owner ){
        this.type = type;
        this.owner = owner;
    }
    @Override
    public String getTypeString() {

        return type;
    }

    @Override
    public Player getOwner() {

        return owner;
    }

    public void setMoveCount(int updateMove){
        moveCount = updateMove;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
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
