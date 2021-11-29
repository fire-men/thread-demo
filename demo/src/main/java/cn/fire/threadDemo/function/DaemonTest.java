package cn.fire.threadDemo.function;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 16:53:25
 * @Description 守护线程
 */
public class DaemonTest {
    @SneakyThrows
    public static void main(String[] args) {

        Thread th1 = new Thread(() -> {
            while (true) {
                System.out.println("当前线程的唯一标示为 {} : " + Thread.currentThread().getId()+", 当前线程"+(Thread.currentThread().isDaemon()==true?"是守护线程":"不是守护线程"));
            }
        });
        th1.setDaemon(true);
        th1.start();

        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("线程th1即将关闭................");

    }
}
