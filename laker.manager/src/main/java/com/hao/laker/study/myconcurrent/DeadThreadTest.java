package com.hao.laker.study.myconcurrent;

/**
 * Created by haojiahong on 17/3/28.
 */
public class DeadThreadTest {

    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public static void main(String[] args) {
        new Thread(new DeadThreadTask(obj1, obj2)).start();
        new Thread(new DeadThreadTask(obj2, obj1)).start();

    }

    static class DeadThreadTask extends Thread {

        private Object obj1;
        private Object obj2;

        DeadThreadTask(Object obj1, Object obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        @Override
        public void run() {
            super.run();
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + "获得锁1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2) {
                    System.out.println(Thread.currentThread().getName() + "获得锁2");
                }
            }
        }
    }


}
