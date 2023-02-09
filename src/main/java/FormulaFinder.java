import java.util.ArrayList;
import java.util.HashMap;

public class FormulaFinder {

    public static void hardcodedFibFinder(double a1, double a2, double a3){
        int fibTop = 1000;
        ArrayList<Integer> fib = SequenceGenerator.fibonacci(1000,5);
        HashMap<Integer, Integer> pairs = SequenceGenerator.strictSequenceToPairs(fib);
        for(int p1 : pairs.keySet()){
            int p2 = pairs.get(p1);
            double constantDouble = (a1 + Math.sqrt(a2)) / a3;
            int p2Actual = (int) Math.round(constantDouble * (p1*1.0));
            if(p2 != p2Actual) {
                return;
            }
        }
        System.out.println("a1 is: " + a1 + " a2 is: " + a2 + " a3 is: " + a3);


    }


    public static void main(String[] args){
        hardcoded3Iterator(10);
    }

    public static void hardcoded3Iterator(int top){
        for(int a1=0; a1<top; a1++){
            for(int a2=0; a2<top; a2++){
                for(int a3=0; a3<top; a3++){
                    hardcodedFibFinder(a1 * 1.0, a2 *1.0, a3 * 1.0);
                }
            }
        }
    }
}
