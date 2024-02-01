package living.study;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.thread.code")
public class MyThreadCode {
    public static void main(String[] args) {
        Runnable r = ()-> log.info("runing");
        Thread t = new Thread(r);
        t.start();
        log.info("runing");
    }
}
