package CyclicBarrier;

import CyclicBarrier.Demo;

public class Main {

    public static void main(String... args) {
        Demo demo = new Demo();
        demo.createAndStartThreadWithWaitTime(5000);
        demo.createAndStartThreadWithWaitTime(10000);
        demo.createAndStartThreadWithWaitTime(3000);
    }
}
