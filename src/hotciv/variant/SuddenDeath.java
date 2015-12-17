package hotciv.variant;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;

/**
 * Created by simon on 10-12-2015.
 */
public class SuddenDeath implements WinnerStrategy {
private ConquestWinnerStrategy conquest;
    private FirstToThree firstToThree;
    private WinnerStrategy state;

    public SuddenDeath(){
        conquest = new ConquestWinnerStrategy();
        firstToThree = new FirstToThree();
    }

    @Override
    public Player checkWinner(Game game) {
        if (game.getAge() < -2000){
            state = conquest;
        }else {
            state = firstToThree;
        }
        return state.checkWinner(game);

    }

    @Override
    public void increaseAttackCounter(Game game) {
        if (game.getAge() > -2000){
            firstToThree.increaseAttackCounter(game);
        }
    }
}
