package com.javaops.webapp;

public class DeadLock {
    public static void main(String[] args) {
        final String lock0 = "lock0";
        final String lock1 = "lock1";
        deadLock(lock0, lock1);
        deadLock(lock1, lock0);
    }

    private static void deadLock(Object lock0, Object lock1) {
        new Thread(() -> {
            print("Waiting", lock0);
            synchronized (lock0) {
                print("Holding", lock0);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print("Waiting", lock1);
                synchronized (lock1) {
                    print("Holding", lock1);
                }
            }
        }).start();
    }

    private static void print(String name, Object lock) {
        System.out.println(Thread.currentThread().getName() + " " + name + " " + lock);
    }
}