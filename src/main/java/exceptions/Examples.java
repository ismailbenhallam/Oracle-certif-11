package exceptions;

class SomeThrowable extends Throwable {
}

class MyThrowable extends SomeThrowable {
}

public class Examples {
    public static void main(String[] args) throws SomeThrowable {
        try {
            m1();
        } catch (SomeThrowable ex) {
            throw ex;
        } finally {
            System.out.println("Done");
        }

    }

    private static void m1() throws MyThrowable {
        throw new MyThrowable();
    }
}
