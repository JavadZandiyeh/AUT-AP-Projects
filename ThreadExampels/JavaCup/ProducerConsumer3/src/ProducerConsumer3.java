import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumer3 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        Thread[] threads = {
                new producer(queue),
                new producer(queue),
                new consumer(queue),
                new consumer(queue)
        };

        for(Thread thread: threads)
            thread.start();
        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished: " + queue.size());
    }
}

class producer extends Thread {
    ArrayBlockingQueue<Integer> queue;

    public producer(ArrayBlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            Random random = new Random();
            int num = random.nextInt(1000);
            try {
                queue.put(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Added: " + num);
        }
    }
}

class consumer extends Thread{
    ArrayBlockingQueue<Integer> queue;

    public consumer(ArrayBlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            try {
                int fetch = queue.take();
                System.out.println("Fetched:" + fetch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
