package annotations.utils;

import _shared_entities.Product;

import java.util.List;

public class ProductManager {

    public List find() {
        return List.of(new Product("P1", 50), new Product("P2", 30));
    }
}
