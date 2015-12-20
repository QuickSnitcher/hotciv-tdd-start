package hotciv.framework;

/**
 * Created by Liam on 17-12-2015.
 */
public interface GameImplFactory  {

    public ActionStrategy createActionStrategy();
    public AgingStrategy createAgingStrategy();
    public LayoutStrategy createLayoutStrategy();
    public WinnerStrategy createWinnerStrategy();
    public CombatStrategy createCombatStrategy();
    public UnitAvailableStrategy unitAvailableStrategy();

}
