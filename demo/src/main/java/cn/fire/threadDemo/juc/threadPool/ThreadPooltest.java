package cn.fire.threadDemo.juc.threadPool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author zhangle
 * @CreateTime 2021-12-01 09:29:14
 * @Description
 */
public class ThreadPooltest {

    public static void main(String[] args) {
        /* 创建3个核心线程数 */
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        /* 模拟10个用户请求，即有10个任务 */
        for (int i = 0; i < 10; i++) {
          threadPool.execute(new Thread(()->{
              System.out.println(Thread.currentThread().getName()+"执行了业务A");
          }));
        }

        //关闭池子(等待任务执行完毕)
        //threadPool.shutdown();

        //立即停止所有正在执行的任务，返回没有执行的任务列表
        List<Runnable> jobs = threadPool.shutdownNow();
        jobs.stream().forEach(job -> System.out.println(job));



    }


}
