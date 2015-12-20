package hotciv.framework;

/**
 * Created by simon on 17-12-2015.
 */
public interface UnitAvailableStrategy {

    public int unitCostOf(String unitType);
    public int getAtt(String unitType);
    public int getDef(String unitType);
}
