import java.util.ArrayList;

public class CollatzRunnerThread implements Runnable{
    ArrayList<Integer> divMods;
    int mult;
    int plus;

    CollatzRunnerThread(ArrayList<Integer> divModsVal, int multVal, int plusVal){
        divMods = divModsVal;
        mult = multVal;
        plus = plusVal;
    }

    @Override
    public void run(){
        SequenceGenerator.doCollatsSpecificTuple(divMods, mult, plus);
    }
}
