import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CollatzRunnerThread implements Runnable{
    ArrayList<Integer> divMods;
    int mult;
    int plus;
    CountDownLatch cdl;

    CollatzRunnerThread(ArrayList<Integer> divModsVal, int multVal, int plusVal, CountDownLatch cdlVal){
        divMods = divModsVal;
        mult = multVal;
        plus = plusVal;
        cdl = cdlVal;
    }

    @Override
    public void run(){
        SequenceGenerator.doCollatsSpecificTuple(divMods, mult, plus);
        cdl.countDown();
    }
}
