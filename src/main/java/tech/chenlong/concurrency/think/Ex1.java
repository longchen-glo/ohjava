package tech.chenlong.concurrency.think;


/**
 * Created by longchen on 16-8-17
 * exercise 1
 */
public class Ex1 implements Runnable {


    private Ex1(String name) {
        System.out.println(name + " start");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " process" + i);
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " stop");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Ex1("thread" + i), "thread" + i).start();
        }
    }
}
