package shop.service;

import shop.model.Customer;
import shop.model.Order;
import shop.model.Product;
import shop.repository.ProductRepository;

public class OrderService {

    private final ProductRepository productRepository;



    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Order createOrder(
            int orderId,
            Customer customer,
            int productId,
            int quantity
    ) {
        Product product = productRepository.findById(productId);

        if(product == null)
            throw new IllegalArgumentException("Товар не найден");

        if(quantity <= 0)
            throw new IllegalArgumentException("Кол-во запрашиваемого товара должно быть больше нуля.");

        if(product.getStockQuantity() < quantity)
            throw new IllegalArgumentException("Недостаточное кол-во товара на складе.");

        product.setStockQuantity(product.getStockQuantity() - quantity);

        return new Order(orderId, customer, product, quantity);
    }
}