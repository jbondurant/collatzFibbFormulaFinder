import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

public class SequenceGenerator {

    //if plusMax is smaller than my num threads, it stops

    // 3 works when biggest prime is 2, 3, 7, 11, 13, 17, 19
    // 5 works when biggest prime is 3, 5, 7, 11, 13, 17, 19
    // 7 works when biggest prime is 5, 7, 11, 13, 17, 19
    // 11& works when biggest prime is 5, 17, 19
    // 13*& works when biggest prime is 19
    // 17& works when biggest prime is 11
    // 19 works when biggest prime is 13, 17, 19...
    // 23*& works when biggest prime is 29, 31, 37...
    // 29 works when biggest prime is 17, 19, 23...
    // 31*& works when biggest prime is 43, 47, 53...
    // 37 works when biggest prime is 23, 29, 31...
    // 41 works when biggest prime is 17, 19, 23...
    // 43 works when biggest prime is 31, 37, 41
    // 47& works when biggest prime is 29, 31
    // 53 works when biggest prime is 31, 37, 43....
    // 59* works when biggest prime is 73, 79, 83
    // 61 works when biggest prime is 37, 41
    // 67 works when biggest prime is 41
    // 71 works when biggest prime is 31, 37, 53...
    // ok yeah no pattern emerges, oh well:)



    public static void doCollatsSpecificTuple(SingleCollatzRunConfiguration singleCollatzRunConfiguration, int hardcodedMultiplier){

        if(singleCollatzRunConfiguration.multiplier!=hardcodedMultiplier || singleCollatzRunConfiguration.plusValue!= 1){
            return;
        }
        CollatzResult cr = doCollatzWithDiffNumsUpToN(singleCollatzRunConfiguration);
        if (cr.equals(CollatzResult.goesToOne) || true) {
            System.out.println("collatz result:\t" + cr
                    + "\tdivMods\t" + singleCollatzRunConfiguration.divModsInts
                    + "\tmult\t" + singleCollatzRunConfiguration.multiplier
                    + "\tplus\t" + singleCollatzRunConfiguration.plusValue);
        }



    }

    public static ArrayList<BigInteger> getBigIntegerList(ArrayList<Integer> numbers){
        ArrayList<BigInteger> bigInts = new ArrayList<>();
        for(int number : numbers){
            bigInts.add(BigInteger.valueOf(number));
        }
        return bigInts;
    }

    public static CollatzResult doCollatzWithDiffNumsUpToN(SingleCollatzRunConfiguration singleCollatzRunConfiguration) {
        ArrayList<Integer> divModsInts = singleCollatzRunConfiguration.divModsInts;
        int mult = singleCollatzRunConfiguration.multiplier;
        int plus = singleCollatzRunConfiguration.plusValue;
        int n = singleCollatzRunConfiguration.firstNTested;
        int maxStepsWithoutHittingVisited = singleCollatzRunConfiguration.maxStepsWithoutHittingVisited;

        //divModsInts.add(0,9);
        //something is wrong with the logic here it says loops when it should diverge.
        //divModsInts.remove(3);
        //divModsInts.remove(2);
        //divModsInts.remove(1);
        divModsInts.remove(0);
        ArrayList<BigInteger> divMods = getBigIntegerList(divModsInts);

        boolean maxOneDivMod = false;//this variable only makes sense if either
        //1) one of the divMod is not prime
        //or
        // 2) we don't check if (!gotDivided) { before doing mult and add
        //otherwise it doesn't affect the behaviour.

        //there's a lot of interesting stuff going on with the caching.
        //why am i not using numsVisitedCurrIVal to check if i already visited a value within a run to find a loop
        int numSteps = 0;
        for (int i = 1; i < n; i++) {
            HashSet<BigInteger> numsVisitedCurrIVal = new HashSet<>();
            BigInteger curr = BigInteger.valueOf(i);
            int start = i;
            HashSet<BigInteger> numsVisitedCurrentRunCheckForLoop = new HashSet<>();
            if (i != 1) {
                numsVisitedCurrentRunCheckForLoop.add(curr);
            }
            if (i == 2) {
                int k = 1;
            }
            while (!curr.equals(BigInteger.ONE)) {
                if (numsVisitedCurrIVal.size() < 5000) {
                    numsVisitedCurrIVal.add(curr);
                }
                if (numSteps >= maxStepsWithoutHittingVisited) {
                    return CollatzResult.doesNotConvergeWithinBoundSteps;
                }
                boolean gotDivided = false;
                for (BigInteger divMod : divMods) {
                    if (curr.mod(divMod).equals(BigInteger.ZERO)) {
                        curr = curr.divide(divMod);
                        gotDivided = true;
                        if (numsVisitedCurrentRunCheckForLoop.contains(curr)) {
                            //System.out.println("Loops when start at\t" + start);
                            return CollatzResult.loops;
                        }
                        numsVisitedCurrentRunCheckForLoop.add(curr);
                        if (maxOneDivMod) {
                            break;
                        }
                    }
                }
                if (!gotDivided) {
                    curr = curr.multiply(BigInteger.valueOf(mult)).add(BigInteger.valueOf(plus));
                    if (numsVisitedCurrentRunCheckForLoop.contains(curr)) {
                        //System.out.println("Loops when start at\t" + start);
                        return CollatzResult.loops;
                    }
                    numsVisitedCurrentRunCheckForLoop.add(curr);
                }//hardcoded
                numSteps++;
            }
        }
        return CollatzResult.goesToOne;
    }



}
