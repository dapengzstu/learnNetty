package interview.model.listener._02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public interface FutureListener<T> extends Future<T> {
    void addListener(Runnable listener, Executor executor) throws ExecutionException, InterruptedException;
}
