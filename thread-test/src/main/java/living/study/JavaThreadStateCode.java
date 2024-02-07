package living.study;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 演示java线程的六种状态
 * @Date 2024-02-07 8:43
 *
 */
@Slf4j(topic = "c.thread.code")
public class JavaThreadStateCode {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> log.debug("running ...."), "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> log.debug("running ..."), "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (JavaThreadStateCode.class) {
                sleep(10000000);
            }
        }, "t4");
        t4.start();
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (JavaThreadStateCode.class) {
                sleep(10000000);
            }
        }, "t6");
        t6.start();
        sleep(500);
        log.debug("t1 state-{}",t1.getState());
        log.debug("t2 state-{}",t2.getState());
        log.debug("t3 state-{}",t3.getState());
        log.debug("t4 state-{}",t4.getState());
        log.debug("t5 state-{}",t5.getState());
        log.debug("t6 state-{}",t6.getState());


    }

    private static void sleep(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
