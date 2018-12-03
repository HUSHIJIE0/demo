package com.example.demo.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: Michael
 * @Date: Created in 10:26 2018/12/3
 * @Desciption:
 * BlockingQueue的核心方法：
 *         放入数据：
 *         　　offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,
 *         　　　　则返回true,否则返回false.（本方法不阻塞当前执行方法的线程）
 *         　　offer(E o, long timeout, TimeUnit unit),可以设定等待的时间，如果在指定的时间内，还不能往队列中
 *         　　　　加入BlockingQueue，则返回失败。
 *         　　put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断
 *         　　　　直到BlockingQueue里面有空间再继续.
 *         获取数据：
 *         　　poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,
 *         　　　　取不到时返回null;
 *         　　poll(long timeout, TimeUnit unit)：从BlockingQueue取出一个队首的对象，如果在指定时间内，
 *         　　　　队列一旦有数据可取，则立即返回队列中的数据。否则知道时间超时还没有数据可取，返回失败。
 *         　　take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到
 *         　　　　BlockingQueue有新的数据被加入;
 *         　　drainTo():一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数），
 *         　　　　通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。
 */

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);

        // 执行10s
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
    }
}
