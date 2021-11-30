**1、目录结构**

  create  线程创建的方式
  function 线程常用的方法
  juc juc包下常用的工具类 
     - atomic 原子类
          - 基本数据类型
              - AtomicInteger
              - AtomicLong
              - AtomicBoolean
          - 数组类型
              - AtomicIntegerArray
              - AtomicLongArray
              - AtomicReferenceArray
          - 对象的属性修改类型
              - AtomicIntegerFieldUpdater
              - AtomicLongFieldUpdater
              - AtomicReferenceFieldUpdater
          - 引用类型
              - AtomicReference
              - AtomicStampedRerence
              - AtomicMarkableReference 
     - lock 锁
     - threadPool 线程池
  communicate 线程之间通信
  deadlock 线程死锁
  synchronization 线程同步(数据共享)
  
**2、注意**  

   Java线程开启是使用star()方法，启动线程，让线程变成就绪状态等待 CPU 调度后执行。
 而thread.yield()方法则是使当前线程由执行状态，变成为就绪状态，让出cpu时间，在
 下一个线程执行时候，此线程有可能被执行，也有可能没有被执行
 
**3、synchronized了解**

   synchronized 是由一对 monitorenter/monitorexit 指令实现的，monitor 对象是同步的基本实现单元。
 在 Java 6 之前，monitor 的实现完全是依靠操作系统内部的互斥锁，因为需要进行用户态到内核态的切换，所
 以同步操作是一个无差别的重量级操作，性能也很低。但在 Java 6 的时候，Java 虚拟机 对此进行了大刀阔斧
 地改进，提供了三种不同的 monitor 实现，也就是常说的三种不同的锁：偏向锁（Biased Locking）、轻量级
 锁和重量级锁，大大改进了其性能。
    Synchornized并不等同于Monitor，而是Monitor机制包括Synchornized，和一系列线程调用的API比如Wait()，
  Notify()方法。开发者要结合使用 synchronized 关键字，以及 Object 的 wait / notify 等元素，才能说
  自己利用 monitor 的机制去解决了一个生产者消费者的问题。
 
  **4、死锁**
  