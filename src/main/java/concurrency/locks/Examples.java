package concurrency.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Examples {
    private List<Product> list = new ArrayList<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock rl = rwl.readLock();
    private Lock wl = rwl.writeLock();

    public Product get(int id) {
        rl.lock();
        try {
            return list.stream().filter(p -> p.id == id).findFirst().orElse(null);
        } finally {
            rl.unlock();
        }
    }

    public void add(Product p) {
        wl.lock();
        try {
            list.add(p);
        } finally {
            wl.unlock();
        }
    }

    public static class Product {
        private int id;
        //...
    }
}
