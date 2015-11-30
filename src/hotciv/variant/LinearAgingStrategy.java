package hotciv.variant;

import hotciv.framework.AgingStrategy;

/**
 * Created by simon on 23-11-2015.
 */
public class LinearAgingStrategy implements AgingStrategy {
    @Override
    public int calculateAge(int ageSoFar) {
        ageSoFar += 100;
        return ageSoFar;
    }
}
