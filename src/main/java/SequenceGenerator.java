import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.jar.JarEntry;

public class SequenceGenerator {
    public static ArrayList<Integer> fibonacci(int top){
        int i = 0;
        ArrayList<Integer> allFib = new ArrayList<>();
        allFib.add(0);
        allFib.add(1);
        while(allFib.get(allFib.size()-1) < top){
            allFib.add(allFib.get(allFib.size()-1) + allFib.get(allFib.size()-2));
        }
        return allFib;
    }

    public static ArrayList<Integer> fibonacci(int top, int prune){
        ArrayList<Integer> fib = SequenceGenerator.fibonacci(top);
        fib = (ArrayList<Integer>) new ArrayList<Integer>(fib.subList(prune, fib.size()));
        return fib;
    }

    public static HashMap<Integer, Integer> strictSequenceToPairs(ArrayList<Integer> sequence){
        HashMap<Integer, Integer> pairs = new HashMap<>();
        for(int i=0; i<sequence.size()-1; i++){
            pairs.put(sequence.get(i), sequence.get(i+1));
        }
        return pairs;
    }

    public static HashMap<Integer, Integer> collatzNumStepsUpOrDown(int top, int first, boolean isStepsUp){
        HashMap<Integer, Integer> collatzStepsInDir = new HashMap();
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

    public static HashMap<Integer, Integer> reverseCollatzStepsUp(int top, int start, int freeDownSteps){
        HashMap<Integer, HashSet<Integer>> collatzStepsInDir = new HashMap();
        HashSet setAtZeroSteps = new HashSet();
        setAtZeroSteps.add(1);
        collatzStepsInDir.put(0, setAtZeroSteps);
        for(int a=1; a<=top; a++){
            //hmmm im not calculating this properly
            for(int lastStepInts : collatzStepsInDir.get(a-1)){
                //do the down steps
                HashSet<Integer> setAtPreviousStep = new HashSet<>();
                HashSet<Integer> setAtCurrentStep = new HashSet<>();
                if(a % 6 == 4){

                }
                //do up step

            }
        }
    }

    public static void main(String[] args){
        //ArrayList<Integer> fib = fibonacci(100, 5);
        //strictSequenceToPairs(fib);

        collatzNumStepsUpOrDown(1025,975, false);
    }



}
