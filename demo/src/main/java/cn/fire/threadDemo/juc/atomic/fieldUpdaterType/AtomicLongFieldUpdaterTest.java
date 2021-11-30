package cn.fire.threadDemo.juc.atomic.fieldUpdaterType;

import cn.fire.threadDemo.juc.atomic.referenceType.User;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 15:49:04
 * @Description AtomicLongFieldUpdater类
 *  注意：
 *   更新的实体类字段必须要被volatile修饰，且不能被private修饰，否则报错
 *   eg:  volatile long id;
 */
public class AtomicLongFieldUpdaterTest {
    private static AtomicLongFieldUpdater alfu = AtomicLongFieldUpdater.newUpdater(Goods.class,"id");

    public static void main(String[] args) {
        Goods goods = new Goods(1000L,"find X3Pro");
        boolean flag = alfu.compareAndSet(goods, 1000L, 2000L);
        System.out.println(flag == true?"id可以更新":"id不可以更新");
        System.out.println("newVlaue = "+goods.getId());

    }

}
