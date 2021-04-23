package concurrent.atomics;

import _shared_entities.Product;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;

public class Examples {
    public static void main(String[] args) {
        // Atomic variables are thread-safe, no need to use "synchronized" blocks or...
        var integer = new AtomicInteger(5);
        System.out.println(integer.incrementAndGet());

        var longArray = new AtomicLongArray(new long[]{1, 9, 5});
        System.out.println(longArray.get(1));

        var atomicReference = new AtomicReference<Product>();
        atomicReference.set(new Product("My awesome product", 0.9f));
        System.out.println(atomicReference.get());
    }
}
