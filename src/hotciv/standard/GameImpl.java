package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/** Skeleton implementation of HotCiv.
 
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
   limitations under the License...

*/

public class GameImpl implements Game {
    private int age = -4000;
    private Player playerInTurn = Player.RED;
    private Player winner = null;

    private int resource = 0;
    private City Redcity = new CityImpl(Player.RED);
    private City Bluecity = new CityImpl(Player.BLUE);
    private UnitImpl redSettlerUnit = new UnitImpl(GameConstants.SETTLER, Player.RED);
    private UnitImpl redArcherUnit = new UnitImpl(GameConstants.ARCHER, Player.RED);
    private UnitImpl blueLegionUnit = new UnitImpl(GameConstants.LEGION, Player.BLUE);
    private Tile oceanTile = new TileImpl(GameConstants.OCEANS);
    private Tile hillTile = new TileImpl(GameConstants.HILLS);
    private Tile mountainTile = new TileImpl(GameConstants.MOUNTAINS);
    private Tile plainsTile = new TileImpl(GameConstants.PLAINS);




    private HashMap<Position, UnitImpl> mapping = new HashMap(256);


public GameImpl(){

    mapping.put(new Position(2,0), redArcherUnit);
    mapping.put(new Position(4,3), redSettlerUnit);
    mapping.put(new Position(3,2), blueLegionUnit);

}

    public Tile getTileAt( Position p ) {
        if(p.getRow() == 1 && p.getColumn() == 0) {

            return oceanTile;

        }
        else if(p.getRow() == 0 && p.getColumn()==1){

            return hillTile;
        }else if(p.getRow()==2 && p.getColumn()==2) {

            return mountainTile;
        }else

        return plainsTile;
        }

  public Unit getUnitAt( Position p ) {

      return mapping.get(p);






  }

  public City getCityAt( Position p ) {
      if (p.getRow()==1 && p.getColumn()==1) {
          return Redcity;
      }
      if (p.getRow()==4 && p.getColumn()==1){

          return Bluecity;
      }
      else{
          return null;
      }

  }
  public Player getPlayerInTurn() { return playerInTurn; }
  public Player getWinner() { return winner; }
  public int getAge() { return age; }
  public boolean moveUnit( Position from, Position to ) {




     mapping.remove(from, redArcherUnit);
    mapping.put(to, redArcherUnit);

    if(getTileAt(to) == oceanTile){
        return false;
    }if (getTileAt(to) == mountainTile){
          return false;
      }
    return true;
  }


  public void endOfTurn() {
      if (playerInTurn == Player.RED) {
          playerInTurn = Player.BLUE;
      } else{
          playerInTurn = Player.RED;
          age += 100;
          resource += 6;
          ((CityImpl)Redcity).setResource(resource);
          ((CityImpl)Bluecity).setResource(resource);
      if(age == -3000){
        winner = Player.RED;
      }
      }


  }


  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {
            ((CityImpl)getCityAt(p)).setUnitInProduction(unitType);

  }
  public void performUnitActionAt( Position p ) {}
}
