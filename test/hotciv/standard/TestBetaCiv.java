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
}
