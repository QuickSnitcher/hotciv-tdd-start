package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Liam on 26-11-2015.
 */
public class TestDeltaCiv {
    private Game game;
    Position p;
    public void setUp(){
        game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaWinnerStrategy(), new DeltaLayoutStrategy());

    }
    @Test
    public void redCityAtPosition1X1(){
        p = new Position(8,12);
        assertThat(game.getCityAt(p).getOwner(), is(Player.RED));
    }

    @Test
    public void blueCityAtPosition4X1(){
        p = new Position(4,5);
        assertThat(game.getCityAt(p).getOwner(), is(Player.BLUE));
    }

}
