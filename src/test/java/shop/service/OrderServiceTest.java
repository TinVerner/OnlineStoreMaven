package shop.service;

import org.junit.jupiter.api.Test;
import shop.model.Customer;
import shop.model.Order;
import shop.model.Product;
import shop.repository.InMemoryProductRepository;
import shop.repository.ProductRepository;
import shop.service.OrderService;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void createOrderSuccessfully() {

        ProductRepository repository =
                new InMemoryProductRepository();

        Product product =
                new Product(1, "Ноутбук", 2500.0, 5);

        repository.save(product);

        Customer customer =
                new Customer(1, "Анна");

        OrderService orderService =
                new OrderService(repository);

        Order order = orderService.createOrder(
                1,
                customer,
                1,
                2
        );

        assertNotNull(order);
        assertEquals(2, order.getQuantity());
        assertEquals(5000.0, order.getTotalPrice());
        assertEquals(3, product.getStockQuantity());
    }

    @Test
    void cannotCreateOrderWithInvalidQuantity() {

        ProductRepository repository =
                new InMemoryProductRepository();

        Product product =
                new Product(1, "Ноутбук", 2500.0, 5);

        repository.save(product);

        Customer customer =
                new Customer(1, "Анна");

        OrderService orderService =
                new OrderService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(
                        1,
                        customer,
                        1,
                        0
                )
        );
    }

    @Test
    void cannotCreateOrderWhenStockIsInsufficient() {

        ProductRepository repository =
                new InMemoryProductRepository();

        Product product =
                new Product(1, "Ноутбук", 2500.0, 5);

        repository.save(product);

        Customer customer =
                new Customer(1, "Анна");

        OrderService orderService =
                new OrderService(repository);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(
                        1,
                        customer,
                        1,
                        10
                )
        );

        assertEquals(
                "Недостаточное кол-во товара на складе.",
                exception.getMessage()
        );

        assertEquals(5, product.getStockQuantity());
    }
}

