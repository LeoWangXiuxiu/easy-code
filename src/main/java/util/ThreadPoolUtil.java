package util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolUtil {
    public static ExecutorService create() {
        return Executors.newFixedThreadPool(10);
    }

    public static void shutdown(ExecutorService service) {
        service.shutdown();
        try {
            while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                log.info("线程池等待关闭");
            }
            log.info("线程池关闭完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void listToShutdown(Integer count, ExecutorService service){
        CountDownLatch countDownLatch = new CountDownLatch(count);
        try {
            countDownLatch.await(); // 等待所有任务执行完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            log.error("线程被中断", e);
        } finally {
            ThreadPoolUtil.shutdown(service); // 关闭线程池
        }
    }
}
