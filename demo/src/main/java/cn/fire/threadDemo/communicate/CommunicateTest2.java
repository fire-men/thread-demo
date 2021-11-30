package cn.fire.threadDemo.communicate;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 11:33:51
 * @Description 打印1-100之间连续的数
 */
public class CommunicateTest2 {
    private static Integer i = 0;

    public static void main(String[] args) {
        Object obj = new Object();
        Thread th1 = new Thread(new Odd(obj));
        Thread th2 = new Thread(new Even(obj));
        th1.setName("odd");
        th2.setName("even");
        th1.setPriority(10);
        th1.start();
        th2.start();

        //System.out.println(sum());

    }

    public static Integer sum() {
        Integer sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }

    /* print奇数 */
    static class Odd implements Runnable {

        private Object obj;

        public Odd(Object obj) {
            this.obj = obj;
        }

        @SneakyThrows
        @Override
        public void run() {
          while (true) {
              TimeUnit.MILLISECONDS.sleep(500);
              synchronized (obj) {
                  if(i % 2 == 0){
                      obj.wait();
                  }else{
                      System.out.println("奇数 {} ："+(i++));
                  }

              }
          }
        }
    }

    /* print偶数 */
    static class Even implements Runnable {

        private Object obj;

        public Even(Object obj) {
            this.obj = obj;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(500);
                synchronized (obj) {
                    if(i % 2 != 0){
                        obj.notifyAll();
                    }else{
                        System.out.println("偶数 {} ："+(i++));
                    }

                }
            }
        }
        }
    }




