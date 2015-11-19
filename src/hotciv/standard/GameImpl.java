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

    private int resource = 6;
    private CityImpl redcity = new CityImpl(Player.RED);
    private CityImpl bluecity = new CityImpl(Player.BLUE);
    private UnitImpl redSettlerUnit = new UnitImpl(GameConstants.SETTLER, Player.RED);
    private UnitImpl redArcherUnit = new UnitImpl(GameConstants.ARCHER, Player.RED);
    private UnitImpl redLegionUnit = new UnitImpl(GameConstants.LEGION, Player.RED);
    private UnitImpl blueLegionUnit = new UnitImpl(GameConstants.LEGION, Player.BLUE);
    private UnitImpl blueArcherUnit = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
    private UnitImpl blueSettlerUnit = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
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
          return redcity;
      }
      if (p.getRow()==4 && p.getColumn()==1){

          return bluecity;
      }
      else{
          return null;
      }

  }
  public Player getPlayerInTurn() { return playerInTurn; }

  public Player getWinner() { return winner; }

  public int getAge() { return age; }

  public boolean moveUnit( Position from, Position to ) {



        if (getTileAt(to) == plainsTile && getUnitAt(from).getMoveCount() == 1  || getTileAt(to) == hillTile && getUnitAt(from).getMoveCount() == 1 ) {
            UnitImpl unit = mapping.get(from);
            unit.setMoveCount(0);
            mapping.remove(from);
            mapping.put(to, unit);


        return true;
    }



else{
        return false;
    }

  }




  public void endOfTurn() {
      if (playerInTurn == Player.RED) {
          playerInTurn = Player.BLUE;
          bluecity.setResource(resource);
          if (bluecity.getProduction() == "archer" && bluecity.getResource() >= 10){

              mapping.put(new Position(4,1), blueArcherUnit);
              bluecity.setResource(-10);
          }
          if (bluecity.getProduction() == "legion" && bluecity.getResource() >= 15){
              mapping.put(new Position(4,1), blueLegionUnit);
              bluecity.setResource(-15);
          }
          if (bluecity.getProduction() == "settler" && bluecity.getResource() >= 30){
              mapping.put(new Position(4,1), blueSettlerUnit);
              bluecity.setResource(-30);
          }


      } else{
          playerInTurn = Player.RED;
          age += 100;

          redcity.setResource(resource);

          if (redcity.getProduction() == "archer" && redcity.getResource() >= 10){
              redcity.setResource(-10);
              if (mapping.containsKey(new Position(1,1))){
                  mapping.put(new Position(1,0), redArcherUnit);
              }
              else{
                  mapping.put(new Position(1,1), redArcherUnit);
              }


          }
          if (redcity.getProduction() == "legion" && redcity.getResource() >= 15){
              mapping.put(new Position(1,1), redLegionUnit);
              redcity.setResource(-15);
          }
          if (redcity.getProduction() == "settler" && redcity.getResource() >= 30){
              mapping.put(new Position(1,1), redSettlerUnit);
              redcity.setResource(-30);
          }


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
