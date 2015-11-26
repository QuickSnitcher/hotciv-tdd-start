package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variant.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by simon on 24-11-2015.
 */
public class TestGammaCiv {

    private Game game;

    @Before
    public void setup(){
        game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaWinnerStrategy(), new DeltaLayoutStrategy());
    }

    public void numberOfRounds(int round){
        for (int i = 0; i < round; i++ ){
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void redSettlerActionCreatesCityAt4X3(){
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getCityAt(new Position(4,3)).getOwner(), is(Player.RED));
        assertThat(game.getUnitAt(new Position(4,3)), is(nullValue()));
    }

    @Test
    public void redArcherActionDoublesDefence(){
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(6));

    }


}
