package cn.fire.threadDemo.create;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 15:58:04
 * @Description 线程池实现
 */
public class Test5 {

    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService threadPool = null;
        try {
            threadPool = Executors.newFixedThreadPool(3);
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println("thread name {} : " + Thread.currentThread().getName() + "_" + finalI);
                });
            }

            TimeUnit.MILLISECONDS.sleep(2);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            threadPool.shutdown();
        }


    }
}
