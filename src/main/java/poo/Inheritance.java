package poo;

public class Inheritance {

    interface I {
    }

    static class A implements I {
    }

    static class B extends A {
    }

    class C extends B {
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a = (B) (I) b;
//        I i = (C) a;
    }

}