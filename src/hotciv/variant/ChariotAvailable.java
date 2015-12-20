package hotciv.variant;

import hotciv.framework.GameConstants;
import hotciv.framework.ThetaConstants;
import hotciv.framework.UnitAvailableStrategy;

/**
 * Created by simon on 17-12-2015.
 */
public class ChariotAvailable implements UnitAvailableStrategy {

    private UnitAvailableStrategy unitAvailableStrategy;

    public ChariotAvailable(UnitAvailableStrategy unitAvailableStrategy) {
        this.unitAvailableStrategy = unitAvailableStrategy;
    }

    @Override
    public int unitCostOf(String unitType) {
        if (unitType.equals(ThetaConstants.CHARIOT)){
            return 20;
        }else{
            return unitAvailableStrategy.unitCostOf(unitType);
        }
    }

    @Override
    public int getAtt(String unitType) {
        if (unitType.equals(ThetaConstants.CHARIOT)){
            return 3;
        }else{
            return unitAvailableStrategy.getAtt(unitType);
        }
    }

    @Override
    public int getDef(String unitType) {
        if (unitType.equals(ThetaConstants.CHARIOT)){
            return 1;
        }else{
            return unitAvailableStrategy.getDef(unitType);
        }
    }

}
