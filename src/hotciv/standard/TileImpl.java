package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created by Liam on 09-11-2015..
 */
public class TileImpl implements Tile {
    private String type;
    public TileImpl(String type) {
        this.type = type;
    }


    @Override
    public String getTypeString() {
        return type;
    }

    public void setTileAt(Position p, Tile tile){

    }
}
