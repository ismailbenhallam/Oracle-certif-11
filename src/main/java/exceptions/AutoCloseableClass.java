package exceptions;

public class AutoCloseableClass implements AutoCloseable {

    public static void main(String[] args) {
        try (var a = new AutoCloseableClass()) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        When you use try-with-resources clause, this method is called automatically,
        even if an exception occur in the block
     */
    @Override
    public void close() throws Exception {
        // Clean up resources..
    }
}
