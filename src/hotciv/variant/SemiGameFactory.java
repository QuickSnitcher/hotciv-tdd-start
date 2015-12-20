package hotciv.variant;

import hotciv.framework.*;

/**
 * Created by simon on 17-12-2015.
 */
public class SemiGameFactory implements GameImplFactory {

    String[] gameLayout = new String[]{
            "...ppMppppp.....",
            "..phhppppfffpp..",
            ".pppppMppp...pp.",
            ".ppMMMpppp..pppp",
            "...pfppphhpppp..",
            ".pfpBfppppphhpp.",
            "...ppp..........",
            ".ppppp.ppphppM..",
            ".ppppp.pphpppf..",
            "pfffpppp.pffpppp",
            "pppppppp...ppppp",
            ".ppMMMpppp......",
            "..ppppppRfpppp..",
            "....ppppppppp...",
            "..ppphhpp.......",
            ".....ppppppppp..",
    };

    @Override
    public ActionStrategy createActionStrategy() {
        return new SettlerAction();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new VaryingAgingStrategy();
    }

    @Override
    public LayoutStrategy createLayoutStrategy() {
        return new CustomLayoutStrategy(gameLayout);
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new SuddenDeath();
    }

    @Override
    public CombatStrategy createCombatStrategy() {
        return new SupportedCombat(new RandomDieRoll(), new RandomDieRoll());
    }

    @Override
    public UnitAvailableStrategy unitAvailableStrategy() {
        return new NoChariotAvailable();
    }
}
