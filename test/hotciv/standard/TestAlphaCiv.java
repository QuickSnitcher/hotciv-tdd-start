package hotciv.standard;

import hotciv.framework.*;

import hotciv.variant.AlphaCivActionStrategy;
import hotciv.variant.AlphaCivAgingStrategy;
import hotciv.variant.AlphaLayoutStrategy;
import hotciv.variant.AlphaWinnerStrategy;
import org.junit.*;

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
    private Position newP;
    private City city;


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
        game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaWinnerStrategy(), new AlphaLayoutStrategy(), new AlphaCivActionStrategy());


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
        assertThat(game.getUnitAt(p).getOwner(), is(Player.RED));
    }

    @Test
    public void redHasASettlerAt4X3AtGameStart(){
        p = new Position(4,3);
        assertThat(game.getUnitAt(p).getTypeString(), is("settler"));
        assertThat(game.getUnitAt(p).getOwner(), is(Player.RED));
    }

    @Test
    public void blueHasLegionAt3X2AtGameStart(){
        p = new Position(3,2);
        assertThat(game.getUnitAt(p).getTypeString(), is("legion"));
        assertThat(game.getUnitAt(p).getOwner(), is(Player.BLUE));
    }

    @Test
    public void noUnitsOnOtherLocations(){
        p = new Position(5,5);
        assertThat(game.getUnitAt(p), is(nullValue()));
    }

    @Test
    public void redArcherMovesOneTileToPosition3X0(){
        p = new Position(2,0);
        newP = new Position(3,0);
       assertThat(game.moveUnit(p, newP), is(Boolean.TRUE));
    }

    @Test
    public void redArcherCannotMoveToOceanTile(){
        p = new Position(2,0);
        newP = new Position(1,0);
        assertThat(game.moveUnit(p, newP), is(Boolean.FALSE));
    }

    @Test
    public void redArcherCannotMoveToMountainTile(){
        p = new Position(2,0);
        newP = new Position(2,2);
        assertThat(game.moveUnit(p, newP), is(Boolean.FALSE));
    }

    @Test
    public void redArcherCanMoveToPlains(){
        p = new Position(2,0);
        newP = new Position(3,1);
        assertThat(game.moveUnit(p, newP), is(Boolean.TRUE));
    }

    @Test
    public void redCityAtPosition1X1(){
        p = new Position(1,1);
        assertThat(game.getCityAt(p).getOwner(), is(Player.RED));
    }

    @Test
    public void blueCityAtPosition4X1(){
        p = new Position(4,1);
        assertThat(game.getCityAt(p).getOwner(), is(Player.BLUE));
    }

    @Test
    public void populationSizeOfRedCityIs1(){
        p = new Position(1,1);
        assertThat(game.getCityAt(p).getSize(), is(1));
    }
    @Test
    public void redCitySetsArcherInProduction(){
        p = new Position(1,1);
        game.changeProductionInCityAt(p, "archer");
        assertThat(game.getCityAt(p).getProduction(), is("archer"));
    }

    @Test
    public void redCitySetsLegionInProduction(){
        p = new Position(1,1);
        game.changeProductionInCityAt(p, "legion");
        assertThat(game.getCityAt(p).getProduction(), is("legion"));
    }

    @Test
    public void blueCitySetsSettlerInProduction(){
        p = new Position(4,1);
        game.changeProductionInCityAt(p, "settler");
        assertThat(game.getCityAt(p).getProduction(), is("settler"));
    }

    @Test
    public void redCityHas0ProductionAtStart(){
        p = new Position(1,1);
        assertThat(game.getCityAt(p).getResource(), is(0));
    }

    @Test
    public void redCityHas6ProductionAfterOneRound(){
        p = new Position(1,1);
        oneRound();
        assertThat(game.getCityAt(p).getResource(), is(6));
    }

    @Test
    public void redCityHas18ProductionAfter3Rounds(){
        p = new Position(1,1);
        oneRound();
        oneRound();
        oneRound();
        assertThat(game.getCityAt(p).getResource(), is(18));
    }

    @Test
    public void blueCityHas6ProductionAfterOneRound(){
        p = new Position(4,1);
        oneRound();
        assertThat(game.getCityAt(p).getResource(), is(6));
    }

    @Test
    public void blueCityHas18ProductionAfter3Rounds(){
        p = new Position(4,1);
        oneRound();
        oneRound();
        oneRound();
        assertThat(game.getCityAt(p).getResource(), is(18));
    }

    @Test
    public void redArcherHasMovedFrom2X0To3X0(){
        p = new Position(2,0);
        newP = new Position(3,0);

        assertThat(game.getUnitAt(p).getTypeString(), is("archer"));
        game.moveUnit(p, newP);

        assertThat(game.getUnitAt(newP).getTypeString(), is("archer"));
    }

    @Test
    public void redArcherHasNotMovedToTileIfTileIsMountain(){
        p = new Position(2,0);
        newP = new Position(2,2);

        game.moveUnit(p, newP);
        assertThat(game.getUnitAt(newP), is(nullValue()));

    }



    @Test
    public void redCityProducesAnArcherWhenProductionIsTenOrMore(){
        p = new Position(1,1);
        game.changeProductionInCityAt(p, "archer");
        oneRound();
        assertThat(game.getCityAt(p).getResource(), is(6));
        oneRound();
        assertThat(game.getUnitAt(p).getTypeString(), is("archer"));
    }

    @Test
    public void blueCityProducesAnArcherWhenProductionIsTenOrMore(){
        p = new Position(4,1);
        oneRound();
        game.changeProductionInCityAt(p, "archer");
        assertThat(game.getCityAt(p).getResource(), is(6));
        oneRound();
        assertThat(game.getUnitAt(p).getTypeString(), is("archer"));
    }

    @Test
    public void redCityProducesTheChosenProductionUnitSet(){
        p = new Position(1,1);
        game.changeProductionInCityAt(p, "legion");
        fiveRounds();
        assertThat(game.getCityAt(p).getProduction(), is("legion"));
        assertThat(game.getUnitAt(p).getTypeString(), is("legion"));
    }

    @Test
    public void blueCityProducesTheChosenProductionUnitSet(){
        p =new Position(4,1);

        game.changeProductionInCityAt(p, "legion");
        fiveRounds();
        assertThat(game.getCityAt(p).getProduction(), is("legion"));
        assertThat(game.getUnitAt(p).getTypeString(), is("legion"));



    }

    @Test
    public void unitCostIsSubtractedFromTheCitiesProductionAmount(){
        Position bluecity = new Position(4,1);
        Position redcity = new Position(1,1);

        game.changeProductionInCityAt(bluecity, "legion");
        game.changeProductionInCityAt(redcity, "settler");
        fiveRounds();
        oneRound();
        assertThat(game.getCityAt(bluecity).getResource(), is(6));
        assertThat(game.getCityAt(redcity).getResource(), is(6));
    }

    @Test
    public void redArcherMovesNorthIfCityTileIsOccupied(){
        Position redcity = new Position(1,1);
        Position north = new Position(1,0);

        game.changeProductionInCityAt(redcity, "legion");
        oneRound();
        assertThat(game.getCityAt(redcity).getResource(), is(6));
        oneRound();
        oneRound();
        game.changeProductionInCityAt(redcity, "archer");
        oneRound();
        oneRound();
        assertThat(game.getUnitAt(redcity).getTypeString(), is("legion"));
        assertThat(game.getUnitAt(north).getTypeString(), is("archer"));

    }

@Test
    public void redArcherCanOnlyMoveOncePerTurn(){
    Position redarcher = new Position(2,0);
    Position moveTo = new Position(3,0);

    game.moveUnit(redarcher, moveTo);
    assertThat(game.getUnitAt(moveTo).getTypeString(), is("archer"));
    assertThat(game.getUnitAt(moveTo).getMoveCount(), is(0));
    game.moveUnit(moveTo, new Position(4,0));
    assertThat(game.getUnitAt(new Position(4,0)), is(nullValue()));
    oneRound();
    game.moveUnit(moveTo, new Position(4,0));
    assertThat(game.getUnitAt(new Position(4,0)).getTypeString(), is("archer"));
    assertThat(game.getUnitAt(new Position(4,0)).getMoveCount(), is(0));

}

    @Test
    public void redArcherCreatedByRedCityMovesToNearestAvailableSpaceClockwise(){

        game.changeProductionInCityAt(new Position(1,1), "archer");
        fiveRounds();
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is("archer"));
        assertThat(game.getUnitAt(new Position(1,0)).getTypeString(), is("archer"));
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(), is("archer"));

    }

    @Test
    public void blueLegionCanNotMoveMoreThanOneTileARound(){
        game.endOfTurn();
        game.moveUnit(new Position(3, 2), new Position(4, 2));
        assertThat(game.getUnitAt(new Position(4,2)).getTypeString(), is("legion"));
        oneRound();
        game.moveUnit(new Position(4,2), new Position(6,2));
        assertThat(game.getUnitAt(new Position(6,2)), is(nullValue()));
    }

    @Test
    public void attackerAlwaysWins(){
        game.moveUnit(new Position(2,0), new Position(3,1));
        oneRound();
        assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is("legion"));
        game.moveUnit(new Position(3,1), new Position(3,2));
        assertThat(game.getUnitAt(new Position(3,2)).getTypeString(), is("archer"));
    }

    @Test
    public void unitCannotAttackOwnUnit(){
        game.moveUnit(new Position(2,0), new Position(3,1));
        oneRound();
        game.moveUnit(new Position(3,1), new Position(4,2));
        oneRound();
        assertThat(game.moveUnit(new Position(4, 2), new Position(4, 3)), is(false));



    }

    @Test
    public void redArcherCanConvertBlueCity(){

        game.moveUnit(new Position(2,0), new Position(3,0));
        oneRound();
        game.moveUnit(new Position(3,0), new Position(4,1));
        assertThat(game.getUnitAt(new Position(4,1)).getTypeString(), is("archer"));
        assertThat(game.getCityAt(new Position(4,1)).getOwner(), is(Player.RED));

    }






}


