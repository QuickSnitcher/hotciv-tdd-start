package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import org.junit.validator.PublicClassValidator;

import javax.swing.plaf.metal.OceanTheme;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
    private Game game;
    private Position p;


    public void oneRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    public void fiveRounds(){
        oneRound();
        oneRound();
        oneRound();
        oneRound();
        oneRound();

    }

    @Before
    public void setUp(){
        game = new GameImpl();

    }

    @Test
    public void playerRedStartsGame() {
        assertThat(game.getPlayerInTurn(), is(Player.RED));

    }

    @Test
    public void afterRedHasHadTurnBlueGetsTurn() {
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }
    @Test
    public void afterBLueHadTurnRedGetsTurn(){
        oneRound();
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void atGamestartYearis4000BC(){
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void afterOneRoundYearIs3900BC(){
        oneRound();
        assertThat(game.getAge(), is(-3900));
    }

    @Test
    public void afterFiveRoundsYearsis3500BC(){
        fiveRounds();
        assertThat(game.getAge(), is(-3500));
    }

    @Test
    public void redWinsGameAt3000BC(){
        fiveRounds();
        fiveRounds();
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void winnerIsNullAt3500BC(){
        fiveRounds();

        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public  void winnerIsRedAt2900BC(){
        fiveRounds();
        fiveRounds();
        oneRound();
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void tileIsOceanAt1X0atGameStart(){
        p = new Position(1,0);
        assertThat(game.getTileAt(p).getTypeString(), is(("ocean")));
    }

    @Test
    public void tileIsHillsAt0X1atGameStart(){
        p = new Position(0,1);
        assertThat(game.getTileAt(p).getTypeString(), is("hills"));
    }

    @Test
    public void tileIsMountain2X2AtGameStart(){
        p = new Position(2,2);
        assertThat(game.getTileAt(p).getTypeString(), is("mountain"));
    }
    @Test
    public void tileIsPlain0X0atGameStart(){
        p = new Position(0,0);
        assertThat(game.getTileAt(p).getTypeString(), is("plains"));
    }

    @Test
    public void redHasAnArcherAt2X0AtGameStart(){
        p = new Position(2,0);
        assertThat(game.getUnitAt(p).getTypeString(), is("archer"));
    }
    }


