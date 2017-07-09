package aslibrary.multithread;

import aslibrary.LogicException;

/**
 * Created by gravit on 06.07.17.
 */
public class ThreadJobber {
    public JobThread[] threads;
    public Runnable run;

    public ThreadJobber(int amount) throws LogicException {
        if (amount <= 0) throw new LogicException("Amount <= 0");
        threads = new JobThread[amount];
    }

    public void fill(Runnable runs) {
        for (JobThread v : threads) {
            v.runnable = runs;
        }
    }
    public void fillNull() {
        for (JobThread v : threads) {
            v.runnable = null;
        }
    }

    public void run() {
        for (JobThread v : threads) {
            v.run();
        }
    }

    public void join() throws InterruptedException {
        for (JobThread v : threads) {
            v.join();
        }
    }
}

class JobThread extends Thread {
    public Runnable runnable;

    @Override
    public void run() {
        runnable.run();
    }
}