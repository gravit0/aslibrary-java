package aslibrary.util;

import aslibrary.LogicException;
import aslibrary.multithread.ThreadJobber;

/**
 * Created by gravit on 27.06.17.
 */
public class CodeExecutionTime implements java.lang.AutoCloseable {
    long start_time =0;
    String name = "CodeExecutionTime ";
    public CodeExecutionTime(String n) {
        start_time = System.currentTimeMillis();
        name = n;
    }

    public static long get(Runnable run) {
        long start = System.currentTimeMillis();
        run.run();
        long stop = System.currentTimeMillis();
        return stop - start;
    }

    public static double getAverage(Runnable run, long replay) {
        long mainTime = 0;
        for (int i = 0; i < replay; i++) {
            long start = System.currentTimeMillis();
            run.run();
            long stop = System.currentTimeMillis();
            mainTime += stop - start;
        }
        return mainTime / replay;
    }

    public static double getMultiThread(Runnable runnable, ThreadJobber jobber) {
        long start = 0;
        try {
            jobber.fill(runnable);
            start = System.currentTimeMillis();
            jobber.run();
            jobber.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stop = System.currentTimeMillis();
        return stop - start;
    }

    public static double getMultiThread(Runnable runnable, int threads) {
        ThreadJobber jobber = null;
        try {
            jobber = new ThreadJobber(threads);
        } catch (LogicException e) {
            e.printStackTrace();
        }
        return getMultiThread(runnable, jobber);
    }

    @Override
    public void close() {
        long stop_time = System.currentTimeMillis();
        System.out.print(name);
        System.out.print(stop_time - start_time);
        System.out.println();
    }
}
