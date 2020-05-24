import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(processors*2);
        System.out.println(processors);
        RunnableImp1 runa = new RunnableImp1();
        es.submit(new RunnableImp1());
        es.submit(new RunnableImp1());
        es.shutdown();
    }

}
class RunnableImp1 implements Runnable {
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"创建一个新的线程执行");
    }
}
