package hotciv.variant;

import hotciv.framework.LayoutStrategy;
import hotciv.framework.Position;

/**
 * Created by Liam on 26-11-2015.
 */
public class AlphaLayoutStrategy implements LayoutStrategy {


    @Override
   public int redCityXPosition(){
        return 1;
   }
    @Override
    public int redCityYPosition(){
        return 1;
    }

    @Override
    public int blueCityXPosition() {
        return 4;
    }

    @Override
    public int blueCityYPosition() {
        return 1;
    }
}
