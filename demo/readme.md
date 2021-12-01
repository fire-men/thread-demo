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
  
  多线程获得锁和释放锁的时机不一致导致
  解决：保持一致
  
  **5、CAS了解**
  
   在JDK 5之前Java语言是靠synchronized关键字保证同步的，这会导致有锁
   锁机制存在以下问题：
   （1）在多线程竞争下，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题。
   （2）一个线程持有锁会导致其它所有需要此锁的线程挂起。
   （3）如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级倒置，引起性能风险。
  
   volatile是不错的机制，但是volatile不能保证原子性。因此对于同步最终还是要回到锁机制上来。
  独占锁是一种悲观锁，synchronized就是一种独占锁，会导致其它所有需要锁的线程挂起，等待持有锁
  的线程释放锁。而另一个更加有效的锁就是乐观锁。所谓乐观锁就是，每次不加锁而是假设没有冲突而去
  完成某项操作，如果因为冲突失败就重试，直到成功为止。乐观锁用到的机制就是CAS，Compare and Swap。
  
   CAS 操作包含三个操作数 —— 内存位置（V）、预期原值（A）和新值(B)。 如果内存位置的值与预期原值相匹配，
 那么处理器会自动将该位置值更新为新值 。否则，处理器不做任何操作。无论哪种情况，它都会在 CAS 指令之前
 返回该 位置的值。（在 CAS 的一些特殊情况下将仅返回 CAS 是否成功，而不提取当前 值。）CAS 有效地说明了
 “我认为位置 V 应该包含值 A；如果包含该值，则将 B 放到这个位置；否则，不要更改该位置，只告诉我这个位置
 现在的值即可。”
   
   通常将 CAS 用于同步的方式是从地址 V 读取值 A，执行多步计算来获得新 值 B，然后使用 CAS 将 V 的值从 A 改为 B。
 如果 V 处的值尚未同时更改，则 CAS 操作成功。
   
   类似于 CAS 的指令允许算法执行读-修改-写操作，而无需害怕其他线程同时修改变量，因为如果其他线程修改变量，那么 CAS 
 会检测它（并失败），算法可以对该操作重新计算
 
   利用CPU的CAS指令，同时借助JNI来完成Java的非阻塞算法。其它原子操作都是利用类似的特性完成的。而整个J.U.C都是建立
 在CAS之上的，因此对于synchronized阻塞算法，J.U.C在性能上有了很大的提升
 
 CAS虽然很高效的解决原子操作，但是CAS仍然存在三大问题。ABA问题，循环时间长开销大和只能保证一个共享变量的原子操作
  1.  ABA问题。因为CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，但是如果一个值原来是A，变成了B，
  又变成了A，那么使用CAS进行检查时会发现它的值没有发生变化，但是实际上却变化了。ABA问题的解决思路就是使用版本号。在变量
  前面追加上版本号，每次变量更新的时候把版本号加一，那么A－B－A 就会变成1A-2B－3A。从Java1.5开始JDK的atomic包里提供了
  一个类AtomicStampedReference来解决ABA问题。这个类的compareAndSet方法作用是首先检查当前引用是否等于预期引用，并且当前
  标志是否等于预期标志，如果全部相等，则以原子方式将该引用和该标志的值设置为给定的更新值。
  2. 循环时间长开销大。自旋CAS如果长时间不成功，会给CPU带来非常大的执行开销。如果JVM能支持处理器提供的pause指令那么效率
  会有一定的提升，pause指令有两个作用，第一它可以延迟流水线执行指令（de-pipeline）,使CPU不会消耗过多的执行资源，延迟的时间
  取决于具体实现的版本，在一些处理器上延迟时间是零。第二它可以避免在退出循环的时候因内存顺序冲突（memory order violation）
  而引起CPU流水线被清空（CPU pipeline flush），从而提高CPU的执行效率。
  3. 只能保证一个共享变量的原子操作。当对一个共享变量执行操作时，我们可以使用循环CAS的方式来保证原子操作，但是对多个共享变量操作时，
  循环CAS就无法保证操作的原子性，这个时候就可以用锁，或者有一个取巧的办法，就是把多个共享变量合并成一个共享变量来操作。比如有两个
  共享变量i＝2,j=a，合并一下ij=2a，然后用CAS来操作ij。从Java1.5开始JDK提供了AtomicReference类来保证引用对象之间的原子性，
  你可以把多个变量放在一个对象里来进行CAS操作。
  
  **6、线程池**
  
  线程池的思路和生产者消费者模型是很接近的。
  1. 准备一个任务容器
  2. 一次性启动10个 消费者线程
  3. 刚开始任务容器是空的，所以线程都wait在上面。
  4. 直到一个外部线程往这个任务容器中扔了一个“任务”，就会有一个消费者线程被唤醒notify
  5. 这个消费者线程取出“任务”，并且执行这个任务，执行完毕后，继续等待下一次任务的到来。
  6. 如果短时间内，有较多的任务加入，那么就会有多个线程被唤醒，去执行这些任务。
  在整个过程中，都不需要创建新的线程，而是循环使用这些已经存在的线程
  
  原理图：
  classpath:多线程-线程池原理概要图.png
  
  线程池的由来
  1、每一个线程的启动和结束都是比较消耗时间和占用资源的。
  2、如果在系统中用到了很多的线程，大量的启动和结束动作会导致系统的性能变卡，响应变慢。
  
  线程池执行流程图
  classpath:线程池执行流程图.png
  
  线程池场景
  地点：理发店
  人物：理发师 + 客户
  情节：如下
   理发店有两名理发师，如果客户比较多，会向总部申请几个理发师过来帮忙(有时间限制).
    一个早上，来了一个客户，有一个理发师来为他负责洗头和剪发，忽然又来了一个客户，另一个理发师
  做相同的事情，没一会，开始上人了，理发店忙不过来了，只能请求总部来处理这批用户需求，效率还挺快，
  这批等待的客户开始按照序号进行理发了，生意太好了，又有一批客户进来了，但是，理发店位置已满，开始
  拒绝客户，说等明天再来吧，客户叹气，嗨.......
    总部规定的救援时间已到，不得已返回到总部，完成了他们的使命。
  
  线程池参数
  public ThreadPoolExecutor(int corePoolSize,
                            int maximumPoolSize,
                            long keepAliveTime,
                            TimeUnit unit,
                            BlockingQueue<Runnable> workQueue) {
      this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
           Executors.defaultThreadFactory(), defaultHandler);
  }  
  corePoolSize : 池中核心的线程数大小
  maximumPoolSize ： 池子最大的线程数大小
  keepAliveTime ： 非核心线程数存活的时间
  unit ： 存活时间单元
  workQueue ：任务队列，用于存放客户任务的容器
  threadFactory : 用于创建线程的工厂
  handler : 拒绝策略处理器  
  
  **7、ForkJoinPool**
  
  forkjoinPool了解
   1、ForkJoinPool 不是为了替代 ExecutorService，而是它的补充，在某些应用场景下性能比 ExecutorService 更好。
   2、ForkJoinPool 主要用于实现“分而治之”的算法，特别是分治之后递归调用的函数，例如 quick sort 等。
   3、ForkJoinPool 最适合的是计算密集型的任务，如果存在 I/O，线程间同步，sleep() 等会造成线程长时间阻塞的情况时，最好配合使用 ManagedBlocker。

  
  参数详情
  public ForkJoinPool(int parallelism,
                          ForkJoinWorkerThreadFactory factory,
                          UncaughtExceptionHandler handler,
                          boolean asyncMode) {
          this(checkParallelism(parallelism),
               checkFactory(factory),
               handler,
               asyncMode ? FIFO_QUEUE : LIFO_QUEUE,
               "ForkJoinPool-" + nextPoolId() + "-worker-");
          checkPermission();
      }
  parallelism : 默认值为 Runtime.availableProcessors(),即返回处理器个数,我的为8核
  factory: 默认值为defaultForkJoinWorkerThreadFactory，即创建ForkJoinWorkerThread,通过池子创造
  handler ： 异常处理器，默认为null
  asyncMode ： 是否是异步模式，默认为false，即LIFO_QUEUE模式，后进先出模式，比如线程栈
    - FIFO：全称First in, First out，先进先出。队列结构
    - LIFO：全称Last in, First out，后进先出。  栈结构
    
  
  ForkJoinTask类  
   1、有两个子类
    - RecursiveTask  有返回值
    - RecursiveAction 无返回值
   2、两个核心方法
      fork() 大任务拆分
      join() 拆分后的子任务将结果合并
      
  
   Fork/Join框架原理
   待补充.........
      
    
     
    