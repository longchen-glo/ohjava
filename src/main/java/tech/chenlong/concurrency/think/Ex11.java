package tech.chenlong.concurrency.think;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenlong on 2016/8/23.
 * exercise 11
 */
public class Ex11 {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService executor = Executors.newCachedThreadPool();
        Tank tank = new Tank();
        for (int i = 0; i < 10; i++) {
            executor.execute(new ConsistencyChecker(tank));
        }
        Thread.yield();
        executor.shutdown();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught  " + e);
    }
}

class Tank{

    enum State {EMPTY, LOADED}

    private State state = State.EMPTY;
    private int currentLoad = 0;

    public void validate() {
        if ((state == State.EMPTY && currentLoad != 0) ||
                (state == State.LOADED && currentLoad != 10))
            throw new IllegalStateException();
    }

    public void fill() {
        state = State.LOADED;
        Thread.yield();
        currentLoad = 10;
    }

    public void drain() {
        state = State.EMPTY;
        Thread.yield();
        currentLoad = 0;
    }
}

class ConsistencyChecker implements Runnable {

    private static Random random = new Random();

    private Tank tank;

    public ConsistencyChecker(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void run() {
        for (;;) {
            if (random.nextBoolean()) {
                tank.fill();
                System.out.println(Thread.currentThread().getName() + "|filled");
            }
            else {
                tank.drain();
                System.out.println(Thread.currentThread().getName() + "|drain");
            }
            tank.validate();
        }
    }
}
