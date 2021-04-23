package exceptions;

public class Device implements AutoCloseable {
    String header = null;

    public void close() {
        System.out.println("Closing device");
        header = null;
        throw new RuntimeException("rte");
    }

    public static void main(String[] args) throws Exception {
        tryFinally();
//        tryWithResources();
    }

    private static void tryFinally() throws Exception {
        Device d = null;
        try {
            d = new Device();
            throw new Exception("test");
        } finally {
            d.close();
        }
    }

    /*
        When using try-with-resources, it's the exception thrown in the close method that's suppressed.
        The original exception is thrown.
     */
    private static void tryWithResources() throws Exception {
        try (Device d = new Device()) {
            throw new Exception("test");
        }
    }
}