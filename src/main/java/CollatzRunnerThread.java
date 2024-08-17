import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class CollatzRunnerThread implements Runnable{
    SingleCollatzRunConfiguration singleCollatzRunConfiguration;
    CountDownLatch cdl;
    int hardcodedMultiplier;

    CollatzRunnerThread(int numDivMods, int mult, int plus, int n, int maxSteps, CountDownLatch cdlVal, int hm){
        singleCollatzRunConfiguration = new SingleCollatzRunConfiguration(numDivMods, mult, plus, n, maxSteps);
        cdl = cdlVal;
        hardcodedMultiplier = hm;
    }

    @Override
    public void run(){
        SequenceGenerator.doCollatsSpecificTuple(singleCollatzRunConfiguration, hardcodedMultiplier);
        cdl.countDown();
    }
}
