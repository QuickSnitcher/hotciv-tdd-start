package hotciv.standard;

import hotciv.framework.LayoutStrategy;

/**
 * Created by Liam on 26-11-2015.
 */
public class DeltaLayoutStrategy implements LayoutStrategy {
    @Override
    public int redCityXPosition() {
        return 8;
    }

    @Override
    public int redCityYPosition() {
        return 12;
    }

    @Override
    public int blueCityXPosition() {
        return 4;
    }

    @Override
    public int blueCityYPosition() {
        return 5;
    }
}
