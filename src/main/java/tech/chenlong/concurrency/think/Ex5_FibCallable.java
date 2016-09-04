package tech.chenlong.concurrency.think;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by longchen on 8/28/16.
 * exercise 5 use callable return fibonacci sum value
 */
public class Ex5_FibCallable {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            results.add(exec.submit(new FibonacciSum(i)));
        }
        for (Future<Integer> fs : results)
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                return;
            } catch (ExecutionException e) {
                System.out.println(e.getMessage());
            } finally {
                exec.shutdown();
            }

    }
}

class FibonacciSum implements Callable<Integer> {

    private int num;

    FibonacciSum(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int sum;
        if (num <= 0)
            throw new IllegalArgumentException();
        else if (num == 1)
            sum = 0;
        else if (num == 2)
            sum = 1;
        else {
            int first = 0, second = 1;
            int temp;
            sum = 1;
            for (int i = 3; i <= num; i++) {
                temp = first + second;
                first = second;
                second = temp;
                sum = sum + temp;
            }
        }
        System.out.println(Thread.currentThread().getName() + ", " + sum);
        return sum;
    }
}
