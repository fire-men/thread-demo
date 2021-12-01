package cn.fire.threadDemo.threadLocal;

import cn.fire.threadDemo.juc.atomic.fieldUpdaterType.Goods;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-12-01 10:59:40
 * @Description ThreadLocal
 *  作用：仅仅存放当前线程的数据,数据安全
 */
public class ThreadLocalTest {
    private static ThreadLocal<Goods> threadLocal = new ThreadLocal();

    @SneakyThrows
    public static void main(String[] args) {
        Thread th1 = new Thread(() -> {
            threadLocal.set(new Goods(100L, "oppo手机"));
        });

        Thread th2 = new Thread(() -> {
            threadLocal.set(new Goods(200L, "Vivo手机"));
        });

        th1.setName("JD");
        th2.setName("TB");
        th1.start();
        th2.start();

        //获取th1和th2的value

        Goods result = threadLocal.get();//当前获取的时main线程的value，为null
        System.out.println(result);

        threadLocal.set(new Goods(300L,"Apple手机"));
        result = threadLocal.get();
        System.out.println("main线程存放的数据为:"+result);

        threadLocal.remove();
        result = threadLocal.get();
        System.out.println("main线程存放的数据为:"+result);

        //让main线程休息10s
        TimeUnit.SECONDS.sleep(10);

    }
}
