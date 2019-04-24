import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock.lock()
 * 让线程t 拿到锁后，进入i/o
 * 启动线程t2
 * 观察线程 t2是否能够获得锁，从而来判断线程在i/o时能否释放锁
 */
public class ThreadTest7 {
    //可重入锁，互斥临界区
    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) throws Exception {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入线程 t");
                lock.lock();
                System.out.println("线程 t 拿到了锁");

                System.out.println("当前线程是否占有锁？" + lock.isHeldByCurrentThread());

                //让线程进入I/O
                System.out.println("请输入数据：");
                Scanner scan = new Scanner(System.in);
                String read = scan.nextLine();
                System.out.println("您输入的数据为：" + read);

                System.out.println("退出线程 t");

                lock.unlock();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("进入线程 t2");

                lock.lock();
                System.out.println("线程 t2 拿到了锁");

                System.out.println("当前线程 t2 是否占有锁？" + lock.isHeldByCurrentThread());
            }
        });

        t.start();


        try {
            //晚1秒执行，保证 t线程先持有锁
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        t2.start();

        for (int i = 0; i < 5; i++) {
            try {
                //为了保持主线程
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}


