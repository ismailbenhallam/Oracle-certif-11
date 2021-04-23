package poo;

public class InitClass {
    /*
        In Java, the order for initialization statements is as follows:
         - static variables and static initializers in order
         - instance variables and instance initializers in order
         - constructors
     */
    public static void main(String args[]) {
        InitClass obj = new InitClass(5);
        System.out.print(new Object());
    }

    int m;
    static int i1 = 5;
    static int i2;
    int j = 100;
    int x;

    public InitClass(int m) {
        System.out.println(i1 + "  " + i2 + "   " + x + "  " + j + "  " + m);
    }

    {
        j = 30;
        i2 = 40;
    }  // Instance Initializer

    static {
        i1++;
    }      // Static Initializer
}