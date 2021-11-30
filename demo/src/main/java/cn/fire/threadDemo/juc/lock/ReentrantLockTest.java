package cn.fire.threadDemo.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 16:54:41
 * @Description ReentrantLock类
 */
public class ReentrantLockTest {
    /* 默认：创建的是非公平锁 */
    private static ReentrantLock lock = new ReentrantLock();
    private static Long stock = 10L;

    static class TicketJob implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                if (stock == 0) {
                    System.out.println("票已经被抢空.........");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "抢到了一张票, 剩下" + (--stock) + "张票");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TicketJob ticketJob = new TicketJob();
        for (int i = 0; i < 10; i++) {
            Thread th1 = new Thread(ticketJob);
            th1.setName("JD");
            th1.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread th2 = new Thread(ticketJob);
            th2.setName("TB");
            th2.start();
        }


    }
}
