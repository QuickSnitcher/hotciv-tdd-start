package hotciv.standard;

import hotciv.framework.*;
import hotciv.variant.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by simon on 03-12-2015.
 */
public class TestEpsilonCiv {
    private Game game;
    private CombatStrategy combatStrategy;
    private int x;
    private int y;
    private SupportedCombat supportedCombat;

    @Before
    public void setUp(){

            game = new GameStub();
        supportedCombat = new SupportedCombat(new StaticDieRoll(4),new StaticDieRoll(4));

    }

    @Test
    public void attackingSoloArcherHas2Strength(){
        supportedCombat.attackerWins(game,new Position(2,2), new Position(2,3));

        assertThat(supportedCombat.getAttackingStrength(), is(2));

    }

    @Test
    public void defendingSoloArcherHas3Strength(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(2,3));
        assertThat(supportedCombat.getDefensiveStrength(), is(3));
    }

    @Test
    public void plainsTileGivesBonusOf1(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(2,3));
        assertThat(supportedCombat.getTerrain(), is(1));
    }

    @Test
    public void hillsTileGivesBonusOf2(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(4,4));
        assertThat(supportedCombat.getTerrain(), is(2));
    }

    @Test
    public void cityGivesBonusOf3(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(7,7));
        assertThat(supportedCombat.getTerrain(), is(3));
    }

    @Test
    public void oneSupportGivesBonusOf1(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(7,7));
        assertThat(supportedCombat.getSupport(), is(1));
    }

    @Test
    public void threeSupportGivesBonusOf3(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(4,5));
        assertThat(supportedCombat.getSupport(), is(3));
    }

    @Test
    public void soloAttackingArcherOnPlainsWithRollOf4HasOutcomeOf8(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(4,5));
        assertThat(supportedCombat.getAttackingResult(), is(8));
    }

    @Test
    public void soloDefendingArcherOnPlainsWithRollOf4HasOutcomeOf12(){
        supportedCombat.attackerWins(game, new Position(2,2), new Position(2,3));
        assertThat(supportedCombat.getDefensiveResult(), is(12));
    }

    @Test
    public void redArcherAt2X0AttacksBlueLegionAt3X2WinsAndMovesToLocation(){
        TestEpsilonGameFactory factory = new TestEpsilonGameFactory(5, 2);
        SupportedCombat certainCombat = (SupportedCombat) factory.createCombatStrategy();
        GameImpl realGame = new GameImpl(factory);
        realGame.moveUnit(new Position(2,0),new Position(3,1));
        realGame.endOfTurn();
        realGame.endOfTurn();
        realGame.moveUnit(new Position(3,1), new Position(3,2));
        assertThat(certainCombat.getAttackingResult(), is(10));
        assertThat(certainCombat.getDefensiveResult(), is(4));
        assertThat(realGame.getUnitAt(new Position(3,2)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void redArcherLosesToBlueLegionAndIsThereforeRemovedFromGame(){
        TestEpsilonGameFactory factory = new TestEpsilonGameFactory(2, 5);
        SupportedCombat certainCombat = (SupportedCombat) factory.createCombatStrategy();
        GameImpl realGame = new GameImpl(factory);
        realGame.moveUnit(new Position(2,0),new Position(3,1));
        realGame.endOfTurn();
        realGame.endOfTurn();
        realGame.moveUnit(new Position(3,1), new Position(3,2));
        assertThat(certainCombat.getAttackingResult(), is(4));
        assertThat(certainCombat.getDefensiveResult(), is(10));
        assertThat(realGame.getUnitAt(new Position(3,1)), is(nullValue()));
    }

    @Test
    public void redPlayerWinsGameAfterWinning3Attacks(){
        Game specialGame = new GameImpl(new TestEpsilonGameFactory(6,1));
        insertUnits(specialGame);

        specialGame.moveUnit(new Position(2, 2), new Position(3, 2));
        specialGame.endOfTurn();
        specialGame.endOfTurn();
        specialGame.moveUnit(new Position(3,2), new Position(4,2));
        specialGame.endOfTurn();
        specialGame.endOfTurn();
        specialGame.moveUnit(new Position(4,2), new Position(5,2));
        specialGame.endOfTurn();
        assertThat(specialGame.getWinner(), is(Player.RED));
    }












    public void insertUnits(Game specialGame){
        specialGame.getUnitMap().put(new Position(2,2), new UnitImpl(GameConstants.ARCHER, Player.RED, 2,3));
        specialGame.getUnitMap().put(new Position(3,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
        specialGame.getUnitMap().put(new Position(4,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
        specialGame.getUnitMap().put(new Position(5,2), new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
    }



    public String[] gameLayout(){
        String[] layout = new String[]{
                "...PPMPPPPP.....",
                "..PhhPPPPfffPP..",
                ".PPPPPMPPP...PP.",
                ".PPMMMPPPP..PPPP",
                "...PfPPPhhPPPP..",
                ".PfPBfPPPPPhhPP.",
                "...PPP..........",
                ".PPPPP.PPPhPPM..",
                ".PPPPP.PPhPPPf..",
                "PfffPPPP.PffPPPP",
                "PPPPPPPP...PPPPP",
                ".PPMMMPPPP......",
                "..PPPPPPRfPPPP..",
                "....PPPPPPPPP...",
                "..PPPhhPP.......",
                ".....PPPPPPPPP..",
        };
        return layout;
    }







}

class GameStub implements Game{


    @Override
    public Tile getTileAt(Position p) {

        if (p.getRow()==4 && p.getColumn()==4){
            return new TileImpl(GameConstants.HILLS);
        }
        else
            return new TileImpl(GameConstants.PLAINS);

    }

    @Override
    public Unit getUnitAt(Position p) {
        if (p.getRow()==2 && p.getColumn()==2){
            return new UnitImpl(GameConstants.ARCHER, Player.RED, 2,3);
        }
        if (p.getRow()==2 && p.getColumn()==3 ||p.getRow()==4 && p.getColumn()==4 || p.getRow() == 7 && p.getColumn() == 7 || p.getRow() == 6 && p.getColumn() == 7 || p.getRow() == 4 && p.getColumn() == 5 || p.getRow() == 5 && p.getColumn() == 4 || p.getRow() == 5 && p.getColumn() == 5  ){
            return new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3);
        }
        else
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        if (p.getRow() == 7 && p.getColumn() == 7){
            return new CityImpl(Player.BLUE);
        }else

        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return false;
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {

    }

    @Override
    public HashMap getTileMap() {
        return null;
    }

    @Override
    public HashMap getUnitMap() {
        return null;
    }

    @Override
    public HashMap getCityMap() {
        return null;
    }
}
