package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variant.*;
import org.junit.Before;
import org.junit.Test;
import sun.util.resources.cldr.te.TimeZoneNames_te;

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
        game = new GameImpl(new ZetaGameFactory());
        game.getUnitMap().put(new Position(2,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
        game.getUnitMap().put(new Position(3,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
        game.getUnitMap().put(new Position(4,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
        game.getUnitMap().put(new Position(5,2), new UnitImpl(GameConstants.ARCHER, Player.RED, 2,3));
        game.getCityMap().put(new Position(5,1), new CityImpl(Player.BLUE));
        game.getCityMap().put(new Position(2,1), new CityImpl(Player.RED));

    }

    @Test
    public void redPlayerWinsGameWhenCapturingBlueCity(){

        assertThat(game.getCityAt(new Position(5,1)).getOwner(), is(Player.BLUE));
        game.moveUnit(new Position(5,2), new Position(5,1));
        assertThat(game.getCityAt(new Position(5,1)).getOwner(), is(Player.RED));

        assertThat(game.getCityAt(new Position(2, 1)).getOwner(), is(Player.RED));
        game.endOfTurn();
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void redPlayerDoesNotWinByCapturingAfter20Rounds(){
        numberOfRounds(21);
        assertThat(game.getCityAt(new Position(5,1)).getOwner(), is(Player.BLUE));
        game.moveUnit(new Position(5,2), new Position(5,1));
        assertThat(game.getCityAt(new Position(5,1)).getOwner(), is(Player.RED));

        assertThat(game.getCityAt(new Position(2, 1)).getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(nullValue()));

    }

    @Test
    public void redPlayerWinsGameAfter20RoundsByWinningThreeAttacks(){
        numberOfRounds(21);
        game.moveUnit(new Position(5,2), new Position(4,2));
        numberOfRounds(1);
        game.moveUnit(new Position(4, 2), new Position(3, 2));
        numberOfRounds(1);
        game.moveUnit(new Position(3, 2), new Position(2, 2));
        game.endOfTurn();
        assertThat(game.getWinner(), is(Player.RED));

    }







    public void numberOfRounds(int round){
        for (int i = 0; i < round; i++ ){
            game.endOfTurn();
            game.endOfTurn();
        }
    }



}
