package CyclicBarrier;

import CyclicBarrier.CustomCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;

public class Demo {
    private CustomCyclicBarrier cyclicBarrier = new CustomCyclicBarrier(3,
            () -> System.out.println("Barrier reached"));

    public void createAndStartThreadWithWaitTime(long timeOutMillis) {
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread " +this.getId() +  " start");
                    synchronized (this) {
                        this.wait(timeOutMillis);
                    }
                    System.out.println("Thread " + this.getId()+ " waiting time over");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
