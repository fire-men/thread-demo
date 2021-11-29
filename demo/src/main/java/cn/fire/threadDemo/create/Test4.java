package cn.fire.threadDemo.create;

import java.util.concurrent.*;



/**
 * @Author zhangle
 * @CreateTime 2021-11-29 15:48:04
 * @Description 通过线程工厂接口实现
 */
public class Test4 {

    static class MyThreadFactory implements ThreadFactory{
        public Thread newThread(Runnable r) {
           return new Thread(r);
        }
    }

    public static void main(String[] args) {
        //线程工厂获取
       new MyThreadFactory().newThread(()->{
           System.out.println("current Thread Name {} : "+Thread.currentThread().getName());
       }).start();
    }


}
