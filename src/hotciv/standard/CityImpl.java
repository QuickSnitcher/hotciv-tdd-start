package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created by simon on 09-11-2015.
 */
public class CityImpl implements City {
    private Player owner;
    private int resource = 0;
    private String unitInProduction = null;


    public CityImpl(Player owner){
        this.owner = owner;
    }


    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return unitInProduction;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    @Override
    public int getResource(){
        return resource;
    }

    public void setUnitInProduction(String newProductionUnit){
        unitInProduction = newProductionUnit;
    }

    public void setResource(int updateResource){
        resource = updateResource;
    }


}
