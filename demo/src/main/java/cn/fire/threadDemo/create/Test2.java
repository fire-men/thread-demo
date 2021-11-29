package cn.fire.threadDemo.create;

import lombok.SneakyThrows;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 15:34:46
 * @Description 实现Runnable接口
 */
public class Test2 {

    static class ImplementRunnable implements Runnable{

        @SneakyThrows
        public void run() {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        new Thread(new ImplementRunnable()).start();
    }
}
