package living.study;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * interrupt 打断park 打断后打断标志为ture,park被打断后打断标志为ture无法再park,只有打断标志为false才能park
 * @Date 2024年1月31日 19点36分
 *
 */
@Slf4j(topic = "c.thread.code")
public class MyThreadCode7 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("park ....");
            LockSupport.park();
            log.debug("unpark ....");
            log.debug("打断状态：{}",Thread.currentThread().isInterrupted());
            Thread.interrupted();//再次打断把打断标志设置为false
            LockSupport.park();
            log.debug("unpark ....");
        }, "t1");
        t1.start();
        sleep(1000);
        log.debug("sleep 1s后开始打断 t1");
        t1.interrupt();
        log.debug("打断后 t1-{}",t1.isInterrupted());

    }

    private static void sleep(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
