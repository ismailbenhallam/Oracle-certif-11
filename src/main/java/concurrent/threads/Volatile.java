package concurrent.threads;

public class Volatile {
    private static int number; // we don't have to make it volatile (piggybacking)
    private static volatile boolean ready; // Volatile

    private static class Reader extends Thread {

        @Override
        public void run() {
            while (!ready) {
                /*
                    yield() provides a mechanism to inform the “scheduler” that the current thread is willing to relinquish its current use of processor
                    but it'd like to be scheduled back soon as possible.
                */
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    /*
        NOTE: Use volatile for "Visibility", but if u need both: "Visibility" and "Mutual Exclusion", consider using "synchronized" blocks, or Atomic variables
        In other words, "volatile" keyword, ensures that the access is atomically, not the operation !
     */
    public static void main(String[] args) throws InterruptedException {
        new Reader().start();
        Thread.sleep(4000);
        number = 42;
        ready = true;
    }

}