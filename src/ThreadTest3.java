/**
 * @author watermelon on 2019/4/19 9:54
 * @description
 */
public class ThreadTest3 {
    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(3000);
                } catch (InterruptedException e)
                {

                }
            }
        });

        t.start();

        for(int i = 0 ; i < 5 ; i ++){
            try
            {
                System.out.println("t 的状态： " + t.getState());
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
     *  进入t1线程
     *  t1 的状态： RUNNABLE

     *  t1 的状态： TIMED_WAITING

     *  t1 的状态： TIMED_WAITING

     *  t1 的状态： TERMINATED

     *  t1 的状态： TERMINATED
     */
}
