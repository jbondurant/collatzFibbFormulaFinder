import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;

class PrimeUtilityTest {

    @org.junit.jupiter.api.Test
    void getFirstNPrimesInReverseWith10() {
        ArrayList<Integer> actualResult = PrimeUtility.getFirstNPrimesInReverse(10);
        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(29, 23, 19, 17, 13, 11, 7, 5, 3, 2));
        Assertions.assertArrayEquals(actualResult.toArray(), expectedResult.toArray());
    }
}