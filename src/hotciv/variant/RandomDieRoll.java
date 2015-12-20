package hotciv.variant;

import hotciv.framework.DieRollStrategy;

import java.util.Random;

/**
 * Created by simon on 17-12-2015.
 */
public class RandomDieRoll implements DieRollStrategy {

    @Override
    public int dieRoll() {
        return new Random().nextInt(6);
    }
}
