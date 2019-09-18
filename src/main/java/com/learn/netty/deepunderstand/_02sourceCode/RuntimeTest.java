package com.learn.netty.deepunderstand._02sourceCode;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

import java.util.concurrent.*;

public class RuntimeTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.    获得系统的核心×2
        int max = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(max);



        //2.    创建一个线程池
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> submit = executorService.submit(callable);
        System.out.println(submit.get());

        //3.    一个在本线程中运行的
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        runnable.run();
        Executor executor = new  DirectExecutor();
        executor.execute(runnable);

    }
}
class DirectExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }
}
