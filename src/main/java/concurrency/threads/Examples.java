package concurrency.threads;

public class Examples {


    public static void main(String[] args) {
        // Using interruption to communicate
//        interrupt();

        waitNotify();

//        join();
    }

    private static String name() {
        return java.lang.Thread.currentThread().getName() + ": ";
    }

    private static void interrupt() {
        Runnable r = () -> {
            var thread = java.lang.Thread.currentThread();
            while (!thread.isInterrupted()) {
                try {
                    System.out.println(name() + "Nothing to do, I'm bored!");
                    java.lang.Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // The thread is interrupted, and then we can execute our action !!
                    System.out.println(name() + "Oh! Here we go!!");
                    return;
                }
            }
        };

        java.lang.Thread t = new java.lang.Thread(r);
        t.start();

        try {
            java.lang.Thread.sleep(5000);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void waitNotify() {
        Object monitor = new Object(); // We can use any object, even the thread executing this block ( Thread#currentThread() )
        Runnable r = () -> {
            System.out.println(name() + "I'll be waiting for u to wake me up");
            synchronized (monitor) {
                try {
                    // Self sleeping
                    monitor.wait();
                    System.out.println(name() + "Good morning :)");
                } catch (InterruptedException ignored) {
                    System.err.println(name() + "I'm trying to sleep!!!!");
                }
            }
        };

        java.lang.Thread t = new java.lang.Thread(r);
        t.start();

        try {
            java.lang.Thread.sleep(4000);
            synchronized (monitor) {
                System.out.println("\n" + name() + "I'm gonna wake him up..");
                monitor.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void join() {
        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(name() + i);
                    java.lang.Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name() + "Done!");
        };

        java.lang.Thread t = new java.lang.Thread(r);
        t.start();

        System.out.println(name() + "Why should I wait for him to finish ?");
        try {
            // Causes the current Thread to wait for the thread "t" until it's finished
            t.join();
            System.out.println(name() + "Ohh, Finally!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
