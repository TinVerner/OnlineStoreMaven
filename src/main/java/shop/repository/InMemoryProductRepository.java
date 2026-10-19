package shop.repository;

import shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }
}