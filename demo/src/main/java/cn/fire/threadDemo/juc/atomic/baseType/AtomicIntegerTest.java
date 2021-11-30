package cn.fire.threadDemo.juc.atomic.baseType;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 14:43:26
 * @Description
 */
public class AtomicIntegerTest {
    /**
     * 初始值10，是被volatile修饰过的，保证可见性+有序性
     */
    private static AtomicInteger ticketsNumber = new AtomicInteger(10);

    static class TicketsJob implements Runnable {

        @Override
        public void run() {
            if (ticketsNumber.get() == 0) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 抢了一张票，还剩下" + ticketsNumber.decrementAndGet() + "张票");
        }
    }

    public static void main(String[] args) {
        TicketsJob ticketsJob = new TicketsJob();
        //模拟有5个从JD来的客户，6个从TB来的客户
        for (int i = 0; i < 5; i++) {
            Thread th1 = new Thread(ticketsJob);
            th1.setName("JD");
            th1.start();
        }

        for (int i = 0; i < 6; i++) {
            Thread th2 = new Thread(ticketsJob);
            th2.setName("TB");
            th2.start();
        }
    }

}
