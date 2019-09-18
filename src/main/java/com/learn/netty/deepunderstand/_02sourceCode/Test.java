package com.learn.netty.deepunderstand._02sourceCode;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;


import java.util.concurrent.Callable;

public class Test {
    public static void main(String[] args) {
        //Netty的Future 和 java的Future不同，功能更强大

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };


    }

    class AfterChange implements GenericFutureListener{
        @Override
        public void operationComplete(Future future) throws Exception {
            System.out.println("operationComplete");
        }
    }
}
