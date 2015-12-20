package hotciv.standard;

import hotciv.framework.*;
import hotciv.variant.FirstToThree;

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
    private CombatStrategy combatStrategy;
    private GameImplFactory factory;
    private UnitAvailableStrategy unitAvailableStrategy;


    private int resource = 6;





    private HashMap<Position, UnitImpl> unitMapping = new HashMap(256);
    private HashMap<Position, CityImpl> cityMapping = new HashMap(256);
    private HashMap<Position, TileImpl> tileMapping = new HashMap(256);


    public GameImpl(GameImplFactory factory) {
        this.factory = factory;
        this.agingStrategy = factory.createAgingStrategy();
        this.winnerStrategy = factory.createWinnerStrategy();
        this.layoutStrategy = factory.createLayoutStrategy();
        this.actionstrategy = factory.createActionStrategy();
        this.combatStrategy = factory.createCombatStrategy();
        this.unitAvailableStrategy = factory.unitAvailableStrategy();
        layoutStrategy.generateWorld(this);


    }

    public HashMap<Position, TileImpl> getTileMap() {
        return tileMapping;
    }

    public Tile getTileAt(Position p) {

            return tileMapping.get(p);
    }

    public HashMap<Position, UnitImpl> getUnitMap() {
        return unitMapping;
    }

    public Unit getUnitAt(Position p) {

        return unitMapping.get(p);
    }


    public HashMap<Position, CityImpl> getCityMap() {
        return cityMapping;
    }

    public CityImpl getCityAt(Position p) {
        return cityMapping.get(p);


    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {

        return winner;
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        if (getUnitAt(from) == null) {
            return false;
        }
        if (getTileAt(to).getTypeString().equals(GameConstants.OCEANS)) {
            return false;
        }
        if (getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS)) {
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

        if (getUnitAt(from).checkFortify()) {
            return false;
        }

        //If attacker loses then remove attacker. Else move attacker.
        if (getUnitAt(to) == null) {
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
        }if (!combatStrategy.attackerWins(this, from, to)) {
            unitMapping.remove(from);

            return true;
        }else{
            UnitImpl unit = unitMapping.get(from);
            unit.setMoveCount(0);
            winnerStrategy.increaseAttackCounter(this);
            unitMapping.remove(to);
            unitMapping.remove(from);
            unitMapping.put(to, unit);
            if (getCityAt(to) != null) {
                if (!getUnitAt(to).getOwner().equals(getCityAt(to).getOwner())) {
                    ((CityImpl) getCityAt(to)).setOwner(getUnitAt(to).getOwner());

                }
            }
            return true;
        }
    }


    public void endOfTurn() {
        if (playerInTurn == Player.RED) {
            playerInTurn = Player.BLUE;
            for (UnitImpl allUnits : unitMapping.values()) {
                allUnits.setMoveCount(1);
            }
            for (Position blueCity : cityMapping.keySet()) {
                CityImpl city = getCityAt(blueCity);
                if (city != null) {
                    if (city.getOwner() == Player.BLUE) {
                        city.setResource(resource);


                        String cityProduction = city.getProduction();
                            int unitCost = unitAvailableStrategy.unitCostOf(cityProduction);
                        if (city.getResource() >= unitCost) {
                            int attack = unitAvailableStrategy.getAtt(cityProduction);
                            int defence = unitAvailableStrategy.getDef(cityProduction);
                            createUnit(blueCity, new UnitImpl(cityProduction, Player.BLUE, attack, defence));
                            city.setResource(-unitCost);
                        }


                    }
                }
            }


        } else {
            playerInTurn = Player.RED;
            age = agingStrategy.calculateAge(age);
            for (UnitImpl allUnits : unitMapping.values()) {
                allUnits.setMoveCount(1);
            }
            for (Position redCity : cityMapping.keySet()) {
                if (getCityAt(redCity).getOwner() == Player.RED) {
                    getCityAt(redCity).setResource(resource);

                    int unitCost = unitAvailableStrategy.unitCostOf(getCityAt(redCity).getProduction());
                    if (getCityAt(redCity).getResource() >= unitCost) {
                        createUnit(redCity, new UnitImpl(getCityAt(redCity).getProduction(), Player.BLUE, unitAvailableStrategy.getAtt(getCityAt(redCity).getProduction()), unitAvailableStrategy.getDef(getCityAt(redCity).getProduction())));
                        getCityAt(redCity).setResource(-unitCost);
                    }


                }
            }
        }
        winner = winnerStrategy.checkWinner(this);

    }



    public void createUnit(Position cityPosition, UnitImpl createdUnit) {

        if (!unitMapping.containsKey(cityPosition)) {

            unitMapping.put(cityPosition, createdUnit);
        } else if (!unitMapping.containsKey(new Position(cityPosition.getRow(), cityPosition.getColumn() - 1))) {
            unitMapping.put(new Position(cityPosition.getRow(), cityPosition.getColumn() - 1), createdUnit);
        } else if (!unitMapping.containsKey(new Position(cityPosition.getRow() + 1, cityPosition.getColumn() - 1))) {
            unitMapping.put(new Position(cityPosition.getRow() + 1, cityPosition.getColumn() - 1), createdUnit);
        } else if (!unitMapping.containsKey(new Position(cityPosition.getRow() + 1, cityPosition.getColumn()))) {
            unitMapping.put(new Position(cityPosition.getRow() + 1, cityPosition.getColumn()), createdUnit);
        } else if (!unitMapping.containsKey(new Position(cityPosition.getRow() + 1, cityPosition.getColumn() + 1))) {
            unitMapping.put(new Position(cityPosition.getRow() + 1, cityPosition.getColumn() + 1), createdUnit);
        } else if (!unitMapping.containsKey(new Position(cityPosition.getRow(), cityPosition.getColumn() + 1))) {
            unitMapping.put(new Position(cityPosition.getRow(), cityPosition.getColumn() + 1), createdUnit);
        }


    }


    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        getCityAt(p).setUnitInProduction(unitType);

    }

    public void performUnitActionAt(Position p) {
        actionstrategy.performAction(p, this);
    }



}
