package concurrent.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Examples {

    public static final Runnable TASK = () -> {
        System.out.println(Thread.currentThread().getName() + " says Hi!");
    };

    public static void main(String[] args) throws InterruptedException {
//        singleThreadExecutor();

//        fixedThreadPool();

//        scheduledThreadPool();

        submitCallable();
    }

    private static void singleThreadExecutor() {
        var executor = Executors.newSingleThreadExecutor();
        executor.execute(TASK);
        executor.execute(TASK);
        executor.execute(TASK);
        executor.execute(TASK);

        executor.shutdown();
    }

    private static void fixedThreadPool() {
        var executor = Executors.newFixedThreadPool(2);
        executor.execute(TASK);
        executor.execute(TASK);
        executor.execute(TASK);
        executor.execute(TASK);

        executor.shutdown();
    }

    private static void scheduledThreadPool() {
        var executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(TASK, 2, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(TASK, 0, 1, TimeUnit.SECONDS);

//        executor.shutdown();
    }

    private static void submitCallable() {
        var executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws MyException {
                if (Math.random() < 0.5)
                    throw new MyException("This is my message");
                return "Done";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (ExecutionException e) {
            /*
                Exception thrown when attempting to retrieve the result of a task that aborted by throwing an exception.
                This exception can be inspected using the getCause() method.
             */
            System.err.println(e.getMessage());
            System.out.println(e.getCause() instanceof MyException);
        }


        executor.shutdown();
    }

    private static class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }
}
