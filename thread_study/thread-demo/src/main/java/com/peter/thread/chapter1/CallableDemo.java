package com.peter.thread.chapter1;

import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<String> callable = new CallableDemo();
        Future<String> future = executorService.submit(callable);
        System.out.println(future.get());
    }
    @Override
    public String call() throws Exception {
        return "string" + 1;
    }
}
