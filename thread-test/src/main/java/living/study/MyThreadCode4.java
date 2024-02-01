package living.study;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程的join方法实例
 * 如果不用join则r的值仍为0
 * 使用join方法等待 则打印r的值为10
 * @Date 2024年1月30日 20点51分
 *
 */
@Slf4j(topic = "c.thread.code")
public class MyThreadCode4 {
    static int r = 0;
    static int r1 = 0;
    static int r2= 0;
    public static void main(String[] args) throws InterruptedException {
        test2();
    }
    private static void test1() throws InterruptedException {
        log.debug("begin");
        Thread t1 = new Thread(() -> {
            log.debug("t1 begin");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("t1 end");
            r = 10;
        }, "t1");
        t1.start();
        t1.join();
        log.debug("result - {}",r);
        log.debug("end");
    }

    /**
     * 等待多个线程
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {
        log.debug("begin");
        Thread t1 = new Thread(() -> {
            sleep(1000);
            r1 = 10;
        }, "t1");
        Thread t2 = new Thread(() -> {
            sleep(2000);
            r2 = 20;
        }, "t2");
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        log.debug("begin join");
        t1.join();
        log.debug("t1 join end");
        t2.join();
        log.debug("t2 join end");
        long end = System.currentTimeMillis();
        log.debug("r1-{};r2-{},cost:{}",r1,r2,end-start);
        log.debug("end");

    }
    private static void sleep(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
