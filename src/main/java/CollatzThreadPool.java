import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CollatzThreadPool {

    public static void main(String[] args) throws Exception {
        doCollatsTuplesMultipleThreads();
    }
    public static void doCollatsTuplesMultipleThreads() throws Exception {
        int multMax = 71;
        int hardcodedMultiplier = multMax;
        int plusMax = 1;
        int numMaxThreads = 1;
        int numDivMods = 30; //1:2 2:3 3:5 4:7 5:11 6:13 7:17 8:19 9:23
        int checkUntilN = 1000;
        int maxSteps = checkUntilN * 100;
        CountDownLatch cdl = new CountDownLatch(multMax);
        ExecutorService pool = Executors.newFixedThreadPool(numMaxThreads);
        for (int currNumDiv = 1; currNumDiv <= numDivMods; currNumDiv++) {
            for (int mult = 1; mult <= multMax; mult++) {
                for (int plus = 1; plus <= plusMax; plus++) {
                    Runnable r = new CollatzRunnerThread(currNumDiv, mult, plus, checkUntilN, maxSteps, cdl, hardcodedMultiplier);
                    pool.execute(r);
                }
            }
        }
        cdl.await();
        pool.shutdown();
    }
}