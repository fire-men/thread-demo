package cn.fire.threadDemo.function;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 17:26:24
 * @Description yield()
 *  Java线程开启是使用star()方法，启动线程，让线程变成就绪状态等待 CPU 调度后执行。
 *  而thread.yield()方法则是使当前线程由执行状态，变成为就绪状态，让出cpu时间，在
 *  下一个线程执行时候，此线程有可能被执行，也有可能没有被执行
 */
public class YieldTest {
    public static void main(String[] args) {
        Thread th1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                if (i == 3) {
                    Thread.yield();
                }
                System.out.println(Thread.currentThread().getName() + ",遍历序号 {} " + i);
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                try {//保证线程th1先执行
                    //TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",遍历序号 {} " + i);
            }
        });

        th1.start();
        th2.start();

    }
}
