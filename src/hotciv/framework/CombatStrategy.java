package hotciv.framework;

/**
 * Created by simon on 03-12-2015.
 */
public interface CombatStrategy {

    public boolean attackerWins(Game game, Position attacker, Position defender);
}
