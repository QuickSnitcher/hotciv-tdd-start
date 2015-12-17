package hotciv.variant;

import hotciv.framework.*;

/**
 * Created by Liam on 17-12-2015.
 */
public class EpsilonGameFactory implements GameImplFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new NoActionsStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new LinearAgingStrategy();
    }

    @Override
    public LayoutStrategy createLayoutStrategy() {
        return new AlphaLayoutStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {return new FirstToThree();
    }

    @Override
    public CombatStrategy createCombatStrategy() {
        return new SupportedCombat(new RandomDieRoll(), new RandomDieRoll());
    }
}
