package interview.concurrent;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws Exception {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };

        int result = 0;
        //第一种
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> submit = executorService.submit(callable);
        System.out.println(submit.get());
        //第二种
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.get());

        //第三种
        Future<Integer> submit1 = executorService.submit(callable);
        submit1.get();
    }
}
