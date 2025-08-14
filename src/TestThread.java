import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:
 * @CreateTime: 2025-07-15
 * @Description:
 */
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            public void run() {
                System.out.println("running...1 time");
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(1);

        ReentrantLock reentrantLock = new ReentrantLock(false);
        System.nanoTime();

        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndExchange(1, 1);
    }
}
