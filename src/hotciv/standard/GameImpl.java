package hotciv.standard;

import hotciv.framework.*;

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
    private Tile tile = null;
    private Unit unit = null;


    public Tile getTileAt( Position p ) {
        if(p.getRow() == 1 && p.getColumn() == 0) {
            tile = new TileImpl(GameConstants.OCEANS);
            return tile;

        }
        else if(p.getRow() == 0 && p.getColumn()==1){
            tile = new TileImpl(GameConstants.HILLS);
            return tile;
        }else if(p.getRow()==2 && p.getColumn()==2) {
            tile = new TileImpl(GameConstants.MOUNTAINS);
            return tile;
        }else
            tile = new TileImpl(GameConstants.PLAINS);
        return tile;
        }

  public Unit getUnitAt( Position p ) {
      if (p.getRow() == 4 && p.getColumn() == 3) {
          unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
          return unit;
      }
      else if (p.getRow() == 2 && p.getColumn() == 0) {
          unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
          return unit;
      }
      else if (p.getRow() == 3 && p.getColumn() == 2){
          unit = new UnitImpl(GameConstants.LEGION, Player.BLUE);
          return unit;
      }
      else
          return null;

  }

  public City getCityAt( Position p ) { return null; }
  public Player getPlayerInTurn() { return playerInTurn; }
  public Player getWinner() { return winner; }
  public int getAge() { return age; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }


  public void endOfTurn() {
      if (playerInTurn == Player.RED) {
          playerInTurn = Player.BLUE;
      } else{
          playerInTurn = Player.RED;
          age += 100;
      if(age == -3000){
        winner = Player.RED;
      }
      }


  }


  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
}
