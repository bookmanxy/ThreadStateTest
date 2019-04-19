# ThreadStateTest

#### 介绍
测试线程各个状态的转换（基于jdk1.8）

#### 自制状态转换图
![线程的生命周期](https://wx3.sinaimg.cn/mw1024/005xB1vLly1g283fw5ftfj30u40lpq4h.jpg) 

#### 测试清单
##### **0：源码注解**
* (文件：ThreadDoc.md )

##### **1：new完是什么状态？**
* 示例文件：ThreadTest1.java
* 结果：NEW 

##### **2：调用start()是什么状态？**
* 示例文件：ThreadTest2.java
* 示例结果：RUNNABLE) 

##### **3：调用sleep()是什么状态？**
* 示例文件：ThreadTest3.java
* 示例结果：从 RUNNABLE 进入 TIMED_WAITING

##### **4：调用 wait() 和 notify() 是什么状态？**
* 示例文件：ThreadTest4.java
* 结果 调用 wait() 状态从 RUNNABLE 进入 WAITING
* 调用 notify() 状态从 WAITING 进入 RUNNABLE

##### **5：调用 wait(long milli) 是什么状态？**
* 示例文件：ThreadTest5.java
* 结果 状态从 RUNNABLE 进入 TIMED_WAITING

##### **6：当发生I/O的时候 是什么状态？**
* 示例文件：ThreadTest6.java
* 结果 状态还是RUNNABLE

##### **7：当发生I/O的时候 当前占有的锁会不会释放？**
* 示例文件：ThreadTest7.java
* 结果 当线程发生i/o时并不会释放锁