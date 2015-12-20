import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.ThetaConstants;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variant.GammeGameFactory;
import hotciv.variant.ThetaGameFactory;
import hotciv.variant.ZetaGameFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by simon on 17-12-2015.
 */
public class TestSemiThetaCiv {

    private GameImpl game;

    @Before
    public void setup(){

        game = new GameImpl(new ThetaGameFactory());
        game.getUnitMap().put(new Position(4,4), new UnitImpl(ThetaConstants.CHARIOT, Player.RED, 3,1));
        game.getCityMap().put(new Position(2,1), new CityImpl(Player.RED));
    }


    @Test
    public void redChariotAt4X4Has3AttackAnd1Defence(){
        assertThat(game.getUnitAt(new Position(4,4)).getAttackingStrength(), is(3));
        assertThat(game.getUnitAt(new Position(4,4)).getDefensiveStrength(), is(1));

    }

    @Test
    public void redCityCanSetProductionToChariotAndCreatesChariot(){
        game.changeProductionInCityAt(new Position(2,1), ThetaConstants.CHARIOT);
         numberOfRounds(4);
        assertThat(game.getUnitAt(new Position(2,1)).getTypeString(), is(ThetaConstants.CHARIOT));
    }

    @Test
    public void redChariotAt4X4HasDoubleDefenceWhileFortified(){
        game.performUnitActionAt(new Position(4,4));
        assertThat(game.getUnitAt(new Position(4,4)).getDefensiveStrength(), is(2));
    }

    public void numberOfRounds(int round){
        for (int i = 0; i < round; i++ ){
            game.endOfTurn();
            game.endOfTurn();
        }
    }








}
