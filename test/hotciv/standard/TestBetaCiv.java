package hotciv.standard;

import hotciv.framework.Game;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by simon on 23-11-2015.
 */
public class TestBetaCiv {
    private Game game;


    @Before
    public void setup(){
       game = new GameImpl(new BetaCivAgingStrategy());
    }

    public void numberOfRounds(int round){
        for (int i = 0; i < round; i++ ){
            game.endOfTurn();
            game.endOfTurn();
        }
    }

    @Test
    public void ageIs3000BCAfter10Rounds(){
        numberOfRounds(10);
        assertThat(game.getAge(), is(-3000));
    }

    @Test
    public void ageIs0After40Rounds(){
        numberOfRounds(40);
        assertThat(game.getAge(), is(0));
    }

    @Test
    public void ageIs1BCAfter41Rounds(){
        numberOfRounds(41);
        assertThat(game.getAge(), is(-1));
    }

    @Test
    public void ageIs0After42Rounds(){
        numberOfRounds(42);
        assertThat(game.getAge(), is(0));
    }

    @Test
    public void ageIs50After43Rounds(){
        numberOfRounds(43);
        assertThat(game.getAge(), is(50));
    }

    @Test
    public void ageIs1750After77Rounds(){
        numberOfRounds(77);
        assertThat(game.getAge(), is(1750));
    }

    @Test
    public void ageIs1900After83Rounds(){
        numberOfRounds(83);
        assertThat(game.getAge(), is(1900));
    }

    @Test
    public void ageIs1970After97Rounds(){
        numberOfRounds(97);
        assertThat(game.getAge(), is(1970));
    }

    @Test
    public void ageIs1973After100Rounds(){
        numberOfRounds(100);
        assertThat(game.getAge(), is(1973));
    }


}
