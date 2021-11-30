package cn.fire.threadDemo.deadlock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 13:58:20
 * @Description 解决死锁
 */
public class DealDeadLockTest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    static class Job1 implements Runnable {
        private Object lock_A;
        private Object lock_B;


        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock_A){
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(Thread.currentThread().getName() + " 获取了锁 {} lock_A");
                synchronized (lock_B){
                    System.out.println(Thread.currentThread().getName() + " 获取了锁 {} lock_B");
                }
            }
            System.out.println(Thread.currentThread().getName() + " 释放了锁 {} lock_A");
            System.out.println(Thread.currentThread().getName() + " 释放了锁 {} lock_B");
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    static class Job2 implements Runnable {
        private Object lock_A;
        private Object lock_B;

        @SneakyThrows
        @Override
        public void run() {
            synchronized (lock_A){
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(Thread.currentThread().getName() + " 获取了锁 {} lock_A");
                synchronized (lock_B){
                    System.out.println(Thread.currentThread().getName() + " 获取了锁 {} lock_B");
                }
            }
            System.out.println(Thread.currentThread().getName() + " 释放了锁 {} lock_A");
            System.out.println(Thread.currentThread().getName() + " 释放了锁 {} lock_B");
        }
    }


    public static void main(String[] args) {
        /* 模拟两把锁 */
        Object lock_A = new Object();
        Object lock_B = new Object();

        Job1 job1 = new Job1().setLock_A(lock_A).setLock_B(lock_B);
        Job2 job2 = new Job2().setLock_A(lock_A).setLock_B(lock_B);
        Thread th1 = new Thread(job1);
        Thread th2 = new Thread(job2);
        th1.setName("JD");
        th2.setName("TB");
        th1.start();
        th2.start();

    }

}
