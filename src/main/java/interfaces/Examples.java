package interfaces;

interface I1 {

    private int getSuperNumber() {
        return 9;
    }

    default void defaultMethod() {
        System.out.println(getSuperNumber());
    }

    static void staticMethod() {
        System.out.println(48);
    }

}

interface I2 {
    default void defaultMethod() {
        System.out.println("I2!!");
    }
}

public class Examples implements I1, I2 {

    @Override
    public void defaultMethod() {
        I2.super.defaultMethod();
    }

    public static void main(String[] args) {
        new Examples().defaultMethod();
    }

}
