package com.bk.core.model.com.bk.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestForSemaphore {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        //信号量限制了最多有5个线程同时执行
        Semaphore semaphore = new Semaphore(5);

        for(int i = 0; i < 10; i++) {
            try {
                executorService.execute(()->{
                    try {
                        semaphore.acquire();
                        System.out.println("ThreadName:"+ Thread.currentThread().getName() + "占用了一个信号");
                        Thread.sleep(3000);
                        System.out.println("ThreadName:"+ Thread.currentThread().getName() + "释放了一个信号");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
