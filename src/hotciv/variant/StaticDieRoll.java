package hotciv.variant;

import hotciv.framework.DieRollStrategy;

/**
 * Created by simon on 03-12-2015.
 */
public class StaticDieRoll implements DieRollStrategy {
    private int roll;

    public StaticDieRoll(int roll){
        this.roll = roll;
    }

    @Override
    public int dieRoll() {
        return roll;
    }
}
