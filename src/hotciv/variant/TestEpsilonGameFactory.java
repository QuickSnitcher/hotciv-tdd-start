package hotciv.variant;

import hotciv.framework.CombatStrategy;

/**
 * Created by Liam on 17-12-2015.
 */
public class TestEpsilonGameFactory extends EpsilonGameFactory {

    private int first;
    private int Second;
    private final SupportedCombat supportedCombat;

    public TestEpsilonGameFactory(int first, int second) {

        this.first = first;
        this.Second = second;

        supportedCombat = new SupportedCombat(new StaticDieRoll(first), new StaticDieRoll(Second));
    }

    @Override
    public CombatStrategy createCombatStrategy() {

        return supportedCombat;
    }
}
