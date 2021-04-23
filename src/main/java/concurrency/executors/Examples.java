package concurrency.executors;

import java.util.concurrent.*;

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
            public String call() throws Exception {
                if (Math.random() < 0.5)
                    throw new Exception("This is my message");
                return "hellooo";
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("ouups");
            System.err.println(e.getMessage());
            e.getCause().printStackTrace();
        }


        executor.shutdown();
    }
}
