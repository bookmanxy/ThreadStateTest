/**
 * @author watermelon on 2019/4/19 9:54
 * @description
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        System.out.println("t 的状态： " + t.getState());

        t.start();
        System.out.println("t 的状态： " + t.getState());
    }

    /**
     *  打印结果:
     *
     *  t 的状态： NEW
     *  t 的状态： RUNNABLE
     *  hello
     */
}

