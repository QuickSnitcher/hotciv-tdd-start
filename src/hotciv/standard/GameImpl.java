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
    private AgingStrategy agingStrategy;
    private WinnerStrategy winnerStrategy;
    private LayoutStrategy layoutStrategy;
    private ActionStrategy actionstrategy;

    private int resource = 6;


    private Tile oceanTile = new TileImpl(GameConstants.OCEANS);
    private Tile hillTile = new TileImpl(GameConstants.HILLS);
    private Tile mountainTile = new TileImpl(GameConstants.MOUNTAINS);
    private Tile plainsTile = new TileImpl(GameConstants.PLAINS);





    private HashMap<Position, UnitImpl> unitMapping = new HashMap(256);
    private HashMap<Position, CityImpl> cityMapping = new HashMap(256);


public GameImpl(AgingStrategy agingStrategy, WinnerStrategy winnerStrategy, LayoutStrategy layoutStrategy, ActionStrategy actionStrategy){
    this.agingStrategy = agingStrategy;
    this.winnerStrategy = winnerStrategy;
    this.layoutStrategy = layoutStrategy;
    this.actionstrategy = actionStrategy;

    unitMapping.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
    unitMapping.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
    unitMapping.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    cityMapping.put(new Position(layoutStrategy.redCityXPosition(),layoutStrategy.redCityYPosition()), new CityImpl(Player.RED));
    cityMapping.put(new Position(layoutStrategy.blueCityXPosition(),layoutStrategy.blueCityYPosition()), new CityImpl(Player.BLUE));
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

    public HashMap getUnitMap(){
        return unitMapping;
    }
  public Unit getUnitAt( Position p ) {

      return unitMapping.get(p);



  }

    public HashMap getCityMap(){
        return cityMapping;
    }

  public City getCityAt( Position p ) {
      return cityMapping.get(p);


  }
  public Player getPlayerInTurn() { return playerInTurn; }

  public Player getWinner() { return winner; }

  public int getAge() { return age; }

  public boolean moveUnit( Position from, Position to ) {
      if (getTileAt(to).getTypeString() == GameConstants.OCEANS) {
          return false;
      }
      if (getTileAt(to).getTypeString() == GameConstants.MOUNTAINS) {
          return false;
      }
      if (getUnitAt(from).getMoveCount() == 0) {
          return false;
      }
      if (getUnitAt(from).getOwner() != playerInTurn) {
          return false;
      }
      if (getUnitAt(to) != null) {
          if (getUnitAt(from).getOwner().equals(getUnitAt(to).getOwner())) {
              return false;
          }
      }
      if (from.getRow() - to.getRow() > 1 || from.getRow() - to.getRow() < -1 || from.getColumn() - to.getColumn() > 1 || from.getColumn() - to.getColumn() < -1) {
          return false;
      }

      if (getUnitAt(from).checkFortify() == true){
          return false;
      }


      UnitImpl unit = unitMapping.get(from);
      unit.setMoveCount(0);
      unitMapping.remove(from);
      unitMapping.put(to, unit);
      if (getCityAt(to) != null) {
          if (!getUnitAt(to).getOwner().equals(getCityAt(to).getOwner())) {
              ((CityImpl) getCityAt(to)).setOwner(getUnitAt(to).getOwner());

          }
      }
      return true;

  }










  public void endOfTurn() {
      if (playerInTurn == Player.RED) {
          playerInTurn = Player.BLUE;
          for (UnitImpl allUnits : unitMapping.values()){
              allUnits.setMoveCount(1);
          }
          for (CityImpl blueCities : cityMapping.values()) {
              if (blueCities.getOwner() == Player.BLUE) {
                  blueCities.setResource(resource);


                  if (blueCities.getProduction() == GameConstants.ARCHER && blueCities.getResource() >= 10) {

                      createUnit(new Position(4, 1), new UnitImpl(GameConstants.ARCHER, Player.BLUE));
                      blueCities.setResource(-10);
                  }
                  if (blueCities.getProduction() == GameConstants.LEGION && blueCities.getResource() >= 15) {
                      createUnit(new Position(4, 1), new UnitImpl(GameConstants.LEGION, Player.BLUE));
                      blueCities.setResource(-15);
                  }
                  if (blueCities.getProduction() == GameConstants.SETTLER && blueCities.getResource() >= 30) {
                      createUnit(new Position(4, 1), new UnitImpl(GameConstants.SETTLER, Player.BLUE));
                      blueCities.setResource(-30);
                  }
              }
          }



      } else{
          playerInTurn = Player.RED;
          age = agingStrategy.calculateAge(age);
          for (UnitImpl allUnits : unitMapping.values()){
              allUnits.setMoveCount(1);
          }
          for (CityImpl redCities : cityMapping.values()) {
              if (redCities.getOwner() == Player.RED){
              redCities.setResource(resource);

              if (redCities.getProduction() == GameConstants.ARCHER && redCities.getResource() >= 10) {
                  redCities.setResource(-10);
                  createUnit(new Position(1, 1), new UnitImpl(GameConstants.ARCHER, Player.RED));


              }
              if (redCities.getProduction() == GameConstants.LEGION && redCities.getResource() >= 15) {
                  createUnit(new Position(1, 1), new UnitImpl(GameConstants.LEGION, Player.RED));
                  redCities.setResource(-15);
              }
              if (redCities.getProduction() == GameConstants.SETTLER && redCities.getResource() >= 30) {
                  createUnit(new Position(1, 1), new UnitImpl(GameConstants.SETTLER, Player.RED));
                  redCities.setResource(-30);
              }


          }}
      }
      winner = winnerStrategy.checkWinner(this);


  }

    public void createUnit(Position cityPosition ,UnitImpl createdUnit){

        if (!unitMapping.containsKey(cityPosition)){

            unitMapping.put(cityPosition, createdUnit);
        }

        else if(!unitMapping.containsKey(new Position(cityPosition.getRow(), cityPosition.getColumn() -1))){
            unitMapping.put(new Position(cityPosition.getRow(), cityPosition.getColumn() -1),createdUnit);
        }
        else if(!unitMapping.containsKey(new Position(cityPosition.getRow() +1, cityPosition.getColumn() -1))){
            unitMapping.put(new Position(cityPosition.getRow() +1, cityPosition.getColumn() -1),createdUnit);
        }
        else if(!unitMapping.containsKey(new Position(cityPosition.getRow() +1, cityPosition.getColumn()))){
            unitMapping.put(new Position(cityPosition.getRow() +1, cityPosition.getColumn()),createdUnit);
        }
        else if(!unitMapping.containsKey(new Position(cityPosition.getRow() +1, cityPosition.getColumn() +1))){
            unitMapping.put(new Position(cityPosition.getRow() +1, cityPosition.getColumn() +1),createdUnit);
        }
        else if(!unitMapping.containsKey(new Position(cityPosition.getRow(), cityPosition.getColumn() +1))){
            unitMapping.put(new Position(cityPosition.getRow(), cityPosition.getColumn() +1),createdUnit);
        }


    }


  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

  public void changeProductionInCityAt( Position p, String unitType ) {
            ((CityImpl)getCityAt(p)).setUnitInProduction(unitType);

  }
  public void performUnitActionAt( Position p ) {actionstrategy.performAction(p, this); }
}
