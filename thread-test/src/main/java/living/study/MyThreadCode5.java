package living.study;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * interrupt 当打断阻塞的线程时打断标志为false,然后会执行完后面的方法
 *           当打断正在进行的线程时，打断标志为true
 * @Date 2024年1月31日 19点36分
 *
 */
@Slf4j(topic = "c.thread.code")
public class MyThreadCode5 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("t1 start");
            sleep(5000);
            log.debug("t1 stop");
        }, "t1");
        t1.start();
        sleep(1000);
        log.debug("sleep 1s后开始打断 t1");
        t1.interrupt();
        log.debug("打断后 t1-{}",t1.isInterrupted());
        Thread t2 = new Thread(() -> {
            log.debug("t2 start");
            int i = 0;
            for (;i<=1000000000 ; ) {
                i += 1;
            }
            log.debug("t2 stop,t-{}",i);
        }, "t2");
        t2.start();
//        sleep(1000);
        log.debug("sleep 1s后开始打断 t2");
        log.debug("打断前 t2-{}",t2.isInterrupted());
        t2.interrupt();
        log.debug("打断后 t2-{}",t2.isInterrupted());
    }

    private static void sleep(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
