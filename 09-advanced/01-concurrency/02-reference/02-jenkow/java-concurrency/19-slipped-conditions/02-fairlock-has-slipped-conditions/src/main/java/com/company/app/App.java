package com.company.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
http://tutorials.jenkov.com/java-concurrency/slipped-conditions.html

A More Realistic Example of slipped conditions

Problem: nested monitor lockout occurs
1, in lock(), the inner queueObject.wait() is holding the synchronization lock on "this" object,
   and it output:
Thread_A_1 wait
is waiting for queueObject.notify()
2, for unlock(), no thread can enter synchronized unlock() because "this" object is locked by lock()

Solution for nested monitor lockout occurs
1, move synchronized(queueObject) block outside the synchronized(this) block.
2, but we run into the slipped conditions problem below.

Problem: slipped conditions
for the mustWait condition
1, synchronized(this) block set the condition by "mustWait = isLocked || waitingThreads.get(0) != queueObject"
2, in synchronized(queueObject) block check if the thread_A is to wait or not. Assume the lock was unlocked
   (mustWait=false) by other thread, so the thread_A exists the synchronized(queueObject) block right away.
3, the last synchronized(this) block is only executed if mustWait=false.

Image two threads call lock() at the same time when the lock is unlocked. First thread_1 checks
the isLocked condition is false. Then thread_2 will do the same thing.

output:
[Thread_2 isLocked=false, Thread_1 isLocked=false]

 */
public class App
{
    static class Lock{
        boolean isLocked = false;
        Set<String> isLockedList = new HashSet<String>();
        Thread lockingThread = null;
        List<Object> waitingThreads = new ArrayList<Object>();
        public void lock() throws InterruptedException {
            Object queueObject = new Object();

            synchronized (this){
                waitingThreads.add(queueObject);
            }

            boolean mustWait = true;
            while(mustWait){

                synchronized (this){
                    isLockedList.add(Thread.currentThread().getName() + " isLocked=" + isLocked);
                    mustWait = isLocked || waitingThreads.get(0) != queueObject;
                }
                synchronized (queueObject){
                    if(mustWait){
                        try {
                            queueObject.wait();
                        } catch (InterruptedException e) {
                            waitingThreads.remove(queueObject);
                            throw e;
                        }
                    }
                }
            }

            Thread.sleep(100);

            synchronized (this){
                if(!mustWait){
                    waitingThreads.remove(queueObject);
                    isLocked = true;
                    lockingThread = Thread.currentThread();
                }
            }
        }
        public synchronized void unlock(){
            if(this.lockingThread != Thread.currentThread()){
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            if(waitingThreads.size() > 0){
                Object queueObject = waitingThreads.get(0);
                synchronized (queueObject){
                    System.out.println(" " + Thread.currentThread().getName() + " notify");
                    queueObject.notify();
                }
            }
        }
    }
    public static void main( String[] args )
    {
        final Lock lock = new Lock();
        for(int i = 1; i <= 2; i++ ){
            Thread thread = new Thread("Thread_" + i){
                public void run(){
                    try {
                        lock.lock();
                    } catch (InterruptedException e) { }
                }
            };
            thread.setDaemon(true);
            thread.start();
        }

        System.out.println(lock.isLockedList);
    }
}
