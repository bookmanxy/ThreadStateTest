import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  使用synchronized
 *  让线程t 拿到锁后，进入i/o
 *  启动线程t2
 *  观察线程 t2是否能够获得锁，从而来判断线程在i/o时能否释放锁
 *
 */
public class ThreadTest8 {
    public static void main(String[] args) throws Exception{

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入线程 t1");

                DemoBlockLock.showme("t1线程");

                System.out.println("t1 结束");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("进入线程 t2");

                DemoBlockLock.showme("t2线程");

                System.out.println("t2 结束");
            }
        });

        t.start();


        try
        {
            //晚1秒执行，保证 t线程先持有锁
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {

        }

        t2.start();

        for(int i = 0 ; i < 5 ; i ++){
            try{
                System.out.println("t1线程状态：" + t.getState());
                System.out.println("t2线程状态：" + t2.getState());

                //为了保持主线程
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {

            }
        }


    }

    /**
     *  打印结果:
     *
     进入线程 t1
     线程： t1线程，拿到了锁
     请输入数据：
     t1线程状态：RUNNABLE
     t2线程状态：RUNNABLE

     进入线程 t2
     t1线程状态：RUNNABLE
     t2线程状态：BLOCKED
     t1线程状态：RUNNABLE
     t2线程状态：BLOCKED
     t1线程状态：RUNNABLE
     t2线程状态：BLOCKED
     t1线程状态：RUNNABLE
     t2线程状态：BLOCKED

     *
     *  进入线程 t2
     */
}

class DemoBlockLock{
    synchronized public static void  showme(String name){

        System.out.println("线程： " + name + "，拿到了锁");

        //让线程进入I/O
        System.out.println("请输入数据：");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("您输入的数据为："+read);

        System.out.println("线程: " + name + " 退出");
    }
}

