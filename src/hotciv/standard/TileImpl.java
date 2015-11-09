package hotciv.standard;

import hotciv.framework.Tile;

/**
 * Created by simon on 09-11-2015.
 */
public class TileImpl implements Tile {
private String type;
    public TileImpl(String type){
        this.type = type;
    }
    @Override
    public String getTypeString() {

        return type;
    }
}
