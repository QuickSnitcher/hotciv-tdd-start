package hotciv.variant;

import hotciv.framework.*;

/**
 * Created by simon on 19-12-2015.
 */
public class ThetaGameFactory implements GameImplFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new ChariotAction(new YesActionStrategy());
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
        return new attackerAlwaysWins();
    }

    @Override
    public UnitAvailableStrategy unitAvailableStrategy() {
        return new ChariotAvailable(new NoChariotAvailable());
    }
}
