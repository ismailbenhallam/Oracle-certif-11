package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class TestClass {
    static char ch;
    static float f;
    static boolean bool;

    private class A {
        static final int y = 4;
    }

    public static void main(String[] args) {
        try {
            System.exit(0);
        } finally {
            System.out.println("finally is always executed!");
        }
        var o = new int[0];

        var mod = 4.5f % (byte) 5.4;
//        var ss = 4 && 5;

//        var rlock = new ReentrantLock();
//        var f1 = rlock.lock();
//        System.out.println(f1);
//        var f2 = rlock.lock();
//        System.out.println(f2);

        try (var s = new FileInputStream("")) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


        ArrayList[] a = null;
        Collection[] b = a;

    }

}