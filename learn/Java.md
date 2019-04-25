# **2019--Java**
## **一、开场白**
简单的介绍一下自己的工作经历与职责，在校或者工作中主要的工作内容，主要负责的内容；（你的信息一清二白的写在简历上，这个主要为了缓解面试者的压力）
介绍下自己最满意的，有技术亮点的项目或平台，重点介绍下自己负责那部分的技术细节；（主要考察应聘者对自己做过的事情是否有清晰的描述，判断做的事情的复杂度）
## **二、Java多线程**
### **1. 线程池的原理，为什么要创建线程池？创建线程池的方式**

>#### **为什么使用线程池** 

诸如 Web 服务器、数据库服务器、文件服务器或邮件服务器之类的许多服务器应用程序都面向处理来自某些远程来源的大量短小的任务。请求以某种方式到达服务器，这种方式可能是通过网络协议（例如 HTTP、FTP 或 POP）、通过 JMS 队列或者可能通过轮询数据库。不管请求如何到达，服务器应用程序中经常出现的情况是：单个任务处理的时间很短而请求的数目却是巨大的。

构建服务器应用程序的一个简单模型是：每当一个请求到达就创建一个新线程，然后在新线程中为请求服务。实际上对于原型开发这种方法工作得很好，但如果试图部署以这种方式运行的服务器应用程序，那么这种方法的严重不足就很明显。每个请求对应一个线程（thread-per-request）方法的不足之一是：为每个请求创建一个新线程的开销很大；为每个请求创建新线程的服务器在创建和销毁线程上花费的时间和消耗的系统资源要比花在处理实际的用户请求的时间和资源更多。

除了创建和销毁线程的开销之外，活动的线程也消耗系统资源。在一个 JVM 里创建太多的线程可能会导致系统由于过度消耗内存而用完内存或“切换过度”。为了防止资源不足，服务器应用程序需要一些办法来限制任何给定时刻处理的请求数目。

线程池为线程生命周期开销问题和资源不足问题提供了解决方案。通过对多个任务重用线程，线程创建的开销被分摊到了多个任务上。其好处是，因为在请求到达时线程已经存在，所以无意中也消除了线程创建所带来的延迟。这样，就可以立即为请求服务，使应用程序响应更快。而且，通过适当地调整线程池中的线程数目，也就是当请求的数目超过某个阈值时，就强制其它任何新到的请求一直等待，直到获得一个线程来处理为止，从而可以防止资源不足。

---
>#### **使用线程池的风险**
虽然线程池是构建多线程应用程序的强大机制，但使用它并不是没有风险的。用线程池构建的应用程序容易遭受任何其它多线程应用程序容易遭受的所有并发风险，诸如同步错误和死锁，它还容易遭受特定于线程池的少数其它风险，诸如与池有关的死锁、资源不足和线程泄漏。
- **死锁**

    任何多线程应用程序都有死锁风险。当一组进程或线程中的每一个都在等待一个只有该组中另一个进程才能引起的事件时，我们就说这组进程或线程 死锁了。死锁的最简单情形是：线程 A 持有对象 X 的独占锁，并且在等待对象 Y 的锁，而线程 B 持有对象 Y 的独占锁，却在等待对象 X 的锁。除非有某种方法来打破对锁的等待（Java 锁定不支持这种方法），否则死锁的线程将永远等下去。

    虽然任何多线程程序中都有死锁的风险，但线程池却引入了另一种死锁可能，在那种情况下，所有池线程都在执行已阻塞的等待队列中另一任务的执行结果的任务，但这一任务却因为没有未被占用的线程而不能运行。当线程池被用来实现涉及许多交互对象的模拟，被模拟的对象可以相互发送查询，这些查询接下来作为排队的任务执行，查询对象又同步等待着响应时，会发生这种情况
-	**资源不足**

    任何多线程应用程序都有死锁风险。当一组进程或线程中的每一个都在等待一个只有该组中另一个进程才能引起的事件时，我们就说这组进程或线程 死锁了。死锁的最简单情形是：线程 A 持有对象 X 的独占锁，并且在等待对象 Y 的锁，而线程 B 持有对象 Y 的独占锁，却在等待对象 X 的锁。除非有某种方法来打破对锁的等待（Java 锁定不支持这种方法），否则死锁的线程将永远等下去。

    虽然任何多线程程序中都有死锁的风险，但线程池却引入了另一种死锁可能，在那种情况下，所有池线程都在执行已阻塞的等待队列中另一任务的执行结果的任务，但这一任务却因为没有未被占用的线程而不能运行。当线程池被用来实现涉及许多交互对象的模拟，被模拟的对象可以相互发送查询，这些查询接下来作为排队的任务执行，查询对象又同步等待着响应时，会发生这种情况
-	**并发错误**

    线程池和其它排队机制依靠使用 wait() 和 notify() 方法，这两个方法都难于使用。如果编码不正确，那么可能丢失通知，导致线程保持空闲状态，尽管队列中有工作要处理。使用这些方法时，必须格外小心。而最好使用现有的、已经知道能工作的实现，例如 util.concurrent 包。
-	**线程泄漏**

    各种类型的线程池中一个严重的风险是线程泄漏，当从池中除去一个线程以执行一项任务，而在任务完成后该线程却没有返回池时，会发生这种情况。发生线程泄漏的一种情形出现在任务抛出一个 RuntimeException 或一个 Error 时。如果池类没有捕捉到它们，那么线程只会退出而线程池的大小将会永久减少一个。当这种情况发生的次数足够多时，线程池最终就为空，而且系统将停止，因为没有可用的线程来处理任务。

    有些任务可能会永远等待某些资源或来自用户的输入，而这些资源又不能保证变得可用，用户可能也已经回家了，诸如此类的任务会永久停止，而这些停止的任务也会引起和线程泄漏同样的问题。如果某个线程被这样一个任务永久地消耗着，那么它实际上就被从池除去了。对于这样的任务，应该要么只给予它们自己的线程，要么只让它们等待有限的时间。
-	**请求过载**

    仅仅是请求就压垮了服务器，这种情况是可能的。在这种情形下，我们可能不想将每个到来的请求都排队到我们的工作队列，因为排在队列中等待执行的任务可能会消耗太多的系统资源并引起资源缺乏。在这种情形下决定如何做取决于您自己；在某些情况下，您可以简单地抛弃请求，依靠更高级别的协议稍后重试请求，您也可以用一个指出服务器暂时很忙的响应来拒绝请求。

---

>#### **有效使用线程池的准则**
只要您遵循几条简单的准则，线程池可以成为构建服务器应用程序的极其有效的方法：

不要对那些同步等待其它任务结果的任务排队。这可能会导致上面所描述的那种形式的死锁，在那种死锁中，所有线程都被一些任务所占用，这些任务依次等待排队任务的结果，而这些任务又无法执行，因为所有的线程都很忙。

在为时间可能很长的操作使用合用的线程时要小心。如果程序必须等待诸如 I/O 完成这样的某个资源，那么请指定最长的等待时间，以及随后是失效还是将任务重新排队以便稍后执行。这样做保证了：通过将某个线程释放给某个可能成功完成的任务，从而将最终取得某些进展。

理解任务,要有效地调整线程池大小，您需要理解正在排队的任务以及它们正在做什么。它们是 CPU 限制的（CPU-bound）吗？它们是 I/O 限制的（I/O-bound）吗？您的答案将影响您如何调整应用程序。如果您有不同的任务类，这些类有着截然不同的特征，那么为不同任务类设置多个工作队列可能会有意义，这样可以相应地调整每个池。

-	**线程池的大小设置**

    调整线程池的大小基本上就是避免两类错误：线程太少或线程太多。幸运的是，对于大多数应用程序来说，太多和太少之间的余地相当宽。

    请回忆：在应用程序中使用线程有两个主要优点，尽管在等待诸如I/O的慢操作，但允许继续进行处理，并且可以利用多处理器。在运行于具有N个处理器机器上的计算限制的应用程序中，在线程数目接近N时添加额外的线程可能会改善总处理能力，而在线程数目超过N时添加额外的线程将不起作用。事实上，太多的线程甚至会降低性能，因为它会导致额外的环境切换开销。

    线程池的最佳大小取决于可用处理器的数目以及工作队列中的任务的性质。若在一个具有N个处理器的系统上只有一个工作队列，其中全部是计算性质的任务，在线程池具有N或N+1个线程时一般会获得最大的CPU利用率。

    对于那些可能需要等待I/O完成的任务（例如，从套接字读取HTTP请求的任务），需要让池的大小超过可用处理器的数目，因为并不是所有线程都一直在工作。通过使用概要分析，您可以估计某个典型请求的等待时间（WT）与服务时间（ST）之间的比例。如果我们将这一比例称之为WT/ST，那么对于一个具有N个处理器的系统，需要设置大约N*(1+WT/ST)个线程来保持处理器得到充分利用。

    处理器利用率不是调整线程池大小过程中的唯一考虑事项。随着线程池的增长，您可能会碰到调度程序、可用内存方面的限制，或者其它系统资源方面的限制，例如套接字、打开的文件句柄或数据库连接等的数目。
-	**常用的几种线程池**

    **newCachedThreadPool**

        创建一个可缓存线程池，如果线程池内线程过多，可灵活回收空闲线程，若无可回收，则新建线程。

        这种类型的线程池特点是：工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
    
        如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
    
        在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很可能会造成系统瘫痪。
    
    **newFixedThreadPool**

        创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。

        FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
        
        定长线程池的大小最好根据系统资源进行设置如Runtime.getRuntime().availableProcessors()。
    **newSingleThreadExecutor**

        创建一个单线程化的Executor，即只创建唯一的工作者线程来执行任务，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO,优先级)执行。如果这个线程异常结束，会有另一个取代它，保证顺序执行。单工作线程最大的特点是可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
    
    **newScheduleThreadpool**

        创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。

---

### **2.	线程的生命周期，什么时候会出现僵死进程**
>#### **线程的生命周期及五种基本状态**
 
- **Java线程具有五种基本状态**

    **新建状态（New）**：当线程对象对创建后，即进入了新建状态，如：Thread t = new MyThread()。

    **就绪状态（Runnable）**：当调用线程对象的start()方法（t.start();），线程即进入就绪状态。处于就绪状态的线程，只是说明此线程已经做好了准备，随时等待CPU调度执行，并不是说执行了t.start()此线程立即就会执行。

    **运行状态（Running）**：当CPU开始调度处于就绪状态的线程时，此时线程才得以真正执行，即进入到运行状态。注：就绪状态是进入到运行状态的唯一入口，也就是说，线程要想进入运行状态执行，首先必须处于就绪状态中。

    **阻塞状态（Blocked）**：处于运行状态中的线程由于某种原因，暂时放弃对CPU的使用权，停止执行，此时进入阻塞状态，直到其进入到就绪状态，才 有机会再次被CPU调用以进入到运行状态。根据阻塞产生的原因不同，阻塞状态又可以分为三种：

    1. 等待阻塞：运行状态中的线程执行wait()方法，使本线程进入到等待阻塞状态。

    2. 同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态。

    3. 其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。

    **死亡状态（Dead）**：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。

    就绪状态转换为运行状态：当此线程得到处理器资源。

    运行状态转换为就绪状态：当此线程主动调用yield()方法或在运行过程中失去处理器资源。

    运行状态转换为死亡状态：当此线程执行体执行完毕或发生了异常。

        此处需要特别注意的是：当调用线程的yield()方法时，线程从运行状态转
        换为就绪状态，但接下来CPU调度就绪状态中的哪个线程具有一定的随机性，
        因此，可能会出现A线程调用了yield()方法后，接下来CPU仍然调度了A线
        程的情况。

---

>#### **面试题:僵尸进程和孤儿进程是什么，分别有什么危害(百度面试题)**
- **概念**

    **孤儿进程：**

    一个父进程退出，而它的一个或多个子进程还在运行，那么那些子进程将成为孤儿进程。孤儿进程将被init进程(进程号为1)所收养，并由init进程对它们完成状态收集工作。

    **僵尸进程：**

    一个进程使用fork创建子进程，如果子进程退出，而父进程并没有调用wait或waitpid获取子进程的状态信息，那么子进程的进程描述符仍然保存在系统中。这种进程称之为僵死进程。

- **危害**

    **孤儿进程：**

    孤儿进程是没有父进程的进程，孤儿进程这个重任就落到了init进程身上，init进程就好像是一个民政局，专门负责处理孤儿进程的善后工作。每当出现一个孤儿进程的时候，内核就把孤 儿进程的父进程设置为init，而init进程会循环地wait()它的已经退出的子进程。这样，当一个孤儿进程凄凉地结束了其生命周期的时候，init进程就会代表党和政府出面处理它的一切善后工作。因此孤儿进程并不会有什么危害。

    **僵尸进程：**

    父进程还在运行但是子进程挂了，但是父进程却没有使用wait来清理子进程的进程信息，导致子进程虽然运行实体已经消失，但是仍然在内核的进程表中占据一条记录，这样长期下去对于系统资源是一个浪费。

- **僵尸进程处理**

    1、通过信号机制

        子进程退出时向父进程发送SIGCHILD信号，父进程处理SIGCHILD信号。调用wait()或者waitpid()，让父进程阻塞等待僵尸进程的出现，处理完在继续运行父进程。
    2、杀死父进程

        当父进程陷入死循环等无法处理僵尸进程时，强制杀死父进程，那么它的子进程，即僵尸进程会变成孤儿进程，由系统来回收。
    3、重启系统

        当系统重启时，所有进程在系统关闭时被停止，包括僵尸进程，开启时init进程会重新加载其他进程。

---

### **3. 说说线程安全问题，什么实现线程安全，如何实现线程安全**
>#### **多线程编程中的三个核心概念**
   
- **原子性**

    一个操作（有可能包含有多个子操作）要么全部执行（生效），要么全部都不执行（都不生效）。
- **可见性**

    可见性是指，当多个线程并发访问共享变量时，一个线程对共享变量的修改，其它线程能够立即看到。可见性问题是好多人忽略或者理解错误的一点。

    CPU从主内存中读数据的效率相对来说不高，现在主流的计算机中，都有几级缓存。每个线程读取共享变量时，都会将该变量加载进其对应CPU的高速缓存里，修改该变量后，CPU会立即更新该缓存，但并不一定会立即将其写回主内存（实际上写回主内存的时间不可预期）。此时其它线程（尤其是不在同一个CPU上执行的线程）访问该变量时，从主内存中读到的就是旧的数据，而非第一个线程更新后的数据。
        
    这一点是操作系统或者说是硬件层面的机制，所以很多应用开发人员经常会忽略。
- **顺序性**

    顺序性指的是，程序执行的顺序按照代码的先后顺序执行。
>#### **Java如何解决多线程并发问题**

- **Java如何保证原子性**
    
    **锁和同步**

    常用的保证Java操作原子性的工具是锁和同步方法（或者同步代码块）。使用锁，可以保证同一时间只有一个线程能拿到锁，也就保证了同一时间只有一个线程能执行申请锁和释放锁之间的代码。
    
    ```java
    public void testLock () {
        lock.lock();
        try{
            int j = i;
            i = j + 1;
        } finally {
            lock.unlock();
        }
    }
    ```
    与锁类似的是同步方法或者同步代码块。使用非静态同步方法时，锁住的是当前实例；使用静态同步方法时，锁住的是该类的Class对象；使用静态代码块时，锁住的是synchronized关键字后面括号内的对象。
        
    ```java
    public void testLock () {
        synchronized (anyObject){
            int j = i;
            i = j + 1;
        }
    }
    ```
    无论使用锁还是synchronized，本质都是一样，通过锁来实现资源的排它性，从而实际目标代码段同一时间只会被一个线程执行，进而保证了目标代码段的原子性。这是一种以牺牲性能为代价的方法。

    **CAS（compare and swap）**

    基础类型变量自增（i++）是一种常被新手误以为是原子操作而实际不是的操作。Java中提供了对应的原子操作类来实现该操作，并保证原子性，其本质是利用了CPU级别的CAS指令。由于是CPU级别的指令，其开销比需要操作系统参与的锁的开销小。AtomicInteger使用方法如下：

    ```java
    AtomicInteger atomicInteger = new AtomicInteger();
    for(int b = 0; b < numThreads; b++) {
        new Thread(() -> {
            for(int a = 0; a < iteration; a++) {
            atomicInteger.incrementAndGet();
            }
        }).start();
    }
    ```
- **Java如何保证可见性**

    Java提供了volatile关键字来保证可见性。当使用volatile修饰某个变量时，它会保证对该变量的修改会立即被更新到内存中，并且将其它缓存中对该变量的缓存设置成无效，因此其它线程需要读取该值时必须从主内存中读取，从而得到最新的值。

    [**关于volatile的总结**](https://blog.csdn.net/u012723673/article/details/80682208)

    [**Java中的双重检查锁**](https://www.cnblogs.com/xz816111/p/8470048.html)

    正确的双重检查锁：
    ```java
    public class Singleton {
        private volatile static Singleton uniqueSingleton;

        private Singleton() {
        }

        public Singleton getInstance() {
            if (null == uniqueSingleton) {
                synchronized (Singleton.class) {
                    if (null == uniqueSingleton) {
                        uniqueSingleton = new Singleton();
                    }
                }
            }
            return uniqueSingleton;
        }
    }
    ```

- **Java如何保证顺序性**

    上文讲过编译器和处理器对指令进行重新排序时，会保证重新排序后的执行结果和代码顺序执行的结果一致，所以重新排序过程并不会影响单线程程序的执行，却可能影响多线程程序并发执行的正确性。

    Java中可通过volatile在一定程序上保证顺序性，另外还可以通过synchronized和锁来保证顺序性。

    synchronized和锁保证顺序性的原理和保证原子性一样，都是通过保证同一时间只会有一个线程执行目标代码段来实现的。

    除了从应用层面保证目标代码段执行的顺序性外，JVM还通过被称为happens-before原则隐式地保证顺序性。两个操作的执行顺序只要可以通过happens-before推导出来，则JVM会保证其顺序性，反之JVM对其顺序性不作任何保证，可对其进行任意必要的重新排序以获取高效率。

- **happens-before原则**  

    **传递规则**：如果操作1在操作2前面，而操作2在操作3前面，则操作1肯定会在操作3前发生，该规则说明了happens-before原则具有传递性。

    **锁定规则**：一个unlock操作肯定会在后面对同一个锁的lock操作前发生。这个很好理解，锁只有被释放了才会被再次获取。

    **volatile变量规则**：对一个被volatile修饰的写操作先发生于后面对该变量的读操作

    **程序次序规则**：一个线程内，按照代码顺序执行。

    **线程启动规则**：Thread对象的start()方法先发生于此线程的其它动作。

    **线程终结原则**：线程的终止检测后发生于线程中其它的所有操作。

    **线程中断规则**： 对线程interrupt()方法的调用先发生于对该中断异常的获取。

    **对象终结规则**：一个对象构造先于它的finalize发生。

- **volatile适用场景**

    volatile适用于不需要保证原子性，但却需要保证可见性的场景。一种典型的使用场景是用它修饰用于停止线程的状态标记。如下所示:
    ```java
    boolean isRunning = false;
    public void start () {
        new Thread( () -> {
            while(isRunning) {
                someOperation();
            }
        }).start();
    }
    public void stop () {
        isRunning = false;
    }
    ```
    在这种实现方式下，即使其它线程通过调用stop()方法将isRunning设置为false，循环也不一定会立即结束。可以通过volatile关键字，保证while循环及时得到isRunning最新的状态从而及时停止循环，结束线程。

---

>#### **线程安全十万个为什么**

问：平时项目中使用锁和synchronized比较多，而很少使用volatile，难道就没有保证可见性？

答：锁和synchronized即可以保证原子性，也可以保证可见性。都是通过保证同一时间只有一个线程执行目标代码段来实现的。

问：锁和synchronized为何能保证可见性？

答：根据JDK 7的Java doc中对concurrent包的说明，一个线程的写结果保证对另外线程的读操作可见，只要该写操作可以由happen-before原则推断出在读操作之前发生。

    The results of a write by one thread are guaranteed to be visible to a read by another thread only if the write operation happens-before the read operation. The synchronized and volatile constructs, as well as the Thread.start() and Thread.join() methods, can form happens-before relationships.

问：既然锁和synchronized即可保证原子性也可保证可见性，为何还需要volatile？

答：synchronized和锁需要通过操作系统来仲裁谁获得锁，开销比较高，而volatile开销小很多。因此在只需要保证可见性的条件下，使用volatile的性能要比使用锁和synchronized高得多。

问：既然锁和synchronized可以保证原子性，为什么还需要AtomicInteger这种的类来保证原子操作？

答：锁和synchronized需要通过操作系统来仲裁谁获得锁，开销比较高，而AtomicInteger是通过CPU级的CAS操作来保证原子性，开销比较小。所以使用AtomicInteger的目的还是为了提高性能。

问：还有没有别的办法保证线程安全

答：有。尽可能避免引起非线程安全的条件——共享变量。如果能从设计上避免共享变量的使用，即可避免非线程安全的发生，也就无须通过锁或者synchronized以及volatile解决原子性、可见性和顺序性的问题。

问：synchronized即可修饰非静态方式，也可修饰静态方法，还可修饰代码块，有何区别

答：synchronized修饰非静态同步方法时，锁住的是当前实例；synchronized修饰静态同步方法时，锁住的是该类的Class对象；synchronized修饰静态代码块时，锁住的是synchronized关键字后面括号内的对象。

### **4. 创建线程池有哪几个核心参数？ 如何合理配置线程池的大小？**
>#### **5大参数**
-	核心线程数
-	最大线程数
-	线程空闲时间
-	阻塞队列大小：queueCapacity
-   任务拒绝处理器：rejectedExceptionHandler
---

### **5. volatile、ThreadLocal的使用场景和原理**
>#### **ThreadLocal原理及常用应用场景**
-	**对ThreadLocal的理解**

    ThreadLocal，很多地方叫做线程本地变量，也有些地方叫做线程本地存储， ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。
-	**深入解析ThreadLocal类**

    在上面谈到了对ThreadLocal的一些理解，那我们下面来看一下具体ThreadLocal是如何实现的。

    先了解一下ThreadLocal类提供的几个方法：
    ```java
    public T get() { }  
    public void set(T value) { }  
    public void remove() { }  
    protected T initialValue() { } 
    ```
     `get()`方法是用来获取`ThreadLocal`在当前线程中保存的变量副本，`set()`用来设置当前线程中变量的副本，`remove()`用来移除当前线程中变量的副本，`initialValue()`是一个`protected`方法，一般是用来在使用时进行重写的，它是一个延迟加载方法，下面会详细说明。

     `get`方法的实现：
    ```java
    public T get(){
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null){
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null){
                return (T)e.value;
            }
        }
        return setInitialValue();
    }
    ```
    第一句是取得当前线程，然后通过`getMap(t)`方法获取到一个map，map的类型为`ThreadLocalMap`。然后获取键值对，这里传入的是this，不是当前线程t。

    首先看下getMap方法中做了什么：
    ```java
    ThreadLocalMap getMap(Thread t){
        return t.threadLocals;
    }
    ```
    那么我们继续取Thread类中取看一下成员变量threadLocals是什么：
    ```java
    ThreadLocal.ThreadLocalMap threadLocals = null;
    ```
    实际上就是一个`ThreadLocalMap`，这个类型是`ThreadLocal`类的一个内部类：
    ```java
    static class ThreadLocalMap{
        static class Entry extends WeakReference<ThreadLocal>{
            Object value;

            Entry(ThreadLocal k,Object v){
                super(k);
                value = v;
            }
        }
    }
    ```
    可以看到`ThreadLocalMap`的Entry继承了`WeakReference`，并且使用`ThreadLocal`作为键值。

    继续看`setInitialValue`方法的具体实现：
    ```java
    private T setInitialValue{
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if(map != null){
            map.set(this,value);
        }else{
            createMap(t,value);
        }
        return value;
    }
    ```
    很容易了解，就是如果map不为空，就设置键值对，为空，再创建Map，看一下createMap的实现：
    ```java
    void createMap(Thread t,T firstValue){
        t.threadLocals = new ThreadLocalMap(this,firstValue);
    }
    ```

    所以，ThreadLocal是这样为每个线程创建变量的副本的：

    1. 在每个线程Thread内部有一个ThreadLocal.ThreadLocalMap类型的成员变量threadLocals，这个threadLocals就是用来存储实际的变量副本的，键为当前ThreadLocal，value为变量副本。
    2. 初始时，在Thread里面，threadLocals为空，当通过ThreadLocal变量调用get()方法或set()方法时，会对其进行初始化，并以当前ThreadLocal变量为键保存副本变量。
    3. 然后在当前线程里面，如果要使用副本变量，就可以通过get方法在threadLocals里面查找。

    总结一下：
    - 实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中。
    - 为何threadLocals的类型ThreadLocalMap的键为ThreadLocal对象，因为每个线程中可以有多个threadLocal变量。
    - 可以通过重写initialValue()方法来定制初始值。

-	**ThreadLocal的应用场景**

    最常见的ThreadLocal使用场景为用来解决数据库连接、Session管理等。

    数据库连接：
    ```java
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {  
        public Connection initialValue() {  
            return DriverManager.getConnection(DB_URL);  
        }  
    };  
  
    public static Connection getConnection() {  
        return connectionHolder.get();  
    } 
    ```

    Session管理：
    ```java
    private static final ThreadLocal threadSession = new ThreadLocal();

    public static Session getSession()throws InfrastructureException{
        Session session = (Session)threadSession.get();
        try{
            if(session == null){
                session = getSessionFactory().openSession();
                threadSession.set(session);
            }
        }catch(HibernateException e){
            throw new InfrastructureException(e);
        }
        return session;
    }
    ```
### **6. ThreadLocal什么时候会出现OOM的情况？为什么？**

先来看一个会造成OOM的实例：

```java
@Slf4j
public class MyThreadLocal {
    private static final int THREAD_LOOP_SIZE = 500;
    private static final int MOCK_DIB_DATA_LOOP_SIZE = 100000;
    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);
        // 初始化值的threadLocal
        ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "123");
        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            exec.execute(() -> {
                threadLocal.set(new MyThreadLocal().add());
                Thread thread = Thread.currentThread();
                log.info(thread.getName());
                // 不 remove 就会出现OOM
                // threadLocal.remove();
            });
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exec.shutdownNow();
    }
    private List<User> add() {
        List<User> users = new ArrayList<>(MOCK_DIB_DATA_LOOP_SIZE);
        for (int i = 0; i < MOCK_DIB_DATA_LOOP_SIZE; i++) {
            users.add(new User("qezhhnjy", "passwd" + i, "男", i));
        }
        return users;
    }
    @AllArgsConstructor
    @Data
    class User {
        private String username;
        private String password;
        private String gender;
        private int age;
    }
}
```
1. 首先看一下ThreadLocal的原理图，Entry的Key实际上是ThreadLocal对象的一个弱引用。
![](https://img-blog.csdn.net/20171023143842983?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvR29HbGVUZWNo/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
2. ThreadLocal的实现是这样的：每个Thread 维护一个 ThreadLocalMap 映射表，这个映射表的 key 是 ThreadLocal实例本身，value 是真正需要存储的 Object。

3. ThreadLocal 本身并不存储值，它只是作为一个 key 来让线程从 ThreadLocalMap 获取 value。图中的虚线表示 ThreadLocalMap 是使用 ThreadLocal 的弱引用作为 Key 的，**弱引用的对象在 GC 时会被回收**。

4. ThreadLocalMap使用ThreadLocal的弱引用作为key，如果一个ThreadLocal没有外部强引用来引用它，那么系统 GC 的时候，这个ThreadLocal势必会被回收，这样一来，ThreadLocalMap中就会出现key为null的Entry，就没有办法访问这些key为null的Entry的value，如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value永远无法回收，造成内存泄漏。

5. 总的来说就是，ThreadLocal里面使用了一个存在弱引用的map, map的类型是ThreadLocal.ThreadLocalMap. Map中的key为一个threadlocal实例。这个Map的确使用了弱引用，不过弱引用只是针对key。每个key都弱引用指向threadlocal。 当把threadlocal实例置为null以后，没有任何强引用指向threadlocal实例，所以threadlocal将会被gc回收。
但是，我们的value却不能回收，而这块value永远不会被访问到了，所以存在着内存泄露。因为存在一条从current thread连接过来的强引用。只有当前thread结束以后，current thread就不会存在栈中，强引用断开，Current Thread、Map value将全部被GC回收。

6. 其实，ThreadLocalMap的设计中已经考虑到这种情况，也加上了一些防护措施：在ThreadLocal的get(),set(),remove()的时候都会清除线程ThreadLocalMap里所有key为null的value。

7. 但是这些被动的预防措施并不能保证不会内存泄漏：
- 使用static的ThreadLocal，延长了ThreadLocal的生命周期，可能导致内存泄漏。
- 分配使用了ThreadLocal又不再调用get(),set(),remove()方法，那么就会导致内存泄漏，因为这块内存一直存在。

> **为什么使用弱引用，OOM是否是弱引用的锅？**

- 从表面上看内存泄漏的根源在于使用了弱引用。网上的文章大多着重分析ThreadLocal使用了弱引用会导致内存泄漏，但是另一个问题也同样值得思考：为什么使用弱引用而不是强引用？

    官方文档的说法：

        To help deal with very large and long-lived usages, the hash table entries use WeakReferences for keys.

- key使用强引用

    引用ThreadLocal的对象被回收了，但是ThreadLocalMap还持有ThreadLocal的强引用，如果没有手动删除，ThreadLocal不会被回收，导致Entry内存泄漏。
- key使用弱引用

    引用ThreadLocal的对象被回收了，由于ThreadLocalMap持有ThreadLocal的弱引用，即使没有手动删除，ThreadLocal也会被回收。value在下一次ThreadLocalMap调用set、get、remove的时候会被清除。

    比较两种情况，我们可以发现：由于ThreadLocalMap的生命周期跟Thread一样长，如果都没有手动删除对应key，都会导致内存泄漏，但是使用弱引用可以多一层保障：弱引用ThreadLocal不会内存泄漏，对应的value在下一次ThreadLocalMap调用set、get、remove的时候会被清除。

    因此，ThreadLocal内存泄漏的根源是：由于ThreadLocalMap的生命周期跟Thread一样长，如果没有手动删除对应key就会导致内存泄漏，而不是因为弱引用。

> **ThreadLocal最佳实践**
- **怎么避免内存泄漏**

    每次使用完ThreadLocal，都调用它的remove()方法，清除数据。

    在使用线程池的情况下，没有及时清理ThreadLocal，不仅是内存泄漏的问题，更严重的是可能导致业务逻辑出现问题。所以，使用ThreadLocal就跟加锁完要解锁一样，用完就清理。

    并不是所有使用ThreadLocal的地方，都在最后remove（），他们的生命周期可能是需要和项目的生存周期一样长的，所以要进行恰当的选择，以免出现业务逻辑错误！但首先应该保证的是ThreadLocal中保存的数据大小不是很大！

---

### **7. synchronized、volatile区别、synchronized锁粒度、模拟死锁场景、原子性与可见性**

>#### **synchronized、volatile区别**

- **volatile关键字的作用**

    其实volatile关键字的作用就是保证了可见性和有序性（不保证原子性），如果一个共享变量被volatile关键字修饰，那么如果一个线程修改了这个共享变量后，其他线程是立马可知的。为什么是这样的呢？比如，线程A修改了自己的共享变量副本，这时如果该共享变量没有被volatile修饰，那么本次修改不一定会马上将修改结果刷新到主存中，如果此时B去主存中读取共享变量的值，那么这个值就是没有被A修改之前的值。如果该共享变量被volatile修饰了，那么本次修改结果会强制立刻刷新到主存中，如果此时B去主存中读取共享变量的值，那么这个值就是被A修改之后的值了。

    volatile能禁止指令重新排序，在指令重排序优化时，在volatile变量之前的指令不能在volatile之后执行，在volatile之后的指令也不能在volatile之前执行，所以它保证了有序性。
- **synchroned关键字的作用**

    synchronized提供了同步锁的概念，被synchronized修饰的代码段可以防止被多个线程同时执行，必须一个线程把synchronized修饰的代码段都执行完毕了，其他的线程才能开始执行这段代码。
    
    因为synchronized保证了在同一时刻，只能有一个线程执行同步代码块，所以执行同步代码块的时候相当于是单线程操作了，那么线程的可见性、原子性、有序性（线程之间的执行顺序）它都能保证了。
- **volatile关键字和synchronized关键字的区别**

    1. volatile只能作用于变量，使用范围较小。synchronized可以作用于变量、方法、类、同步代码块等，使用范围比较广。
    2. volatile只能保证可见性和有序性，不能保证原子性。synchronized都可以保证。
    3. volatile不会造成线程阻塞，synchronized可能造成线程阻塞。

- **volatile不适用的场景**

    volatile不适用复合操作，count++不是一个原子性操作，是由读取、加、赋值3步组成：
    ```java
    class Test {
        public volatile int count = 0;

        public void add() {
            count++;
        }

        public static void main(String[] args) {
            final Test test = new Test();
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    for (int j = 0; j < 1000; j++) {
                        test.add();
                    }
                }).start();
            }
            while (Thread.activeCount() > 2) Thread.yield();
            System.out.println(test.count);
        }
    }
    ```

    采用synchronized：
    ```java
    class Test2{
        public int count = 0;
        public synchronized void add() {
            count ++;
        }

        public static void main(String[] args) {
            final Test2 test2 = new Test2();
            for (int i = 0; i < 10; i++) {
                new Thread(()->{
                    for (int j = 0; j < 1000; j++) {
                        test2.add();
                    }
                }).start();
            }

            while (Thread.activeCount() > 2)Thread.yield();
            System.out.println(test2.count);
        }
    }

    ```

    采用Lock：
    ```java
    class Test3{
        public int count = 0;
        private Lock lock = new ReentrantLock();

        public void add() {
            lock.lock();
            try{
                count ++;
            }finally {
                lock.unlock();
            }
        }

        public static void main(String[] args) {
            final Test3 test = new Test3();
            for (int i = 0; i < 10; i++) {
                new Thread(()->{
                    for (int j = 0; j < 1000; j++) {
                        test.add();
                    }
                }).start();
            }

            while (Thread.activeCount() > 2)Thread.yield();
            System.out.println(test.count);
        }
    }

    ```

    采用Java并发包中的原子操作类，原子操作类是通过CAS循环的方式来保证其原子性的：
    ```java
    class Test4{
        public AtomicInteger count = new AtomicInteger();
        public void add() {
            count.getAndIncrement();
        }
        public static void main(String[] args) {
            final Test4 test = new Test4();
            for (int i = 0; i < 10; i++) {
                new Thread(()->{
                    for (int j = 0; j < 1000; j++) {
                        test.add();
                    }
                }).start();
            }

            while (Thread.activeCount() > 2)Thread.yield();
            System.out.println(test.count);
        }
    }

    ```
> #### **模拟死锁的场景**

《Java编程思想》:三个人三根筷子，每个人需要拿到身边的两根筷子才能吃饭，出现死锁的场景是，三个人都拿了右边的筷子，但是由于筷子都被抢占，均无法获得左边的筷子。

```java
public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        int size = 3;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size]));
        }
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
@Data
class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
    }
}
@AllArgsConstructor
@Slf4j
@Data
class Philosopher implements Runnable {

    private Chopstick left;
    private Chopstick right;

    private void pause() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    private void pauseToHoldChopstick() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                log.info(this + " thinking");
                pause();
                right.take();
                log.info(this + " grabbing right");
                pauseToHoldChopstick();
                left.take();
                log.info(this + " grabbing left");
                log.info(this + " eating");
                pause();
                right.drop();
                log.info(this + " drop right");
                left.drop();
                log.info(this + " drop left");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

## **三、JVM相关**

### **1. JVM内存模型，GC机制和原理**

>#### **JVM内存模型**

根据JVM规范，JVM把内存划分成了如下几个区域：
1. 方法区(Method Area)
2. 堆区(Heap)
3. 虚拟机栈(VM Stack)
4. 本地方法栈(Native Method Stack)
5. 程序计数器(Program Counter Register)

![](http://upload-images.jianshu.io/upload_images/2032177-71ff90b07948fe2e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其中，方法区和堆所有线程共享。

- **方法区(Method Area)**

    方法区存放了要加载的类的信息（如类名、修饰符等）、静态变量、构造函数、final定义的常量、类中的字段和方法等信息。方法区是全局共享的，在一定条件下也会被GC。当方法区超过它允许的大小时，就会抛出OutOfMemory：PermGen Space异常。

    在Hotspot虚拟机中，这块区域对应**持久代（Permanent Generation）**，一般来说，方法区上执行GC的情况很少，因此方法区被称为持久代的原因之一，但这并不代表方法区上完全没有GC，其上的GC主要针对常量池的回收和已加载类的卸载。在方法区上进行GC，条件相当苛刻而且困难。

    **运行时常量池（Runtime Constant Pool）** 是方法区的一部分，用于存储编译器生成的常量和引用。一般来说，常量的分配在编译时就能确定，但也不全是，也可以存储在运行时期产生的常量。比如String类的intern（）方法，作用是String类维护了一个常量池，如果调用的字符”hello”已经在常量池中，则直接返回常量池中的地址，否则新建一个常量加入池中，并返回地址。

- **堆区(Heap)**

    堆区是GC最频繁的，也是理解GC机制最重要的区域。堆区由所有线程共享，在虚拟机启动时创建。堆区主要用于存放对象实例及数组，所有new出来的对象都存储在该区域。

- **虚拟机栈（VM Stack）**

    虚拟机栈占用的是操作系统内存，每个线程对应一个虚拟机栈，它是线程私有的，生命周期和线程一样，每个方法被执行时产生一个**栈帧(Stack Frame)**，栈帧用于存储局部变量表、动态链接、操作数和方法出口等信息，当方法被调用时，栈帧入栈，当方法调用结束时，栈帧出栈。

    **局部变量表**中存储着方法相关的局部变量，包括各种基本数据类型及对象的引用地址等，因此它有个特点：内存空间可以在编译期间就确定，运行时不再改变。

    虚拟机栈定义了两种异常类型：**StackOverFlowError(栈溢出)**和**OutOfMemoryError(内存溢出)**。如果线程调用的栈深度大于虚拟机允许的最大深度，则抛出StackOverFlowError；不过大多数虚拟机都允许动态扩展虚拟机栈的大小，所以线程可以一直申请栈，直到内存不足时，抛出OutOfMemoryError。

- **本地方法栈(Native Method Stack)**

    本地方法栈用于支持native方法的执行，存储了每个native方法的执行状态。本地方法栈和虚拟机栈他们的运行机制一致，唯一的区别是，虚拟机栈执行Java方法，本地方法执行native方法。在很多虚拟机中，会将虚拟机栈和本地方法栈一起使用。

- **程序计数器**

    程序计数器是一个很小的内存区域，不再RAM上，而是直接划分在CPU上，程序员无法操作它，它的作用是：JVM在解释字节码(.class)文件时，存储当前线程执行的字节码行号，只是一种概念模型，各种JVM所采用的方式不一样。字节码解释器工作时，就是通过改变程序计数器的值来取下一条要执行的指令，分支、循环、跳转等基础功能都是依赖此技术区完成的。

    每个程序计数器只能记录一个线程的行号，因此它是线程私有的。

    如果程序当前执行的是一个java方法，则程序计数器记录的是正在执行的虚拟机字节码指令地址，如果执行的是native方法，则计数器的值为空，此内存区是唯一不会抛出OutOfMemoryError的区域。

>#### **GC机制**

随着程序的运行，内存中的实例对象、变量等占据的内存越来越多，如果不及时进行回收，会降低程序运行效率，甚至引发系统异常。

在上面介绍的五个内存区域中，有3个是不需要进行垃圾回收的：本地方法栈、程序计数器、虚拟机栈。因为他们的生命周期是和线程同步的，随着线程的销毁，他们占用的内存会自动释放。所以，只有方法区和堆区需要进行垃圾回收，回收的对象就是那些不存在任何引用的对象。

- **查找算法**

    经典的**引用计数算法**，每个对象添加到引用计数器，每被引用一次，计数器+1，失去引用，计数器-1，当计数器在一段时间内为0时，即认为该对象可以被回收了。但是这个算法有个明显的缺陷：当两个对象相互引用，但二者都已经没有作用时，理应把它们都回收，但是由于它们相互引用，不符合垃圾回收条件，所以导致无法处理掉这一块内存区域。因此Sun的JVM没有采用这种算法，而是采用的**根搜索算法(可达性算法)**。

    基本思想是：从一个叫GC Roots的根节点出发，向下搜索，如果一个对象不能达到GC Roots的时候，说明该对象不再被引用，可以被回收。这样就解决了引用计数算法的缺陷。

    在JDK1.2后引入了四个概念：**强引用**、**软引用**、**弱引用**、**虚引用**。
    **强应用**：new出来的对象都是强引用，GC无论如何都不会回收，即使抛出
    OOM异常。
    **软应用**：只有当JVM内存不足时才会被回收。
    **弱应用**：只要GC，就会立马回收，不管内存是否充足。
    **虚应用**：可以忽略不计，JVM完全不会在乎虚引用，你可以理解为它是来凑数的，凑够”四大天王”。它唯一的作用就是做一些跟踪记录，辅助finalize函数的使用。

    最后总结：什么样的类需要被回收：

        1. 该类的所有实例都已经被回收。
        2. 加载该类的ClassLoad已经被回收。
        3. 该类对应的反射类java.lang.Class对象没有被任何地方引用。

- **内存分区**

    [**Java方法区、永久代、元空间、常量池详解**](https://blog.csdn.net/u011635492/article/details/81046174)

    [**JDK8-废弃永久代(PermGen)迎来元空间(Metaspace)**](https://www.cnblogs.com/yulei126/p/6777323.html)

    内存主要被分为三块：**新生代(Young Generation)**、**旧生代(Old Generation)**、**持久代(Permanent Generation）**。三代的特点不同，造就了他们使用的GC算法不同，新生代适合生命周期较短，快速创建和销毁的对象，旧生代适合生命周期较长的对象，持久代在Sun Hotpot虚拟机中就是指方法区(有些JVM根本没有持久代这一说法)。

    **新生代(Young Generation)**：大致分为Eden区和Survivor区，Survivor区又分为大小相同的两部分：FromSpace和ToSpace。新建的对象都是从新生代分配内存，Eden不足时，会把存活的对象转移到Survivor区。当新生代进行垃圾回收时会触发**Minor GC**。
    **旧生代(Old Generation)**：旧生代用于存放新生代多次回收依然存货的对象，如缓存对象。当旧生代满了就需要对旧生代进行回收，旧生代的垃圾回收称作**Major GC**。
    **持久代(Permanent Generation)**：在Sun的JVM中就是方法区的意思，尽管大多数JVM没有这一代。

- **GC算法**

    **常见的GC算法：复制、标记-清除、标记-压缩**

    **复制**:复制算法采用的方式为从根集合进行扫描，将存活的对象移动到一块空闲的区域：

    ![](http://upload-images.jianshu.io/upload_images/2032177-0f409441396e91fd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


    当存活的对象较少时，复制算法会比较高效(新生代的Eden区就是采用这种算法)，其带来的成本是需要一块额外的空闲空间和对象的移动。

    **标记-清除**：该算法采用的方式是从根集合开始扫描，对存活的对象进行标记，标记完毕后，再扫描整个空间中未被标记的对象，并进行清除。
    ![](http://upload-images.jianshu.io/upload_images/2032177-6cc2dd284de2888a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    ![](http://upload-images.jianshu.io/upload_images/2032177-f06f598289ef1d9d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    清除阶段清理的是没有被引用的对象，存活的对象被保留。
    标记-清除动作不需要移动对象，且仅对不存货的对象进行清理，在空间中存活对象较多的时候，效率较高，但由于只是清除，没有重新整理，因此会造成内存碎片。

    **标记-压缩**：该算法与标记-清除算法类似，都是先对存活的对象进行标记，但是在清除后把活的对象向左端空闲空间移动，然后再更新其引用对象的指针。

    ![](http://upload-images.jianshu.io/upload_images/2032177-9de538386e7c8947.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    由于进行了移动规整动作，该算法避免了标记-清除的碎片问题，但由于需要进行移动，因此成本也增加了(该算法适用于旧生代)。

>#### **垃圾收集器**

- **串行收集器(Serial GC)**

    **Serial GC**
    
    是最古老也是最基本的收集器，但是现在依然广泛使用，JAVA SE5和JAVA SE6中客户端虚拟机采用的默认配置。比较适合于只有一个处理器的系统。在串行处理器中minor和major GC过程都是用一个线程进行回收的。它的最大特点是在进行垃圾回收时，需要对所有正在执行的线程暂停，对于有些应用是难以接受的，但是如果应用的实时性要求不是那么高，只要停顿的时间控制在N毫秒之内，大多数应用还是可以接受的，而且事实上，它并没有让我们失望，几十毫秒的停顿，对于客户机也是完全可以接受的，该收集器适用于单CPU、新生代空间较小且对暂停时间要求不高的应用上，是client级别的默认GC方式。

- **ParNew GC**

    基本和Serial GC一样，但本质区别是加入了多线程机制，提高了效率，这样它就可以被用于服务端上，同时它可以与CMS GC配合，所以，更加有理由将它用于server端。

- **Parallel Scavenge GC**

    在整个扫描和赋值过程采用多线程的方式进行，适用于多CPU、对暂停时间要求较短的应用，是server级别的默认GC方式。

- **CMS(Concurrent Mark Sweep)收集器**

    该收集器的目标是解决Serial GC停顿的问题，以达到最短回收时间。常见的B/S架构的应用就适合这种收集器，因为其高并发、高响应的特点，
    CMS是基于标记-清除算法实现的。

    CMS收集器的优点：并发收集、低停顿，但远没有达到完美。

    CMS收集器的缺点：

    - CMS收集器对CPU资源非常敏感，在并发阶段虽然不会导致用户停顿，但    是会占用CPU资源而导致应用程序变慢，总吞吐量下降。 
    - CMS收集器无法处理浮动垃圾，可能出现“Concurrnet Mode           Failure”，失败而导致另一次的Full GC。 
    - CMS收集器是基于标记-清除算法的实现，因此也会产生碎片。

- **G1收集器**

    相比CMS收集器有不少改进，首先，基于标记-压缩算法，不会产生内存碎片，其次可以比较精确的控制停顿。

- **Serial Old收集器**

    Serial Old是Serial收集器的老年代版本，它同样使用一个单线程执行收集，使用标记-整理算法。主要使用在Client模式下的虚拟机。

- **Parallel Old收集器**

    Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和标记-整理算法。

- **RTSJ垃圾收集器**

    RTSJ垃圾收集器，用于Java实时编程。

### **2. GC分哪两种，Minor GC 和Full GC有什么区别？什么时候会触发Full GC？分别采用什么算法？**

- **Minor GC**:
    MinorGC采用复制算法。首先，把Eden和ServivorFrom区域中存活的对象复制到ServicorTo区域（如果有对象的年龄以及达到了老年的标准，则复制到老年代区），同时把这些对象的年龄+1（如果ServicorTo不够位置了就放到老年区）；然后，清空Eden和ServicorFrom中的对象；最后，ServicorTo和ServicorFrom互换，原ServicorTo成为下一次GC时的ServicorFrom区。

- **Major GC**:
    Major GC采用标记-清除算法：首先扫描一次所有老年代，标记出存活的对象，然后回收没有标记的对象。Major GC的耗时比较长，因为要扫描再回收。Major GC会产生内存碎片，为了减少内存损耗，我们一般需要进行合并或 标记出来方便下次直接分配。

    [Java GC工作原理以及Minor GC、Major GC、Full GC、GC收集相关算法整理](https://blog.csdn.net/lovexiaoqiqi/article/details/81737213)

    [GC算法](https://blog.csdn.net/youyou1543724847/article/details/52728244)

### **3. JVM里的有几种classloader，为什么会有多种？**

类装载工作由ClassLoader及其子类负责，ClassLoader是一个重要的Java执行时系统组件，它负责在运行时查找和装入Class字节码文件。JVM在运行时会产生三个ClassLoader：根装载器、ExtClassLoader（扩展类装载器）和AppClassLoader（系统类装载器）。其中，根装载器不是ClassLoader的子类，它使用C++编写，因此我们在Java中看不到它，根装载器负责装载JRE的核心类库，如JRE目标下的rt.jar、charsets.jar等。ExtClassLoader和AppClassLoader都是ClassLoader的子类。其中ExtClassLoader负责装载JRE目录ext中的JAR类包；AppClassLoader负责装载ClassPath路径下的类包。

具体加载方式可以在`sun.misc.Launcher`类中查看，它是一个java虚拟机的入口应用。

- **启动类加载器(Bootstrap ClassLoader)**:这个类加载器负责对存放在<JAVA_HMOE>/lib目录中的jar/class文件进行加载。启动类加载器无法被Java程序直接引用，用户在编写自定义类加载器时，如果需要把加载请求委派给引导类加载器，那直接使用null代替即可。`System.getProperty("sun.boot.class.path")`可查看其加载路径。
- **扩展类加载器(Extension ClassLoader)**:这个加载器由sun.misc.Launcher$ExtClassLoader实现，它负责加载<JAVA_HOME>/lib/ext目录中的jar/class文件，或者被java.ext.dirs系统变量所指定的路径中的所有类库，开发者可以直接使用扩展类加载器。`System.getProperty("java.ext.dirs")`可查看其加载路径。
- **应用程序类加载器(Application ClassLoader)**:这个类加载器由sun.misc.Launcher$AppClassLoader实现。由于这个类加载器是`ClassLoader中的getSystemClassLoader()`方法的返回值，所以一般也称为系统类加载器。它负责加载用户类路径(classpath)上所指定的类库，开发者可以直接使用这个类加载器，如果应用程序中没有自定义自己的类加载器，这个就是程序默认的类加载器。


```java
@Slf4j
public class MyClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = MyClassLoader.class.getClassLoader();
        log.info("ClassLoader : {}", classLoader);
        log.info("parent : {}", classLoader.getParent());
        log.info("parent's parent : {}", classLoader.getParent().getParent());
    }
}
// ClassLoader : sun.misc.Launcher$AppClassLoader@18b4aac2
// parent : sun.misc.Launcher$ExtClassLoader@37f8bb67
// parent's parent : null
```
**Bootstrap ClassLoader是由C++编写的。**
Bootstrap ClassLoader是由C/C++编写的，它本身是虚拟机的一部分，所以它并不是一个JAVA类，也就是无法在java代码中获取它的引用。JVM初始化sum.misc.Launcher并创建Extension ClassLoader和AppClassLoader实例。并将ExtClassLoader设置为AppClassLoader的父加载器。Bootstrap没有父加载器，但它可以作为ExtClassLoader的父加载器。

### **4. 什么是双亲委派机制？介绍一些运作过程，双亲委派模型的好处**

>#### **双亲委托**
一个类加载器查找class和resource时，是通过“委托模式”进行的，它首先判断这个class是不是已经加载成功，如果没有的话它并不是自己进行查找，而是先通过父加载器，然后递归下去，直到Bootstrap ClassLoader，如果Bootstrap classloader找到了，直接返回，如果没有找到，则一级一级返回，最后到达自身去查找这些对象。这种机制就叫做双亲委托。
整个流程可以如下图所示：

![](https://img-blog.csdn.net/20170211135054825?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYnJpYmx1ZQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

1. 一个AppClassLoader查找资源时，先看看缓存是否有，缓存有从缓存中获取，否则委托给父加载器。
2. 递归，重复第1部的操作。
3. 如果ExtClassLoader也没有加载过，则由Bootstrap ClassLoader出面，它首先查找缓存，如果没有找到的话，就去找自己的规定的路径下，也就是sun.mic.boot.class下面的路径。找到就返回，没有找到，让子加载器自己去找。
4. Bootstrap ClassLoader如果没有查找成功，则ExtClassLoader自己在java.ext.dirs路径中去查找，查找成功就返回，查找不成功，再向下让子加载器找。
5. ExtClassLoader查找不成功，AppClassLoader就自己查找，在java.class.path路径下查找。找到就返回。如果没有找到就让子类找，如果没有子类会怎么样？抛出各种异常。

**我们可以发现委托是从下向上，然后具体查找过程却是自上至下。**

上面已经详细介绍了加载过程，但具体为什么是这样加载，我们还需要了解几个重要的方法loadClass()、findLoadedClass()、findClass()、defineClass()。

>#### **重要方法**

- **loadClass()**

    JDK文档中是这样写的，通过指定的全限定类名加载class，它通过同名的loadClass(String,boolean)方法。
    ```java
    protected Class<?> loadClass(String name,boolean resolve)throws ClassNotFoundException
    ```
    上面是方法原型，一般实现这个方法的步骤是：
    1. 执行`findLoadedClass(String)`去检测这个class是不是已经加载过了。
    2. 执行父加载器的`loadClass`方法。如果父加载器为null，则JVM内置的加载器去替代(Bootstrap ClassLoader)。
    3. 如果向上委托父加载器没有加载成功，则通过`findClass(String)`查找。

    如果class在上面的步骤中找到了，参数resolve又是true的话，那么`loadClass()`又会调用`resolveClass(Class)`这个方法来生成最终的Class对象。
    ```java
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException{
            synchronized (getClassLoadingLock(name)) {
                // 首先，检测是否已经加载
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    try {
                        if (parent != null) {
                            //父加载器不为空则调用父加载器的loadClass
                            c = parent.loadClass(name, false);
                        } else {
                            //父加载器为空则调用Bootstrap Classloader
                            c = findBootstrapClassOrNull(name);
                        }
                    } catch (ClassNotFoundException e) {
                        // ClassNotFoundException thrown if class not found
                        // from the non-null parent class loader
                    }

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        //父加载器没有找到，则调用findclass
                        c = findClass(name);

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    //调用resolveClass()
                    resolveClass(c);
                }
                return c;
            }
        }
    ```

    另外，要注意的是如果要编写一个ClassLoader的子类，也就是自定义一个ClassLoader，建议覆盖`findClass()`方法，而不要直接改写`loadClass()`方法。

    ```java
    if(parent != null){
        c = parent.loadClass(name, false);
    } else {
        c = findBootstrapClassOrNull(name);
    }
    ```

>#### **自定义ClassLoader**

不知道大家有没有发现，不管是Bootstrap ClassLoader还是ExtClassLoader等，这些类加载器都只是加载指定的目录下的jar包或资源。如果在某种情况下，我们需要动态加载一些东西呢？比如从D盘某个文件夹加载一个class文件，或者从网络上下载class主内容然后再进行加载。

如果要这样做的话，需要我们自定义一个ClassLoader。

- **自定义步骤**

1. 编写一个类继承自ClassLoader抽象类。
2. 复写它的`findClass()`方法。
3. 在`findClass()`方法中调用`defineClass()`。

- **defineClass()**

    这个方法在编写自定义ClassLoader的时候非常重要，它能将class二进制内容转换成Class对象，如果不符合要求的会抛出各种异常。

- **注意点：**

    一个ClassLoader创建时如果没有指定parent，那么它的parent默认就是AppClassLoader。

    上面说的是，如果自定义一个ClassLoader，默认的parent父加载器是AppClassLoader，因为这样就能够保证它能访问系统内置加载器加载成功的class文件。

- **自定义ClassLoader示例之DiskClassLoader**

    假设我们需要一个自定义的ClassLoader，默认加载路径为/home/fuzy/Documents下的jar包和资源。
    **Test.java**
    ```java
    package com.fuzy;
    public class Test{
        public void say(){
            System.out.println("Say Hello");
        }
    }
    ```
    我们通过`javac Test.java`命令编译该java文件。

    **DiskClassLoader**
    ```java
    package com.fzyszsz.study.first;

    import java.io.*;
    import java.lang.reflect.InvocationTargetException;
    import java.lang.reflect.Method;

    /**
    * @author fuzy
    * create time 19-3-10-下午8:56
    */
    public class DiskClassLoader extends ClassLoader {

        private String mLibPath;

        public DiskClassLoader(String path) {
            mLibPath = path;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String fileName = getFileName(name);
            File file = new File(mLibPath, fileName);
            try {
                FileInputStream is = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len;
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
                byte[] data = bos.toByteArray();
                is.close();
                bos.close();
                return defineClass(name, data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return super.findClass(name);
        }

        private static String getFileName(String name) {
            int index = name.lastIndexOf(".");
            if (index == -1) {
                return name + ".class";
            } else {
                return name.substring(index + 1) + ".class";
            }
        }

        public static void main(String[] args) {
            DiskClassLoader disk = new DiskClassLoader("/home/fuzy/Documents");
            try {
                // 这里的全名跟Test.class在mLibPath的路径无关，跟类开头的package相关
                Class c = disk.loadClass("com.fuzy.Test");
                if (c != null) {
                    try{
                        Object obj = c.newInstance();
                        Method method = c.getDeclaredMethod("say", null);
                        method.invoke(obj, null);
                    } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    ```
    我们在`findClass()`方法中定义了查找class的方法，然后数据通过`defineClass()`生成了Class对象。

- **关键字 路径**

    BootStrap ClassLoader、ExtClassLoader、AppClassLoader都是加载指定路径下的jar包。如果我们要突破这种限制，实现自己某些特殊的需求，我们就得自定义ClassLoader，自已指定加载的路径，可以是磁盘、内存、网络或者其它。

> #### **Class解密类加载器**
常见的用法是将Class文件按照某种加密手段进行加密，然后按照规则编写自定义的ClassLoader进行解密，这样我们就可以在程序中加载特定类，并且这个类只能被我们自定义的加载器进行加载，提高了程序的安全性。

- **定义加密解密协议**

加密和解密的协议有很多种，具体怎么定看业务需求。这里简单将加密解密定义为异或运算，当一个文件进行异或运算后，产生加密文件，再进行一次异或后，就进行解密。

- **编写加密工具类**

`Encrypt.java`
```java
class Encrypt {
    public static final int SALT = Integer.MAX_VALUE;

    public static void encode(String path) {
        try {
            InputStream input = Files.newInputStream(Paths.get(path));
            OutputStream output = Files.newOutputStream(Paths.get(path + "fu"));
            int b;
            while ((b = input.read()) != -1) {
                output.write(b ^ SALT);
            }
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] decode(String path) {
        try {
            InputStream input = Files.newInputStream(Paths.get(path));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = input.read()) != -1) {
                out.write(b ^ SALT);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

- **Context ClassLoader线程上下文类加载器**

    前面讲到过Bootstrap ClassLoader、ExtClassLoader、AppClassLoader，现在又出来这么一个类加载器，这是为什么？

    前面三个之所以放在前面讲，是因为它们是真实存在的类，而且遵从”双亲委托“的机制。而ContextClassLoader其实只是一个概念。

    查看`Thread.java`源码：
```java
public class Thread implements Runnable {

/* The context ClassLoader for this thread */
   private ClassLoader contextClassLoader;
   
   public void setContextClassLoader(ClassLoader cl) {
       SecurityManager sm = System.getSecurityManager();
       if (sm != null) {
           sm.checkPermission(new RuntimePermission("setContextClassLoader"));
       }
       contextClassLoader = cl;
   }

   public ClassLoader getContextClassLoader() {
       if (contextClassLoader == null)
           return null;
       SecurityManager sm = System.getSecurityManager();
       if (sm != null) {
           ClassLoader.checkClassLoaderPermission(contextClassLoader,
                                                  Reflection.getCallerClass());
       }
       return contextClassLoader;
   }
}
```
contextClassLoader只是一个成员变量，通过`setContextClassLoader()`方法设置，通过`getContextClassLoader()`设置。

每个Thread都有一个相关联的ClassLoader，默认是AppClassLoader。并且子线程默认使用父线程的ClassLoader除非子线程特别设置。

[具体实例详情可以看这里](https://blog.csdn.net/briblue/article/details/54973413)

---

### **5. 什么情况下我们需要破坏双亲委派模型**
[深入理解 Tomcat（四）Tomcat 类加载器之为何违背双亲委派模型](https://blog.csdn.net/qq_38182963/article/details/78660779)

---
### **6. 常见的JVM调优方法有哪些？可以具体到调整哪个参数，调成什么值**
>#### **JVM参数**
在JVM启动参数中，可以设置跟内存、垃圾回收相关的一些参数设置，默认情况下不做任何设置JVM会工作的很好，但对一些配置很好的Server和具体的应用必须仔细调优才能获得最佳性能。通过设置我们希望达到一些目标：
- **GC的时间足够短**
- **GC的次数足够少**
- **发生Full GC的周期足够长**

前两个目前是相悖的，要想GC时间小必须要一个更小的堆，要保证GC次数足够少，必须保证一个更大的堆，我们只能取其平衡。

1. 针对JVM堆的设置，一般可以通过-Xms -Xmx限定其最小、最大值，**为了防止垃圾收集器在最小、最大之间收缩堆而产生额外的时间，我们通过把最大、最小设置为相同的值**。
2. **年轻代和老年代将根据默认的比例(1:2)分配堆内存**，可以通过调整二者之间的比率NewRadio来调整二者之间的大小，也可以针对回收代，比如年轻代，通过`-XX:newSize -XX:MaxNewSize`来设置其绝对大小。同样，为了防止年轻代的堆压缩，我们通常会把`-XX:newSize -XX:MaxNewSize`设置为同样大小。
3. 年轻代和老年代设置多大才算合理？这个问题没有答案，我们可以观察二者大小变化有哪些影响。

    - **更大的年轻代必然导致更小的老年代，大的年轻代会延长Minor GC的周期，但会增加每次GC的时间；小的老年代会导致更频繁的Full GC**
    - **更小的年轻代必然导致更大的老年代，小的年轻代会导致Minor GC很频繁，但每次的GC时间会更短；大的老年代会减少Full GC的频率**
    - 如何选择应该依赖应用程序**对象生命周期的分布情况**：如果应用存在大量的临时对象，应该选择更大的年轻代；如果存在相对较多的持久对象，老年代应该适当增大，但很多应用都没有这样明显的特性，在抉择时应该根据以下两点：(A)本着Full GC尽量少的原则，让老年代尽量缓存常用对象，JVM默认比例1:2也是这个道理 (B)通过观察应用一段时间，看其他在峰值时老年代会占用多少内存，在不影响Full GC的前提下，根据实际情况加大年轻代，比如可以把比例控制在1：1。但应该给老年代至少预留1/3的增长空间。

4. **在配置较好的机器上(比如多核、大内存)，可以为老年代选择并行收集算法：-XX:+UseParallelOldGC**，默认为Serial收集。

5. 线程堆栈的设置：每个线程默认会开启1M的堆栈，用于存放栈帧、调用参数、局部变量等，对大多数应用而言这个默认值太大了，一般256K就足够了。理论上，在内存不变的情况下，减少每个线程的堆栈，可以产生更多的线程，但这实际上还受限与操作系统。

6. 可以通过下面的参数打印Heap Dump信息：

    - `-XX:HeapDumpPath`
    - `-XX:+PrintGCDetails`
    - `-XX:+PrintGCTimeStamps`
    - `-Xloggc:/usr/aaa/dump/heap_trace.txt`

        通过下面的参数可以控制OutOfMemoryError时打印堆的信息：
    - `-XX:+HeapDumpOnOutOfMemoryError`

 请看一下一个实际的Java参数配置：（服务器：Linux 64Bit，8Core×16G）

 
```java
 JAVA_OPTS="$JAVA_OPTS -server -Xms3G -Xmx3G -Xss256k -XX:PermSize=128m -XX:MaxPermSize=128m -XX:+UseParallelOldGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/aaa/dump -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:/usr/aaa/dump/heap_trace.txt -XX:NewSize=1G -XX:MaxNewSize=1G"
 ```

经过观察该配置非常稳定，每次普通GC的时间在10ms左右，Full GC基本不发生，或隔很长很长的时间才发生一次

通过分析dump文件可以发现，每个1小时都会发生一次Full GC，经过多方求证，只要在JVM中开启了JMX服务，JMX将会1小时执行一次Full GC以清除引用，关于这点请参考附件文档。

### **7. JVM虚拟机内存划分、类加载器、垃圾收集算法、垃圾收集器、class文件结构是如何解析的**

---
## **四、Java扩展篇**

### **1. 红黑树的实现原理和应用场景**
>#### **红黑树的介绍**
先来看下算法导论对R-B Tree的介绍：
红黑树，一种二叉查找树，但在每个节点上增加一个存储位表示节点的颜色，可以是Red或Black。
通过对任何一条从根到叶子的路径上各个节点着色方式的限制，红黑树确保没有一条路径会比其他路径长两倍，因而是接近平衡的。

> #### **二叉查找树**
红黑树，作为一颗二叉查找树，满足二叉查找树的一般性质：
二叉查找树，也称有序二叉树，或已排序二叉树，是指一颗空树或者具有下列性质的二叉树：
- 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
- 若任意节点的右子树不空，则右子树上所有结点的值均大于它的根节点的值；
- 任意节点的左、右子树也分别为二叉查找树；
- 没有键值相等的结点。

因为一颗由n个节点随机构造的二叉查找树的高度为log2N，所以顺理成章，二叉查找树的一般操作的执行时间为O(log2N)。但二叉查找树若退化成了一颗具有n个节点的线性链后，则这些操作最坏情况的运行时间为O(n)。

红黑树虽然本质上是一棵二叉查找树，但它在二叉查找树的基础上增加了着色和相关的性质使得红黑树相对平衡，从而保证了红黑树的查找、插入、删除的时间复杂度最坏为O(logn)。

但它是如何保证一棵n个节点的红黑树的高度始终保持的log2N的？这就引出了**红黑树的5个性质**：
1. 每个节点要么是红的要么是黑的。
2. 根节点是黑的。
3. 每个叶节点(叶节点即指树尾端NIL指针或NULL节点)都是黑的。
4. 如果一个节点是红的，那么它的两个子节点都是黑的。
5. 对于任意节点而言，其到叶节点树尾端NIL指针的每条路径都包含相同数目的黑节点。

正是红黑树的这5条性质，使一棵n个结点的红黑树始终保持了logn的高度，从而也就解释了上面所说的“红黑树的查找、插入、删除的时间复杂度最坏为O(log n)”这一结论成立的原因。

（注：上述第3、5点性质中所说的NULL结点，包括wikipedia.算法导论上所认为的叶子结点即为树尾端的NIL指针，或者说NULL结点。然百度百科以及网上一些其它博文直接说的叶结点，则易引起误会，因，此叶结点非子结点）

如下图所示，即是一颗红黑树：
![红黑树](https://img-my.csdn.net/uploads/201212/12/1355319681_6107.png)

此图忽略了叶子和根部的父节点。同时，上文中我们所说的 "叶结点" 或"NULL结点"，如上图所示，它不包含数据而只充当树在此结束的指示，这些节点在绘图中经常被省略，望看到此文后的读者朋友注意。 

>#### **树的旋转知识**
当在对红黑树进行插入和删除等操作时，对树做了修改可能会破坏红黑树的性质。为了继续保持红黑树的性质，可以通过对节点进行重新着色，以及对树进行相关的旋转操作，即通过修改树中某些节点的颜色及指针结构，来达到对红黑树进行插入或删除节点等操作后继续保持它的性质或平衡的目的。

树的旋转分为左旋和右旋：
1. **左旋**
![左旋](https://img-my.csdn.net/uploads/201012/29/8394323_1293614183gD0H.jpg)
如上图所示，当在某个节点pivot上，做左旋操作时，我们假设它的右子节点Y不是NIL，pivot可以为任何不是NIL的左子节点。左旋以pivot到Y之间的链为“支轴”进行，它使Y成为该子树的新根，而Y的左子节点b则成为pivot的右子节点。

2. **右旋**
![右旋](https://img-my.csdn.net/uploads/201012/29/8394323_1293614183DSC3.jpg)

树在经过左旋右旋之后，树的搜索性质保持不变，但树的红黑性质则被破坏了，所以，红黑树插入和删除数据后，需要利用旋转和颜色重涂来重新恢复树的红黑性质。

>#### **红黑树的插入**
要真正理解红黑树的插入，还得先理解二叉查找树的插入。


### **2. NIO是什么？适用于何种场景？**

### **3. Java9比Java8改进了什么**

### **4. HashMap内部的数据结构是什么？底层是怎么实现的？（还可能会延伸考察ConcurrentHashMap与HashMap、HashTable等，考察对技术细节的深入了解程度）**

### **5. 说说反射的用途及实现，反射是不是很慢，我们在项目中是否要避免使用反射**

### **6. 说说自定义注解的场景及实现**

### **7. List 和 Map 区别，Arraylist 与 LinkedList 区别，ArrayList 与 Vector 区别**

---
## **五、Spring相关**

### **1. Spring AOP的实现原理和场景**

### **2. Spring bean的作用域和生命周期**

### **3. Spring Boot比Spring做了哪些改进？ Spring 5比Spring4做了哪些改进**

### **4. 如何自定义一个Spring Boot Starter**

### **5. Spring IOC是什么？优点是什么？**

### **6. SpringMVC、动态代理、反射、AOP原理、事务隔离级别**

---
## **六、中间件篇**

### **1. Dubbo完整的一次调用链路介绍**

### **2. Dubbo支持几种负载均衡策略**

### **3. Dubbo Provider服务提供者要控制执行并发请求上限，具体怎么做**

### **4. Dubbo启动的时候支持几种配置方式**

### **5. 了解几种消息中间件产品？各产品的优缺点介绍**

### **6. 消息中间件如何保证消息的一致性和如何进行消息的重试机制**

### **7. Spring Cloud熔断机制介绍**

### **8. Spring Cloud对比下Dubbo，什么场景下该使用Spring Cloud**
---
## **七、数据库篇**

### **1. 锁机制介绍：行锁、表锁、排他锁、共享锁**

### **2. 乐观锁的业务场景及实现方式**

### **3. 事务介绍，分布式事物的理解，常见的解决方案有哪些，什么事两阶段提交、三阶段提交**

### **4. MySQL记录binlog的方式主要包括三种模式？每种模式的优缺点是什么？**

### **5. MySQL锁，悲观锁、乐观锁、排它锁、共享锁、表级锁、行级锁**

### **6. 分布式事务的原理2阶段提交，同步\异步\阻塞\非阻塞**

### **7. 数据库事务隔离级别，MySQL默认的隔离级别、Spring如何实现事务、JDBC如何实现事务、嵌套事务实现、分布式事务实现**

### **8. SQL的整个解析、执行过程原理、SQL行转列**
---
## **八、Redis**

### **1. Redis为什么这么快？redis采用多线程会有哪些问题**

### **2. Redis支持哪几种数据结构**

### **3. Redis跳跃表的问题**

### **4. Redis单进程单线程的Redis如何能够高并发**

### **5. Redis如何使用Redis实现分布式锁**

### **6. Redis分布式锁操作的原子性，Redis内部是如何实现的**

## **九、其他**

看过哪些源代码？然后会根据你说的源码问一些细节的问题？（这里主要考察面试者是否对技术有钻研的精神，还是只停留在表面，还是背了几道面经，这个对于很多有强迫症的面试官，如果你连源码都没看过，基本上是会pass掉的，比如我也是这样的！）

项目中遇到了哪些比较有挑战性的问题，是如何解决的；（这个很有争议，一方面是你连一个复杂的问题都解决不了，要你过来干什么，还有就是我的能力牛逼啊，但是公司没有业务场景让我展示啊！这个就看你遇到的面试官了，祝你好运！）

