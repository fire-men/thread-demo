package cn.fire.threadDemo.function;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 16:14:34
 * @Description 线程状态
 */
public class StateTest {

    /**
     * 1、线程状态
     *    NEW
     *    RUNNABLE
     *    BLOCKED
     *    WAITING
     *    TIMED_WAITING
     *    TERMINATED
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        Thread th = new Thread(new Job());
        System.out.println("当前线程状态为{}：" + th.getState());//NEW
        th.start();
        System.out.println("当前线程状态为{}：" + th.getState());//RUNNABLE

        TimeUnit.SECONDS.sleep(2);
        System.out.println("当前线程状态为{}：" + th.getState());//TERMINATED
    }

    static class Job implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("当前线程状态为{}：" + Thread.currentThread().getState());//RUNNABLE
        }
    }


} 
  
