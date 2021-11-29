package cn.fire.threadDemo.function;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 16:39:53
 * @Description 线程优先级
 * 优先级高的线程会有更大的几率获得CPU资源
 */
public class PriorityTest {
    public static void main(String[] args) {
        Job job = new Job();
        Thread t1 = new Thread(job);
        Thread t2 = new Thread(job);
        t1.setPriority(3);
        t2.setPriority(8);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }

    static class Job implements Runnable{
        @Override
        public void run() {
            System.out.println("current Thread name {} : "+Thread.currentThread().getName()+", current thread id {} "+Thread.currentThread().getId());
        }
    }
}
