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
        game = new GameImpl(new GammeGameFactory());
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

    @Test
    public void redArcherHas3DefenceAtNormal(){
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), is(3));

    }

    @Test
    public void redArcherCannotMoveWhenFortified(){
        game.performUnitActionAt(new Position(2,0));
        game.moveUnit(new Position(2,0), new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)), is(nullValue()));
    }

    @Test
    public void redArcherCanUnfortify(){
        game.performUnitActionAt(new Position(2,0));
        game.performUnitActionAt(new Position(2,0));
        game.moveUnit(new Position(2,0), new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getTypeString(), is("archer"));
        assertThat(game.getUnitAt(new Position(3,0)).getDefensiveStrength(), is(3));

    }





}
