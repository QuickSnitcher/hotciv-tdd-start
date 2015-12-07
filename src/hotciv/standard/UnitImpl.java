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

    public boolean checkFortify(){
        return fortified;
    }

    public void setFortify(){
        if (fortified == false){

            fortified = true;
        }
        else{fortified = false;

        }
    }

    @Override
    public int getDefensiveStrength() {
        if (this.getTypeString().equals(GameConstants.ARCHER)){
            if (fortified){
                return 6;
            }
            return 3;
        }
        else if (this.getTypeString().equals(GameConstants.LEGION)){
            return 2;
        }
        else{
            return 0;
        }

    }

    @Override
    public int getAttackingStrength() {
        if (this.getTypeString().equals(GameConstants.ARCHER)){
            return 2;
        }
        else if (this.getTypeString().equals(GameConstants.LEGION)){
            return 4;
        }
        else {
            return 0;
        }
    }
}
