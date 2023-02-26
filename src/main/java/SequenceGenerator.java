import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.JarEntry;

public class SequenceGenerator {

    public static void doCollatsTuplesMultipleThreads(int multMax, int plusMax) throws Exception {
        int numMaxThreads = 3;
        ArrayList<Integer> divMods = new ArrayList<>();
        divMods.add(3);
        divMods.add(2);
        for (int mult = 1; mult <= multMax; mult++) {
            System.out.println("mult is " + mult);
            CountDownLatch cdl = new CountDownLatch(multMax);
            ExecutorService pool = Executors.newFixedThreadPool(numMaxThreads);

            for (int plus = 1; plus <= plusMax; plus++) {
                System.out.println("plus is " + plus);
                Runnable r = new CollatzRunnerThread(divMods, mult, plus, cdl);
                pool.execute(r);
            }
            cdl.await();
            pool.shutdown();
        }
    }

    public static void doCollatsSpecificTuple(ArrayList<Integer> divMods, int mult, int plus){
        int n = 1000;
        int maxStepsWithoutHittingVisited = n*100;

        CollatzResult cr = doCollatzWithDiffNumsUpToN(divMods, mult, plus, n, maxStepsWithoutHittingVisited);
        if (cr.equals(CollatzResult.goesToOne)) {
            System.out.println(divMods + "\t" + mult + "\t" + plus);
        }



    }

    public static ArrayList<BigInteger> getBigIntegerList(ArrayList<Integer> numbers){
        ArrayList<BigInteger> bigInts = new ArrayList<>();
        for(int number : numbers){
            bigInts.add(BigInteger.valueOf(number));
        }
        return bigInts;
    }

    public static CollatzResult doCollatzWithDiffNumsUpToN(ArrayList<Integer> divModsInts, int mult, int plus, int n, int maxStepsWithoutHittingVisited){
        HashSet<BigInteger> numsThatGoToOne = new HashSet<>();
        ArrayList<BigInteger> divMods = getBigIntegerList(divModsInts);
        boolean maxOneDivMod = true;

        int numSteps = 0;
        for(int i=1; i<n; i++){
            HashSet<BigInteger> numsVisitedCurrIVal = new HashSet<>();
            BigInteger curr = BigInteger.valueOf(i);
            while(!curr.equals(BigInteger.ONE)) {
                if (numsVisitedCurrIVal.size() < 5000) {
                    numsVisitedCurrIVal.add(curr);
                }
                if (numSteps >= maxStepsWithoutHittingVisited) {
                    return CollatzResult.divergesOrLoopTooLong;
                }
                boolean gotDivided = false;
                for (BigInteger divMod : divMods) {
                    if (curr.mod(divMod).equals(BigInteger.ZERO)) {
                        curr = curr.divide(divMod);
                        gotDivided = true;
                        if (maxOneDivMod) {
                            break;
                        }
                    }
                }
                if (!gotDivided) {
                    curr = curr.multiply(BigInteger.valueOf(mult)).add(BigInteger.valueOf(plus));
                }//hardcoded
                numSteps++;
                if (numsThatGoToOne.size() < 100000) {
                    numsThatGoToOne.addAll(numsVisitedCurrIVal);
                }
            }
        }
        return CollatzResult.goesToOne;
    }

    public static void main(String[] args) throws Exception {
        //ArrayList<Integer> fib = fibonacci(100, 5);
        //strictSequenceToPairs(fib);

        //collatzNumStepsUpOrDown(1025,975, false);
        doCollatsTuplesMultipleThreads(10, 10);
    }



}
