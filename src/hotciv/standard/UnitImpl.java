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

    private int defensiveStr = 3;



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

    public void setMoveCount(int updateCount){
        moveCount = updateCount;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }


    public void setDefensiveStrength(){
        defensiveStr = 6;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStr;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
