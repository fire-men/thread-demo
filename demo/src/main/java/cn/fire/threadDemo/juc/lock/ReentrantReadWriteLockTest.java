package cn.fire.threadDemo.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 17:15:50
 * @Description J.U.C之ReentrantReadWriteLock
 * 性能比ReentrantLock更高，锁粒度被细化
 * readLock : 只对读上锁
 * WriteLock ：只对写上锁
 */
public class ReentrantReadWriteLockTest {
    /* 默认为非公平锁 */
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private static Map<String,Object> contains = new HashMap<>();

    public static void main(String[] args) {



        for (int i = 0; i <10 ; i++) {
            int finalI = i;
            new Thread(()->{
                storeData(finalI);
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
            readData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void readData() {
        try {
            readWriteLock.readLock().lock();
            System.out.println(contains);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void storeData(int i) {
        try {
            readWriteLock.writeLock().lock();
            contains.put("username"+i,"zhangsan"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }




}
