package tech.chenlong.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenlong on 2016/8/18
 * exercise 2
 */
public class Ex2_Fibonacci implements Runnable {

    private int num;

    public Ex2_Fibonacci(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        int first = 0;
        int second = 1;
        int temp;
        String s = String.valueOf(first) + ", " + String.valueOf(second) + ", ";
        for (int i = 2; i < num; i ++) {
            temp = first + second;
            first = second;
            second = temp;
            if (i == num -1)
                s += String.valueOf(second);
            else
                s += String.valueOf(second) + ", ";
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " || " + s);
    }

    public static void main(String[] args) {
        // 使用线程创建任务
        for (int i = 0; i < 100; i++) {
            new Thread(new Ex2_Fibonacci(10), "thread" + i).start();
        }

        // 使用Executor管理Thread对象
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            exec.execute(new Thread(new Ex2_Fibonacci(10)));
        }
        exec.shutdown();
    }
}
