package hotciv.variant;

import hotciv.framework.*;

/**
 * Created by Liam on 17-12-2015.
 */
public class DeltaGameFactory implements GameImplFactory {
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
        return new NoActionsStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new LinearAgingStrategy();
    }

    @Override
    public LayoutStrategy createLayoutStrategy() {
        return new CustomLayoutStrategy(gameLayout);
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return null;
    }

    @Override
    public CombatStrategy createCombatStrategy() {
        return null;
    }
}
