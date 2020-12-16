package com.javaops.webapp;

public class DeadLock {
    public static void main(String[] args) {
        final String lock1 = "lock1";
        final String lock2 = "lock2";
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Thread waiting for " + lock1);
            synchronized (lock1) {
                System.out.println("Thread holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread waiting for " + lock2);
                synchronized (lock2) {
                    System.out.println("Thread holding " + lock2);
                }
            }
        }).start();
    }
}
