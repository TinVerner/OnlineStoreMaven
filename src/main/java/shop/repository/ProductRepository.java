package shop.repository;
import shop.model.Product;

public interface ProductRepository {

    Product findById(int id);

    void save(Product product);
}