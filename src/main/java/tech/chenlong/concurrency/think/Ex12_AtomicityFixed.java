package tech.chenlong.concurrency.think;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by longchen on 9/4/16.
 * Atomicity.java fix
 */
public class Ex12_AtomicityFixed implements Runnable {

    private int i = 0;

    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i ++;
        i ++;
    }

    @Override
    public void run() {
        while (true)
            evenIncrement();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Ex12_AtomicityFixed atomicityFixed = new Ex12_AtomicityFixed();
        exec.execute(atomicityFixed);
        while (true) {
            int value = atomicityFixed.getValue();
            if (value % 2 != 0) {
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}
