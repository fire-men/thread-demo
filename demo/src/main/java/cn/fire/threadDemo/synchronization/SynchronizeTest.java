package cn.fire.threadDemo.synchronization;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 09:29:44
 * @Description synchronized关键字 + volatile关键字
 *  volatile : 可见性 + 有序性(防止指令重排序)
 */
public class SynchronizeTest {

    @Deprecated
    static class Tickets {
        private Long num;

        public Tickets(Long num) {
            this.num = num;
        }

        public void buy() {
            if (this.num == 0) {
                return;
            }
            System.out.println("channel {} : " + Thread.currentThread().getName() + "抢到了一张票, " + "当前还剩下" + (--this.num) + "张票");

        }
    }

    static class Job implements Runnable {
        /* 总共票数 */
        private volatile Long num;

        public Job(Long num) {
            this.num = num;
        }

        @SneakyThrows
        @Override
        public void run() {
            synchronized (this) {
                if (this.num == 0) {
                    return;
                }
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("channel {} : " + Thread.currentThread().getName() + "抢到了一张票, " + "当前还剩下" + (--this.num) + "张票");

            }
        }
    }

    public static void main(String[] args) {

        Job job = new Job(10L);
        for (int i = 0; i < 5; i++) {
            Thread th1 = new Thread(job);
            th1.setName("JD");
            th1.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread th2 = new Thread(job);
            th2.setName("TB");
            th2.start();
        }


    }
}
