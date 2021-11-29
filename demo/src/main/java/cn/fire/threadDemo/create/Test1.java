package cn.fire.threadDemo.create;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 15:29:46
 * @Description 继承Thread
 */

public class Test1 {
    public static void main(String[] args) {
        System.out.println("继承Thread类的方式");
        ExtendThread th = new ExtendThread();
        th.setName("thread:v1");
        th.start();

    }

    static class ExtendThread extends Thread{
        @Override
        public void run() {
            System.out.println("current thread name :{} "+Thread.currentThread().getName());
        }
    }

}
