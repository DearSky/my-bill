package com.bk.core.model.com.bk.test.concurrent;

import java.util.concurrent.*;

public class TestForCountDownLatch2 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CountDownLatch latch = new CountDownLatch(2);
        int total = 0;
        for(int i = 0; i < 2; i++) {
            try {
                Future<Integer> tmp = executorService.submit(()->{
                    System.out.println("ThreadName" + Thread.currentThread().getName() + "正在进行中");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadName" + Thread.currentThread().getName() + "结束了");
                    latch.countDown();
                    return 1;
                });

                total += tmp.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("等待2个子线程执行完毕");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程执行完毕，现在处理数据");

        Thread.sleep(3000);
        System.out.println("ThreadName："+Thread.currentThread().getName()+"计算出总额=" + total);
    }
}
