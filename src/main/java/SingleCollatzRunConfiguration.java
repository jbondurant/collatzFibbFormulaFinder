import java.util.ArrayList;

public class SingleCollatzRunConfiguration {
    public ArrayList<Integer> divModsInts;
    int multiplier;
    int plusValue;
    int firstNTested;
    int maxStepsWithoutHittingVisited;


    SingleCollatzRunConfiguration(int numberOfPrimesForDivModsInts, int mult, int plus, int n, int maxStepsPerRun) {
        divModsInts = PrimeUtility.getFirstNPrimesInReverse(numberOfPrimesForDivModsInts);
        multiplier = mult;
        plusValue = plus;
        firstNTested = n;
        maxStepsWithoutHittingVisited = maxStepsPerRun;
    }
}
