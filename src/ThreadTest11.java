/**
 *
 *  wait()后，线程是否会释放锁
 */
public class ThreadTest11 {
    public static void main(String[] args) throws Exception{
        Object object = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========t1线程进来了=====");
                synchronized (object){
                    try {
                        System.out.println("====t1线程拿到锁了==== " );
                        //模拟执行了一段时间
                        Thread.sleep(5000);

                        System.out.println("====t1线程被wait了==== " );
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========t2线程进来了=====");
                synchronized (object){
                    System.out.println("====t2线程拿到锁了==== " );
                }
            }
        });

        t.start();

        try {
            //保证t1线程先执行
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        try
        {
            System.out.println();
            //为了保持主线程
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {

        }

    }

    /**
     *  打印结果:
     *
     ========t1线程进来了=====
     ====t1线程拿到锁了====

     ========t2线程进来了=====
     ====t1线程被wait了====
     ====t2线程拿到锁了====

     总结：t1被wait() 监视器锁释放
     */
}

