/**
 *
 *  yield()后，线程是否会释放锁
 *
 *  理论上，yield意味着放手，放弃，投降。一个调用yield()方法的线程告诉虚拟机它乐意让其他线程占用自己的位置。
 */
public class ThreadTest12 {
    public static void main(String[] args) throws Exception{
        Object object = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========t1线程进来了=====");
                synchronized (object){
                    System.out.println("====t1线程拿到锁了==== " );
                    System.out.println("==yield前的状态是：" + Thread.currentThread().getState());
                    Thread.yield();
                    System.out.println("==yield后的状态是：" + Thread.currentThread().getState());

                    try {
                        //保证t1线程先执行
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========t2线程进来了====");
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

        for(int i = 0 ; i < 5 ; i ++){
            try
            {
                System.out.println();
                System.out.println("t1的状态" + t.getState());
                System.out.println("t2的状态" + t2.getState());


                //为了减少打印次数，所以t1每打印一次睡1秒
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {

            }
        }


    }

    /**
     *  打印结果:
     *
     ========t1线程进来了=====
     ====t1线程拿到锁了====
     ==yield前的状态是：RUNNABLE
     ==yield后的状态是：RUNNABLE

     t1的状态TIMED_WAITING
     t2的状态RUNNABLE
     ========t2线程进来了====

     t1的状态TIMED_WAITING
     t2的状态BLOCKED

     t1的状态TIMED_WAITING
     t2的状态BLOCKED
     ====t2线程拿到锁了====

     t1的状态TERMINATED
     t2的状态TERMINATED

     t1的状态TERMINATED
     t2的状态TERMINATED

     总结：t1被yield() 不释放锁。

     Yield是一个静态的原生(native)方法
     Yield告诉当前正在执行的线程把运行机会交给线程池中拥有相同优先级的线程。
     Yield不能保证使得当前正在运行的线程迅速转换到可运行的状态
     它仅能使一个线程从运行状态转到可运行状态，而不是等待或阻塞状态

     参考：http://www.importnew.com/14958.html
     */
}

