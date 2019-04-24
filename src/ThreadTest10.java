/**
 * 原文：https://blog.csdn.net/yuanzhanli/article/details/79003352
 *
 * 3、出现未处理的error或者exception导致异常结束的时候释放
 *
 *  为模拟代码执行久一点，采用sleep的方式
 *
 */
public class ThreadTest10 {
    public static void main(String[] args) throws Exception{

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行t1");
                DemoSync2.showme("t1线程");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("执行t2");
                DemoSync2.showme("t2线程");

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

     执行t2
     线程： t1线程，拿到了锁
     Exception in thread "Thread-0" java.lang.RuntimeException: 模拟运行时异常
     at DemoSync2.showme(ThreadTest10.java:82)
     at ThreadTest10$1.run(ThreadTest10.java:16)
     at java.lang.Thread.run(Thread.java:745)
     线程： t2线程，拿到了锁
     线程: t2线程 结束

    总结：t1代码执行中 出现RuntimeException 会释放监视器锁，即让出The Owner
     *
     */
}

class DemoSync2{
    synchronized public static void  showme(String name){

        try {
            //为了模拟执行久一点
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程： " + name + "，拿到了锁");

        if("t1线程".equals(name)){
            throw new RuntimeException("模拟运行时异常");
        }else{
            System.out.println("线程: " + name + " 结束");
        }

    }
}

