package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variant.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by simon on 10-12-2015.
 */
public class TestZetaCiv {

    private GameImpl game;


    @Before
    public void setup(){
        String[] world = new String[]{
                "...PPMPPPPP.....",
                "..PhhPPPPfffPP..",
                ".PPPPPMPPP...PP.",
                ".PPMMMPPPP..PPPP",
                "...PfPPPhhPPPP..",
                ".PfPBfPPPPPhhPP.",
                "...PPP..........",
                ".PPPPP.PPPhPPM..",
                ".PPPPP.PPhPPPf..",
                "PfffPPPP.PffPPPP",
                "PPPPPPPP...PPPPP",
                ".PPMMMPPPP......",
                "..PPPPPPRfPPPP..",
                "....PPPPPPPPP...",
                "..PPPhhPP.......",
                ".....PPPPPPPPP..",
        };
        game = new GameImpl(new LinearAgingStrategy(), new SuddenDeath(), new CustomLayoutStrategy(world), new NoActionsStrategy(), new attackerAlwaysWins());
        game.getUnitMap().put(new Position(2,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE));
        game.getUnitMap().put(new Position(3,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE));
        game.getUnitMap().put(new Position(4,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE));
        game.getUnitMap().put(new Position(5,2), new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.getCityMap().put(new Position(4,4), new CityImpl(Player.RED));
        game.getCityMap().put(new Position(2,1), new CityImpl(Player.RED));

    }

    @Test
    public void redPlayerWinsGameWhenCapturingBlueCity(){
        game.moveUnit(new Position(5,2), new Position(5,1));
        assertThat(game.getWinner(), is(Player.RED));
    }












}
