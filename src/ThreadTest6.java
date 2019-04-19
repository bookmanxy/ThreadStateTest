import java.util.Scanner;

/**
 * 采用Scanner scan = new Scanner(System.in); 来模拟i/o
 *
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

