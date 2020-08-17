package com.mine.redis;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * RedisDelayedQueue
 *
 * @Author mineChen
 * @Date 2020/8/12 10:49
 */
@Service
public class RedisDelayedQueue {

    /**
     * 任务回调监听
     *
     * @param <T>
     */
    public abstract static class TaskEventListener<T> {
        /**
         * 执行方法
         *
         * @param t
         */
        public abstract void invoke(T t);
    }

    @Autowired
    RedissonClient redissonClient;

    /**
     * 添加队列
     *
     * @param t        DTO传输类
     * @param delay    时间数量
     * @param timeUnit 时间单位
     * @param <T>      泛型
     */
    public <T> void addQueue(T t, long delay, TimeUnit timeUnit) {
        RBlockingQueue<T> blockingFairQueue = redissonClient.getBlockingQueue(t.getClass().getName());
        RDelayedQueue<T> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        delayedQueue.offer(t, delay, timeUnit);
        delayedQueue.destroy();
    }

    /**
     * 获取队列
     *
     * @param zClass            DTO泛型
     * @param taskEventListener 任务回调监听
     * @param <T>               泛型
     * @return
     */
    public <T> void getQueue(Class zClass, TaskEventListener taskEventListener) {
        RBlockingQueue<T> blockingFairQueue = redissonClient.getBlockingQueue(zClass.getName());
        //由于此线程需要常驻，可以新建线程，不用交给线程池管理
        ((Runnable) () -> {
            while (true) {
                try {
                    T t = blockingFairQueue.take();
                    taskEventListener.invoke(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

}
