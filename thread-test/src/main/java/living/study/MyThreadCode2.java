package living.study;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程sleep interrupt
 * @Date 2024年1月30日 20点17分
 *
 */
@Slf4j(topic = "c.thread.code")
public class MyThreadCode2 {
    public static void main(String[] args) {
        Runnable r = ()->
        {
            try {
                log.debug("enter sleep.....");
//                Thread.sleep(2000);
                TimeUnit.MILLISECONDS.sleep(2000);
            }catch (Exception e){
                log.debug("wake up ...");
                e.printStackTrace();
            }
        };
        Thread t = new Thread(r);
        t.start();
        log.debug("t runing,state:{}",t.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("sleep t runing,state:{}",t.getState());
        log.debug("t.interrupt");
        t.interrupt();
    }
}
