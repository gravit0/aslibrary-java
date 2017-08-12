package aslibrary.multithread;

import aslibrary.LogicException;

/**
 * Created by gravit on 06.07.17.
 */
public class ThreadJobber {
    protected JobThread[] threads;

    public ThreadJobber(int amount) {
        if (amount <= 0) throw new LogicException("Amount <= 0");
        threads = new JobThread[amount];
    }

    public void fill(Runnable runs) {
        for (JobThread v : threads) {
            v.runnable = runs;
        }
    }

    public void clear() {
        fill(null);
    }

    public void run() {
        for (JobThread v : threads) {
            //v.run();
            v.start();
        }
    }

    public void join() throws InterruptedException {
        for (JobThread v : threads) {
            v.join();
        }
    }
}

class JobThread extends Thread {
    protected Runnable runnable;

    @Override
    public void run() {
        runnable.run();
    }
}