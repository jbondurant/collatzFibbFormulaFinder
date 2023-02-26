import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.jar.JarEntry;

public class SequenceGenerator {

    public static HashMap<Integer, Integer> strictSequenceToPairs(ArrayList<Integer> sequence){
        HashMap<Integer, Integer> pairs = new HashMap<>();
        for(int i=0; i<sequence.size()-1; i++){
            pairs.put(sequence.get(i), sequence.get(i+1));
        }
        return pairs;
    }

    public static HashMap<Integer, Integer> collatzNumStepsUpOrDown(int top, int first, boolean isStepsUp){
        HashMap<Integer, Integer> collatzStepsInDir = new HashMap<>();
        for(int a = first; a <= top; a++){
            int currVal = a;
            int numStepsInDir = 0;
            while(currVal != 1){
                if(currVal % 2 == 0){
                    if(!isStepsUp){
                        numStepsInDir++;
                    }
                    currVal = currVal / 2;
                }
                else{
                    if(isStepsUp) {
                        numStepsInDir++;
                    }
                    currVal = 3 * currVal + 1;
                }
            }
            collatzStepsInDir.put(a, numStepsInDir);
        }
        System.out.println(collatzStepsInDir);
        return collatzStepsInDir;
    }

    public static void doCollatsDuos(){
        int aMax = 10;
        int multMax = 10;
        int plusMax = 10;
        int n = 1000;
        int maxStepsWithoutHittingVisited = n*100;
        ArrayList<Integer> divMods = new ArrayList<>();
        divMods.add(2);
        for(int a=1; a<aMax; a++){
            System.out.println("a is " + a);
            for(int mult=1; mult<multMax; mult++){
                System.out.println("mult is " + mult);
                for(int plus=1; plus<plusMax; plus++) {
                    System.out.println("plus is " + plus);

                    CollatzResult cr = doCollatzWithDiffNumsUpToN(divMods, mult, plus, n, maxStepsWithoutHittingVisited);
                    if (cr.equals(CollatzResult.goesToOne)) {
                        System.out.println(a + "\t" + mult);
                    }
                }
            }
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

    public static void main(String[] args){
        //ArrayList<Integer> fib = fibonacci(100, 5);
        //strictSequenceToPairs(fib);

        //collatzNumStepsUpOrDown(1025,975, false);
        doCollatsDuos();
    }



}
