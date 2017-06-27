package aslibrary.util;

/**
 * Created by gravit on 27.06.17.
 */
public class CodeExecutionTime {
    private CodeExecutionTime()
    {

    }
    public static long get(Runnable run)
    {
        long start = System.currentTimeMillis();
        run.run();
        long stop = System.currentTimeMillis();
        return stop - start;
    }
    public static double getAverage(Runnable run, long replay)
    {
        long mainTime = 0;
        for(int i=0;i<replay;i++) {
            long start = System.currentTimeMillis();
            run.run();
            long stop = System.currentTimeMillis();
            mainTime += stop - start;
        }
        return mainTime / replay;
    }
}
