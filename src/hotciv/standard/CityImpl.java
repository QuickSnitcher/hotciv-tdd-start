package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created by simon on 09-11-2015.
 */
public class CityImpl implements City {
    private Player owner;
    private int resource = 0;


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
        return "archer";
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    @Override
    public int getResource(){
        return resource;
    }



    public void setResource(int updateResource){
        resource = updateResource;
    }


}
