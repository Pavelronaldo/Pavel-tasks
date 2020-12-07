package ReentrantLock;

public class ReentrantLock implements CustomLock {

    int lockHoldCount;

    long ThreadId;

    public ReentrantLock() {
        this.lockHoldCount = 0;
    }

    public synchronized void lock() {
        if (lockHoldCount == 0) {
            lockHoldCount++;
            ThreadId = Thread.currentThread().getId();
        }
        //If current thread already holds lock then lock hold
        // count is increased by 1.
        else if (lockHoldCount > 0
                && ThreadId == Thread.currentThread().getId()) {
            lockHoldCount++;
        }
        //If the lock is held by another thread then the current
        // thread waits for another thread to release lock.
        else {
            try {
                wait();
                lockHoldCount++;
                ThreadId = Thread.currentThread().getId();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void unlock() {
        if (lockHoldCount == 0)
            throw new IllegalMonitorStateException();

        lockHoldCount--; //decrement lock hold count by 1

        //if lockHoldCount is 0, lock is released, and
        //one waiting thread is notified.
        if (lockHoldCount == 0)
            notify();

    }

    public boolean tryLock() {
        if (lockHoldCount == 0) {
            lock();
            return true;
        }
        //If lock is held by another thread then method return false.
        else
            return false;
    }

}
