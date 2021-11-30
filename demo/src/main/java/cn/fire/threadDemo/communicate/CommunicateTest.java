package cn.fire.threadDemo.communicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 10:18:42
 * @Description 线程通信--生产者消费者模式
 *  synchronized关键字 + wait() + nitifyall()一起使用，否则会出现非法monitor状态异常
 */
public class CommunicateTest {

    @Data
    static
    class Factory {
        private List<Goods> goodsList = new ArrayList<Goods>();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    static
    class Goods {
        private String goodsName;
        private Long stock = 0L;
    }

    static class Producer implements Runnable {
        private Factory factory;

        public Producer(Factory factory) {
            this.factory = factory;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (factory) {
                    TimeUnit.SECONDS.sleep(1);
                    if (factory.getGoodsList().size() == 0) {
                        List goodsList = new ArrayList();
                        for (int i = 0; i < 10; i++) {
                            Goods x3Pro = new Goods("finx x3 pro" + i, 200L);
                            goodsList.add(x3Pro);
                            System.out.println(Thread.currentThread().getName() + "生产了一个商品 {} : " + x3Pro);
                        }
                        factory.setGoodsList(goodsList);
                    } else {
                        try {
                            factory.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    static class Consumer implements Runnable {
        private Factory factory;

        public Consumer(Factory factory) {
            this.factory = factory;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (factory) {
                    TimeUnit.SECONDS.sleep(1);
                    if (factory.getGoodsList().size() != 0) {
                        for (int i = 0; i < factory.getGoodsList().size(); i++) {
                            System.out.println(Thread.currentThread().getName() + "消费了一个商品 {} : " + factory.getGoodsList().get(i).getGoodsName());
                            factory.getGoodsList().remove(i);
                        }
                    }else{
                        factory.notifyAll();
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        Factory factory = new Factory();
        List goodsList = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            Goods x3Pro = new Goods("finx x3 pro" + i, 200L);
//            goodsList.add(x3Pro);
//        }
        //factory.setGoodsList(goodsList);
        Producer producer = new Producer(factory);
        Consumer consumer = new Consumer(factory);
        Thread th1 = new Thread(producer);
        Thread th2 = new Thread(consumer);
        th1.setName("producer");
        th2.setName("consumer");
        th1.start();
        th2.start();

    }
}
