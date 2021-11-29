package cn.fire.threadDemo.function;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 17:03:07
 * @Description join函数
 * join()
 * join(long millis)
 *
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread th1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                if (i == 5) {

                }
                System.out.println(Thread.currentThread().getName() + ",当前遍历的序号为 {}：" + i);
            }
        });


        Thread th2 = new Thread(() -> {
            for (char i = 'a'; i <= 'z'; i++) {
                if ('d' == i) {
                    try {
                        //中断th2线程执行，等待th1任务执行完毕
                        th1.join();
                        //th2线程等待4s
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ",当前遍历的序号为 {}：" + i);
            }
        });

        th1.setName("number");
        th2.setName("char");
        //为了看到实时效果，将th2优先级设置为最高
        th2.setPriority(10);
        th1.setPriority(2);
        th1.start();
        th2.start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println(th2.getState()); //TIMED_WAITING


    }
}
