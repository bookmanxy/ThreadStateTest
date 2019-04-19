import java.util.Scanner;

/**
 *
 *  wait()后，线程会释放掉它所占有的“锁标志”，从而使线程所在对象中的其他shnchronized数据可被别的线程使用。
 *  wait()h和notify()因为会对对象的“锁标志”进行操作，所以他们必需在Synchronized函数或者 synchronized block 中进行调用。如果在non-synchronized 函数或 non-synchronized
 *  block 中进行调用，虽然能编译通过，但在运行时会发生IllegalMonitorStateException的异常。
 *  参考：https://www.e-learn.cn/content/qita/2023261
 */
public class ThreadTest6 {
    public static void main(String[] args) throws Exception{
        Object object = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入线程 t");

                //让线程进入I/O
                System.out.println("请输入数据：");
                Scanner scan = new Scanner(System.in);
                String read = scan.nextLine();
                System.out.println("您输入的数据为："+read);

                System.out.println("退出线程 t");
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
     *  进入线程 t
     *  请输入数据：
     *
     *  当前打印第1条，t 的状态： RUNNABLE
     *
     *  当前打印第2条，t 的状态： RUNNABLE
     *
     *  前打印第3条，t 的状态： RUNNABLE
     *
     *  当前打印第4条，t 的状态： RUNNABLE
     */
}

