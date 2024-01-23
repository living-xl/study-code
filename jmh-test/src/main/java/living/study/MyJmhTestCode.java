package living.study;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class MyJmhTestCode {
    static int[] ARRAY =new int[1000_000_00];
    static {
        Arrays.fill(ARRAY,1);
    }
    @Benchmark
    public int test1() throws ExecutionException, InterruptedException {
        int[] array = ARRAY;
        FutureTask<Integer> t1 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_00; i++) {
                sum += array[0 + i];
            }
            return sum;
        });
        FutureTask<Integer> t2 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_00; i++) {
                sum += array[250_000_00 + i];
            }
            return sum;
        });
        FutureTask<Integer> t3 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_00; i++) {
                sum += array[2*250_000_00 + i];
            }
            return sum;
        });
        FutureTask<Integer> t4 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i < 250_000_00; i++) {
                sum += array[3*250_000_00 + i];
            }
            return sum;
        });
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
        return t1.get()+t2.get()+t3.get()+t4.get();
    }
    @Benchmark
    public int test2(){
        int[] array = ARRAY;
        int sum=0;
        for (int i = 0; i < array.length; i++) {
            sum+=array[i];
        }
        return sum;
    }
}
