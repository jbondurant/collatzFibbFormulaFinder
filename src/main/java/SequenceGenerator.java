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
        int bMax = 10;
        int n = 10000;
        int maxStepsWithoutHittingVisited = n*10000;
        for(int a=1; a<aMax; a++){
            System.out.println("a is " + a);
            for(int b=1; b<bMax; b++){
                CollatzResult cr = doCollatzWithDiffNumsUpToN(a, b, n, maxStepsWithoutHittingVisited);
                if(cr.equals(CollatzResult.goesToOne)){
                    System.out.println(a + "\t" + b);
                }
            }
        }
    }

    public static CollatzResult doCollatzWithDiffNumsUpToN(int a, int b, int n, int maxStepsWithoutHittingVisited){
        HashSet<BigInteger> numsThatGoToOne = new HashSet<>();
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigB = BigInteger.valueOf(b);
        BigInteger big0 = BigInteger.ZERO;
        BigInteger big1 = BigInteger.ONE;
        int numSteps = 0;
        for(int i=1; i<n; i++){
            HashSet<BigInteger> numsVisitedCurrIVal = new HashSet<>();
            BigInteger curr = BigInteger.valueOf(i);
            while(!curr.equals(big1)){
                if(numsVisitedCurrIVal.size()<5000) {
                    numsVisitedCurrIVal.add(curr);
                }
                if(numSteps >= maxStepsWithoutHittingVisited){
                    return CollatzResult.divergesOrLoopTooLong;
                }
                if(curr.mod(bigA).equals(big0)){
                    curr = curr.divide(bigA);
                }
                else{
                    curr = curr.multiply(bigB).add(big1);//hardcoded
                }
                numSteps++;
            }
            if(numsThatGoToOne.size()<1000000) {
                numsThatGoToOne.addAll(numsVisitedCurrIVal);
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
