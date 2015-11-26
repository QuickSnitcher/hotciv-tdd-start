package hotciv.variant;

import hotciv.framework.AgingStrategy;

/**
 * Created by simon on 23-11-2015.
 */
public class BetaCivAgingStrategy implements AgingStrategy {

    private boolean hasBeenZero = false;

    @Override
    public int calculateAge(int ageSoFar) {

        if(ageSoFar <= -100){
            ageSoFar += 100;
        }
        else if(ageSoFar == 0 && hasBeenZero == false){

            ageSoFar -= 1;
        }
        else if (ageSoFar == -1){
            ageSoFar +=1;
            hasBeenZero = true;
        }
        else if (ageSoFar == 0 && hasBeenZero == true){
            ageSoFar += 50;
        }
        else if (ageSoFar >= 50 && ageSoFar < 1750){
            ageSoFar += 50;
        }
        else if (ageSoFar >= 1750 && ageSoFar < 1900){
            ageSoFar += 25;
        }
        else if (ageSoFar >= 1900 && ageSoFar < 1970){
            ageSoFar += 5;
        }
        else if (ageSoFar >= 1970){
            ageSoFar += 1;
        }

        return ageSoFar;
    }
}
