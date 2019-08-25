<style>
h1,h2,h3,h4,h5,h6{
    font-weight:bold;
}
h1{
    color:#b43;
    font-size:60px;
}
h2{
    color:#5fa;
    font-size:30px;
}
h3{
    color:#f88;
    font-size:24px;
}
h4{
    color:#abf;
    font-size:20px
}
h5{
    font-size:16px;
    color:#abf;
}
p.red{
    color:red;
    font-weight:bold;
    font-size:16px;
}
</style>
# Java 线程池

## 1. 线程池详解
>### `ThreadPoolExecutor`

`ThreadPoolExecutor`是最灵活的一个线程池,用户可以根据实际需要通过多个参数配置出合适的线程池,构造方法如下:

```java
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
 BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) 
```
>### 参数说明
参数名|	说明
-|-
`corePoolSize`|	线程池维护线程的最少数量。线程池至少会保持改数量的线程存在，即使没有任务可以处理。（注意：这里说的至少是指线程达到这个数量后，即使有空闲的线程也不会释放，而不是说线程池创建好之后就会初始化这么多线程）
`maximumPoolSize`	|线程池维护线程的最大数量。线程池最多可创建的线程数，即使队列中的任务满了线程数量也不会超过maximumPoolSize
`keepAliveTime`|	线程池维护线程所允许的空闲时间。当线程池中的线程数量大于 corePoolSize时，超过corePoolSize的线程如果空闲时间超过keepAliveTime，线程将被终止
`unit`|	线程池维护线程所允许的空闲时间的单位，和keepAliveTime配合使用
`workQueue`	|线程池所使用的缓冲队列。ArrayBlockingQueue，LinkedBlockingQueue，SynchronousQueue，PriorityBlockingQueue
`handler`|线程池对拒绝任务的处理策略。AbortPolicy，CallerRunsPolicy，DiscardOldestPolicy，DiscardPolicy，自定义

>### 各类型队列说明

队列类型|	特性及使用场景
-|-
`ArrayBlockingQueue`	|有界队列，FIFO，需要指定队列大小，如果队列满了，会触发线程池的RejectedExecutionHandler逻辑
`LinkedBlockingQueue`	|无界队列，FIFO，可以无限向队列中添加任务，直到内存溢出
`SynchronousQueue`	|一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。同步队列没有任何内部容量，甚至连一个队列的容量都没有。可以简单理解为是一个容量只有1的队列。Executors.newCachedThreadPool使用的是这个队列
`PriorityBlockingQueue`	|优先级队列，线程池会优先选取优先级高的任务执行，队列中的元素必须实现Comparable接口

>### `RejectedExecutionHandler`类型
RejectedExecutionHandler|	特性及效果
-|-
`AbortPolicy`|	线程池默认的策略，如果元素添加到线程池失败，会抛出RejectedExecutionException异常
`DiscardPolicy`|	如果添加失败，则放弃，并且不会抛出任何异常
`DiscardOldestPolicy`|	如果添加到线程池失败，会将队列中最早添加的元素移除，再尝试添加，如果失败则按该策略不断重试
`CallerRunsPolicy`|	如果添加失败，那么主线程会自己调用执行器中的execute方法来执行改任务
`自定义`|	如果觉得以上几种策略都不合适，那么可以自定义符合场景的拒绝策略。需要实现RejectedExecutionHandler接口，并将自己的逻辑写在rejectedExecution方法内。

- **`AbortPolicy`**

该策略是线程池的默认策略.使用该策略时,如果线程池队列满了丢掉这个任务并且抛出`RejectedExecutionException`异常.
```java
public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
    throw new RejectedExecutionException("Task " + r.toString() +
                                            " rejected from " +
                                            e.toString());
}
```

- **`DiscardPolicy`**

这个策略是`AbortPolicy`的slient版本,如果线程池队列满了,会直接丢掉这个任务并且不会有任何异常.
```java
public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
}
```

- **`DiscardOldestPolicy`**

这个策略从字面上也很好理解,丢弃最老的.也就是说如果队列满了,会将最早进入队列的任务删掉腾出空间,再尝试加入队列.

因为队列是队尾进,队头出,所以队头元素是最老的,因此每次都是移除队头元素后再尝试入队.

```java
public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
    if (!e.isShutdown()) {
        e.getQueue().poll();
        e.execute(r);
    }
}
```

- **`CallerRunsPolicy`**

使用此策略,如果添加到线程池失败,那么主线程会自己执行该任务,不会等待线程池中的线程去执行.

```java
public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
    if (!e.isShutdown()) {
        r.run();
    }
}
```

- **自定义**

如果以上策略都不符合业务场景,那么可以自己定义一个拒绝策略,只要实现`RejectedExecutionHandler`接口,并且实现`rejectedExecution`方法就可以了.


>### 便捷的创建线程池的方式(不推荐)

线程池类型	|说明
-|-
`Executors.newFixedThreadPool`|	创建固定线程数的线程池，使用的是LinkedBlockingQueue无界队列
`Executors.newSingleThreadExecutor`|	创建只有一个线程的线程池，使用的也是LinkedBlockingQueue无界队列
`Executors.newCachedThreadPool`|	改线程池使用的是SynchronousQueue，如果其创建的线程空闲时间超过60s会自动销毁

大家在使用的时候选择合适的线程池来使用,但是在使用包含无界队列`LinkedBlockingQueue`的时候,要注意是否会导致队列不断增长,导致内存溢出.使用`SynchronousQueue`的时候要注意是否会导致线程数不断增长.





