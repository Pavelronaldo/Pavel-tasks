package Phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class CarDemo {
    private static final CustomPhaser START = new CustomPhaser(5);
    private static final int trackLength = 500000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            new Thread(new Car(i, (int) (Math.random() * 100 + 50))).start();
            Thread.sleep(100);
        }

        while (START.getPhase() > 5) //Проверяем, собрались ли все автомобили
            Thread.sleep(100);                  //у стартовой прямой. Если нет, ждем 100ms

        Thread.sleep(100);
        System.out.println("На старт!");
//        START.arriveAndDeregister();
        Thread.sleep(100);
        System.out.println("Внимание!");
//        START.arriveAndDeregister();
        Thread.sleep(100);
        System.out.println("Марш!");
        START.arriveAndDeregister();
    }

    public static class Car implements Runnable {
        private int carNumber;
        private int carSpeed;

        public Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Автомобиль №%d подъехал к стартовой прямой.\n", carNumber);
                START.arriveAndDeregister();
                START.arriveAndAwaitAdvance();
                Thread.sleep(trackLength / carSpeed);
                System.out.printf("Автомобиль №%d финишировал!\n", carNumber);
            } catch (InterruptedException e) {
            }
        }
    }

}