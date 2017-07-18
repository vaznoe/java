package com.company.app;

import io.vertx.core.Future;

/*
Wiki verticle initialization phases

To make code cleaner, adopt return a future/promise object pattern to notify when each of the phases completes, and
whether successful or not.

By having each method returning a future object, the implementation of the start() method becomes a composition.

When the future of PromiseTest.task1() completes successfully, then PromiseTest.task2() is called and the steps future
completes depending of the outcome of the future returned by PromiseTest.task2.
PromiseTest.task2 is never called if PromiseTest.task1 encounters an error, in which case the steps future is a failed
state and becomes completed with the exception describing the error.
 */
class PromiseTest {
    private Future<Void> task1() {
        Future<Void> future = Future.future();
        System.out.println("task1");
        future.complete();
        return future;
    }
    private Future<Void> task2() {
        Future<Void> future = Future.future();
        System.out.println("task2");
        future.complete();
        return future;
    }
    public void run(Future<Void> startFuture){
        // promises: task1().compose(v -> task2())
        Future<Void> steps = task1().compose(v -> task2());
        steps.setHandler(ar -> {
            if(ar.succeeded()) {
                System.out.println("pass");
                startFuture.complete();
            }
            else {
                System.out.println("fail");
                startFuture.fail(ar.cause());
            }
        });
    }
}

public class App
{
    public static void main( String[] args )
    {
        PromiseTest promiseTest = new PromiseTest();
        promiseTest.run(Future.future());
    }
}
/*
output:
task1
task2
pass
 */
