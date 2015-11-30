package hotciv.variant;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by Liam on 26-11-2015.
 */
public class CustomLayoutStrategy implements LayoutStrategy {


    @Override
    public void generateWorld(GameImpl game) {
        String[] layout = new String[] {
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
        game.setupWorld(layout);
    }
}
