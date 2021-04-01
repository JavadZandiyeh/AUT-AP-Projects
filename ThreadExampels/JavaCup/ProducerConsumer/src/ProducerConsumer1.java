import java.util.ArrayList;
import java.util.Random;

public class ProducerConsumer1 {
    public static void main(String[] args){

        ArrayList<Integer> list = new ArrayList<Integer>();

        Thread T1 = new Thread(new consumer(list));
        Thread T2 = new Thread(new consumer(list));

        Thread T3 = new Thread(new producer(list));
        Thread T4 = new Thread(new producer(list));
        Thread T5 = new Thread(new producer(list));

        Thread[] threads = {T1, T2, T3, T4, T5};


        for(Thread thread: threads)
            thread.start();

        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished" + list.size());
    }
}

class producer implements Runnable{

    ArrayList<Integer> integers;
    public producer(ArrayList<Integer> list){
        integers = list;
    }

    @Override
    public void run() {
        Random random = new Random();
        for(int i=0; i<10; i++) {
            synchronized (integers){
                int num = random.nextInt(1000);
                integers.add(num);
                System.out.println("Added: " + num);
                integers.notify();
            }

            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class consumer implements Runnable{

    ArrayList<Integer> integers;
    public consumer(ArrayList<Integer> list){
        integers = list;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            synchronized (integers){
                while (integers.size() == 0){
                    try {
                        integers.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Random random = new Random();
                Integer fetch = integers.remove(random.nextInt(integers.size()));
                System.out.println("Removed: " + fetch);

            }
        }
    }
}
