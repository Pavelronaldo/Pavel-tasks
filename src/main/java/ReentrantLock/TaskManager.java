package ReentrantLock;

public class TaskManager implements Runnable {
    ReentrantLock lockCustom;

    public TaskManager(ReentrantLock lockCustom) {
        this.lockCustom = lockCustom;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " is Waiting to acquire lock");

        lockCustom.lock();
        System.out.println();
        System.out.println(Thread.currentThread().getName()
                + " has called lock(), lockHoldCount=1 ");


        lockCustom.lock();
        System.out.println(Thread.currentThread().getName()
                + " has called lock(), lockHoldCount=2 ");


        System.out.println(Thread.currentThread().getName()
                + " is about to call unlock(), lockHoldCount will become 1 ");
        lockCustom.unlock();


        System.out.println(Thread.currentThread().getName()
                + " is about to call unlock(), lockHoldCount will become 0 ");
        lockCustom.unlock();

    }
}

