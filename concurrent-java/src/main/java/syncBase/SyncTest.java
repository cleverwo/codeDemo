package syncBase;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/12 16:16
 * @Description:
 * 简单的synchronize关键字应用
 * 多线程访问共享资源 同步访问 count值
 */
public class SyncTest implements Runnable {
    private int count = 10;

    // 加synchronized 时同步count；
    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+ " count "+ count);
    }

    public static void main(String[] args){
        SyncTest t = new SyncTest();
        for(int i=0 ;i<5;i++){
            new Thread(t,"THREAD"+i).start();
        }
    }
}
