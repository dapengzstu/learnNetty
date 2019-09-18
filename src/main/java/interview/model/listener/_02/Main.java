package interview.model.listener._02;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService callBackExecutor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程执行完成");
                return "SUCCESS";
            }
        });
        FutureListennerWrapper<String> futureWrapper  = new FutureListennerWrapper<String>(future);

        futureWrapper.addListener(new Runnable() {
            public void run() {
                System.out.println("回调方法调用");
            }
        },callBackExecutor);
        System.out.println("主线程执行完成");
        executor.shutdown();
    }
}
