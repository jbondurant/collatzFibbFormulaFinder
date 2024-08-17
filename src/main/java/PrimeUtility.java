import java.util.ArrayList;
import java.util.Collections;

public class PrimeUtility {//found online, didn't write this

    public static ArrayList<Integer> getFirstNPrimesInReverse(int n) {
        ArrayList<Integer> firstNPrimes = new ArrayList<Integer>();
        int status = 1;
        int num = 3;
        firstNPrimes.add(2);
        for (int i = 2; i <= 100; ) {
            if (firstNPrimes.size() == n) {
                Collections.reverse(firstNPrimes);
                return firstNPrimes;
            }
            for (int j = 2; j <= Math.sqrt(num); j++) {
                if (num % j == 0) {
                    status = 0;
                    break;
                }
            }
            if (status != 0) {
                firstNPrimes.add(num);
                i++;
            }
            status = 1;
            num++;
        }
        Collections.reverse(firstNPrimes);
        return firstNPrimes;
    }
}