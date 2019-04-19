/**
 *
 *  wait()后，线程会释放掉它所占有的“锁标志”，从而使线程所在对象中的其他shnchronized数据可被别的线程使用。
 *  wait()h和notify()因为会对对象的“锁标志”进行操作，所以他们必需在Synchronized函数或者 synchronized block 中进行调用。如果在non-synchronized 函数或 non-synchronized
 *  block 中进行调用，虽然能编译通过，但在运行时会发生IllegalMonitorStateException的异常。
 *  参考：https://www.e-learn.cn/content/qita/2023261
 */
public class ThreadTest5 {
    public static void main(String[] args) throws Exception{
        Object object = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    try {
                        object.wait(2500);
                        System.out.println();
                        System.out.println("自己醒后 t 的状态是： " + Thread.currentThread().getState());
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();

        for(int i = 0 ; i < 5 ; i ++){
            try
            {
                System.out.println("当前打印第" + i + "条，t 的状态： " + t.getState());
                System.out.println();

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
     *  当前打印第0条，t 的状态： RUNNABLE
     *
     *  当前打印第1条，t 的状态： TIMED_WAITING
     *
     *  当前打印第2条，t 的状态： TIMED_WAITING
     *
     *  自己醒后 t 的状态是： RUNNABLE
     *
     *  当前打印第3条，t 的状态： TERMINATED
     *
     *  当前打印第4条，t 的状态： TERMINATED
     */
}

