package tech.chenlong.concurrency.think;

/**
 * Created by chenlong on 2016/8/18
 * exercise 2
 */
public class Fibonacci implements Runnable {

    private int num;

    public Fibonacci(int num) {
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
        }
        System.out.println(Thread.currentThread().getName() + " || " + s);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Fibonacci(10), "thread" + i).start();
        }
    }
}
