

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoaderMain {

    public static void main(String[] args) throws InterruptedException {
        List<Object> objects = new ArrayList<>();
        int numObjects = 10000;
        for (int i = 0; i < numObjects; i++) {
            objects.add(null);
        }
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread t = new Thread(new Task(objects));
            t.start();
        }
        Thread.sleep(100000000L);
    }

    private static final class Thing {
        int[] a;

        Thing(int size) {
            a = new int[size];
        }
    }

    private static final class Task implements Runnable {

        private final Random r = new Random(1234);
        private final List<Object> objects;

        Task(List<Object> objects) {
            this.objects = objects;
        }

        @Override
        public void run() {
            while (true) {
                int max = 3;
                double v = Math.min(Math.abs(r.nextGaussian()), max);
                int index = (int) Math.round(v / max * (objects.size() - 1));
                objects.set(index, new Thing(r.nextInt(10000)));
            }
        }
    }

}
