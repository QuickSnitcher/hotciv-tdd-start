package hotciv.variant;

import hotciv.framework.*;

/**
 * Created by Liam on 17-12-2015.
 */
public class GammeGameFactory implements GameImplFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new YesActionStrategy();
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
    public WinnerStrategy createWinnerStrategy() {
        return new RedWinnerStrategy();
    }

    @Override
    public CombatStrategy createCombatStrategy() {
        return null;
    }
}
