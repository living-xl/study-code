package living.study;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程状态转换实例 RUNNABLE->TIMED_WAITING
 * @Date 2024年1月30日 20点12分
 *
 */
@Slf4j(topic = "c.thread.code")
public class MyThreadCode1 {
    public static void main(String[] args) {
        Runnable r = ()->
        {
            log.info("runing");
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        };
        Thread t = new Thread(r);
        t.start();
        log.info("t runing,state:{}",t.getState());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("sleep t runing,state:{}",t.getState());
    }
}
