import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProducerConsumer2 {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
        Semaphore semaphore = new Semaphore(0);

        Thread[] threads = {
                new Producer(list, semaphore),
                new Producer(list, semaphore),
                new Consumer(list, semaphore),
                new Consumer(list, semaphore)
        };

        for(Thread thread: threads)
            thread.start();
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished" + list.size());
    }
}

class Consumer extends Thread {

    List<Integer> list;
    Semaphore semaphore;

    public Consumer(List list, Semaphore semaphore){
        this.list = list;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list) {
                Random random = new Random();
                Integer fetch = list.remove(random.nextInt(list.size()));
                System.out.println("Removed: " + fetch);
            }
        }
    }
}

class Producer extends Thread {
    List<Integer> list;
    Semaphore semaphore;

    public Producer(List<Integer> list, Semaphore semaphore){
        this.list = list;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            Random random = new Random();
            int num = random.nextInt(1000);
            System.out.println("Added:" + num);
            synchronized (list){
                list.add(num);
            }
            semaphore.release();
        }
    }
}

