package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

/*
http://vertx.io/docs/vertx-core/java/#blocking_code

When several workers are created with the same name, they will share the same pool. The worker pool is destroyed when all the worker executors using it are closed.

When an executor is created in a Verticle, Vert.x will close it automatically for you when the Verticle is undeployed.
 */
public class App
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx
            .vertx();

        WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool");
        executor.executeBlocking(future -> {
            System.out.println(Thread.currentThread().getName() + ": vertx.executeBlocking() future handler");

            // Call some blocking API that takes a significant amount of time to return
            String result = blockingCode();
            future.complete(result);
        }, ar -> {
            System.out.println(Thread.currentThread().getName() + ": vertx.executeBlocking() AsyncResult=" + ar.result());
        });
        executor.close();

        System.out.println(Thread.currentThread().getName() + ": thread END");

        vertx.close();
    }

    private static String blockingCode() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // need to comment out e.printStackTrace();
//            e.printStackTrace();
        }

        return "executed blocking code";
    }
}
/*
output:
main: thread END
my-worker-pool-0: vertx.executeBlocking() future handler
vert.x-eventloop-thread-1: vertx.executeBlocking() AsyncResult=executed blocking code
 */
