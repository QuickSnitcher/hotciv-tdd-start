package hotciv.variant;

import hotciv.framework.GameConstants;
import hotciv.framework.UnitAvailableStrategy;

/**
 * Created by simon on 17-12-2015.
 */
public class NoChariotAvailable implements UnitAvailableStrategy {
    @Override
    public int unitCostOf(String unitType) {
        switch (unitType) {
            case GameConstants.ARCHER:
                return 10;
            case GameConstants.LEGION:
                return 15;
            case GameConstants.SETTLER:
                return 30;
        }
        return 0;
    }

    @Override
    public int getAtt(String unitType) {
        switch (unitType) {
            case GameConstants.ARCHER:
                return 2;
            case GameConstants.LEGION:
                return 4;
            case GameConstants.SETTLER:
                return 0;
        }
        return 0;
    }

    @Override
    public int getDef(String unitType) {
        switch (unitType) {
            case GameConstants.ARCHER:
                return 3;
            case GameConstants.LEGION:
                return 2;
            case GameConstants.SETTLER:
                return 3;
        }
        return 0;
    }

}
