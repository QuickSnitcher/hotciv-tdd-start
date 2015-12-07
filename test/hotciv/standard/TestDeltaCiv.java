package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variant.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Liam on 26-11-2015.
 */
public class TestDeltaCiv {
    private Game game;
    Position p;

    @Before
    public void setUp(){
        game = new GameImpl(new LinearAgingStrategy(), new RedWinnerStrategy(), new CustomLayoutStrategy(), new NoActionsStrategy(), new attackerAlwaysWins());

    }

    @Test
    public void oceanTileAt0x0(){
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void redCityAtPosition8X12(){
        p = new Position(8,12);
        assertThat(game.getCityAt(p).getOwner(), is(Player.RED));
    }

    @Test
    public void blueCityAtPosition4X5(){
        p = new Position(4,5);
        assertThat(game.getCityAt(p).getOwner(), is(Player.BLUE));
    }

}
