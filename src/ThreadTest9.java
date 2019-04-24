import java.util.Scanner;

/**
 * 原文：https://blog.csdn.net/yuanzhanli/article/details/79003352
 *
 * 1、当前线程的同步方法、代码块执行结束的时候释放
 * 2、当前线程在同步方法、同步代码块中遇到break 、 return 终于该代码块或者方法的时候释放。
 *
 * 为模拟代码执行久一点，采用sleep的方式
 *
 */
public class ThreadTest9 {
    public static void main(String[] args) throws Exception{

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行t1");
                DemoSync1.showme("t1线程");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("执行t2");
                DemoSync1.showme("t2线程");

            }
        });

        t.start();


        try
        {
            //晚一点执行，保证 t线程先持有锁
            Thread.sleep(10);
        } catch (InterruptedException e)
        {

        }

        t2.start();

        try{
            //为了保持主线程
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {

        }
    }

    /**
     *  打印结果:
     *
     执行t1
     线程： t1线程，拿到了锁

     执行t2
     线程: t1线程 退出
     线程： t2线程，拿到了锁
     线程: t2线程 退出

    总结：t1代码执行完后释放监视器锁，即让出The Owner
     *
     */
}

class DemoSync1{
    synchronized public static void  showme(String name){

        System.out.println("线程： " + name + "，拿到了锁");

        try {
            //为了模拟执行久一点
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程: " + name + " 退出");
    }
}

