package living.study;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 两阶段终止实现
 * @Date 2024年1月31日 20点04分
 *
 */
@Slf4j(topic = "c.thread.code")
public class MyThreadCode6 {

    public static void main(String[] args) {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        sleep(3600);
        twoPhaseTermination.stop();
    }
    private static void sleep(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
@Slf4j(topic = "c.thread.TwoPhaseTermination")
class TwoPhaseTermination{
    private Thread monitor;

    public void start(){
        monitor = new Thread(()->{
            while (true){
                Thread currentThread = Thread.currentThread();
                if(currentThread.isInterrupted()){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(100);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    currentThread.interrupt();//重新设置打断标记
                }
            }
        },"monitor");
        monitor.start();
    }
    public void stop(){
        monitor.interrupt();
    }
}
