package com.hwz;

/**
 * Created by ZhangZaipeng on 2017/7/15.
 */
public class Tmain implements Runnable{

    private String threadName ;

    public Tmain() {}

    public Tmain(String threadName) {
        this.threadName = threadName;
    }

    public static void main(String[] args) throws InterruptedException {


        Tmain tm1 = new Tmain("tm1");
        Tmain tm2 = new Tmain("tm2");
        Tmain tm3 = new Tmain("tm3");

        Thread t1 = new Thread(tm1);
        Thread t2 = new Thread(tm2);
        Thread t3 = new Thread(tm3);

        // 设置线程优先级
        // t1.setPriority(Thread.MAX_PRIORITY);
        // t2.setPriority(Thread.NORM_PRIORITY);
        // t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();


        // System.out.println("t 加入到当前线程！");
        // t.join();
    }

    public void run() {
        /*try {
            Thread.sleep(1111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for (int i = 1; i <= 10; i++) {
            /*try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            if (threadName.equals("tm2")){
                Thread.yield();
            }

            System.out.println("Tmain =>"+ threadName +" run===> " + i);
        }
    }
}
