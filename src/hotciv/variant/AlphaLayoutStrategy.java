package hotciv.variant;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by Liam on 26-11-2015.
 */
public class AlphaLayoutStrategy implements LayoutStrategy {


    @Override
    public void generateWorld(GameImpl game) {
        String[] layout = new String[]{
                "P.PPPPPPPPPPPPPP",
                "hPPPPPPPPPPPPPPP",
                "PPMPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
        };

        game.getUnitMap().put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.getUnitMap().put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        game.getUnitMap().put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
        game.getCityMap().put(new Position(1, 1), new CityImpl(Player.RED));
        game.getCityMap().put(new Position(4, 1), new CityImpl(Player.BLUE));

        game.setupWorld(layout);
    }
}
