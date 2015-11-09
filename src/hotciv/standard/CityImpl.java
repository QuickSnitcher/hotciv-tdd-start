package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created by simon on 09-11-2015.
 */
public class CityImpl implements City {
    private Player owner;

    public CityImpl(Player owner){
        this.owner = owner;
    }
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getProduction() {
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
