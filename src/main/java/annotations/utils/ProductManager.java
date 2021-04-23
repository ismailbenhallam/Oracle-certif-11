package annotations.utils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductManager {

    public List find() {
        return List.of(new Product("P1", 50), new Product("P2", 30));
    }
}
