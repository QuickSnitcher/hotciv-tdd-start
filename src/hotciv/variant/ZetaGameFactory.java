package hotciv.variant;

import hotciv.framework.*;

/**
 * Created by Liam on 17-12-2015.
 */
public class ZetaGameFactory implements GameImplFactory {
    String[] world = new String[]{
            "...PPMPPPPP.....",
            "..PhhPPPPfffPP..",
            ".PPPPPMPPP...PP.",
            ".PPMMMPPPP..PPPP",
            "...PfPPPhhPPPP..",
            ".PfPPfPPPPPhhPP.",
            "...PPP..........",
            ".PPPPP.PPPhPPM..",
            ".PPPPP.PPhPPPf..",
            "PfffPPPP.PffPPPP",
            "PPPPPPPP...PPPPP",
            ".PPMMMPPPP......",
            "..PPPPPPPfPPPP..",
            "....PPPPPPPPP...",
            "..PPPhhPP.......",
            ".....PPPPPPPPP..",
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
        return new CustomLayoutStrategy(world);
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new SuddenDeath();
    }

    @Override
    public CombatStrategy createCombatStrategy() {
        return new attackerAlwaysWins();
    }

    @Override
    public UnitAvailableStrategy unitAvailableStrategy() {
        return new NoChariotAvailable();
    }
}
