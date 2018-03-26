package threads;


import java.util.ArrayList;

public class ThreadDemo {

    ArrayList<Thread> threads = new ArrayList<>();

    static int i;

    private class NewThread implements Runnable {
        String name;

        @Override
        public void run() {
            increase();
            System.out.println(name + " is running, i = " + i);

        }

        synchronized void increase() {
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException ie) {}
            i ++;
            notify();
        }

        NewThread(String name) {
            this.name = name;
        }
    }

    ThreadDemo() {
        for (int i = 0; i < 30; i ++)
            threads.add(new Thread(new NewThread("Thread #" + i)));
        for (Thread t : threads)
            t.start();
    }

    public static void main(String... args) {
        new ThreadDemo();
    }
}
