package shop.service;

import shop.model.Customer;
import shop.model.Order;
import shop.model.Product;
import shop.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Order createOrder(
            int orderId,
            Customer customer,
            int productId,
            int quantity
    ) {
        logger.info(
                "Order request received: orderId={}, productId={}, quantity={}",
                orderId,
                productId,
                quantity
        );

        Product product = productRepository.findById(productId);

        if (product == null) {
            logger.error(
                    "Order processing failed: product with id={} was not found",
                    productId
            );

            throw new IllegalArgumentException("Товар не найден");
        }

        if (quantity <= 0) {
            logger.warn(
                    "Invalid order quantity: productId={}, quantity={}",
                    productId,
                    quantity
            );
            
            throw new IllegalArgumentException("Кол-во запрашиваемого товара должно быть больше нуля.");
        }

        if (product.getStockQuantity() < quantity) {
            logger.warn(
                    "Not enough stock: productId={}, requested={}, available={}",
                    productId,
                    quantity,
                    product.getStockQuantity()
            );

            throw new IllegalArgumentException("Недостаточное кол-во товара на складе.");
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);

        logger.info(
                "Order created successfully: orderId={}, productId={}, quantity={}",
                orderId,
                productId,
                quantity
        );

        return new Order(orderId, customer, product, quantity);
    }
}