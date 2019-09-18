package interview.model.listener._02;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FutureListennerWrapper<T> extends AbstractFuture<T> implements FutureListener<T> {

    private  static final  Executor defaultWrapperExecutor = Executors.newCachedThreadPool();

    //执行的线程会返回结果到这里
    private final Future<T> delegate;

    private final Executor adapterExecutor;

    private final AtomicBoolean hasListeners = new AtomicBoolean(false);

    public FutureListennerWrapper(Future<T> delegate){
        this(delegate,defaultWrapperExecutor);
    }
    public FutureListennerWrapper(Future<T> delegate,Executor adapterExecutor){
        this.delegate = delegate;
        this.adapterExecutor = adapterExecutor;
    }



    @Override
    protected Future<T> delegate() {
        return this.delegate;
    }

    @Override
    public void addListener(Runnable listener, Executor executor) throws ExecutionException, InterruptedException {
        //listener和执行listener的线程池
        final Runnable listenerInner = listener;
        final ExecutorService executorServiceInner = (ExecutorService) executor;

        if (hasListeners.compareAndSet(false,true)){
            T t = delegate.get();
            executorServiceInner.execute(listenerInner);
            executorServiceInner.shutdown();
            return;
            /*//判断被代理线程是否执行结束
            if (this.isDone()){
                executorServiceInner.execute(listenerInner);
                executorServiceInner.shutdown();
                return;
            }
            adapterExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("get");
                        delegate.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    executorServiceInner.execute(listenerInner);
                    executorServiceInner.shutdown();
                }
            });
            ((ExecutorService)adapterExecutor).shutdown();*/
        }
    }
}
