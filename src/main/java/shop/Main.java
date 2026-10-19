package shop;

import shop.model.Customer;
import shop.model.Order;
import shop.model.Product;
import shop.repository.InMemoryProductRepository;
import shop.repository.ProductRepository;
import shop.service.OrderService;

public class Main {

    public static void main(String[] args) {

        ProductRepository productRepository =
                new InMemoryProductRepository();

        Product laptop = new Product(
                1,
                "Ноутбук",
                2500.0,
                2
        );

        productRepository.save(laptop);

        Customer customer = new Customer(
                1,
                "Анна"
        );

        OrderService orderService =
                new OrderService(productRepository);

        try {
            Order order = orderService.createOrder(
                    1,
                    customer,
                    1,
                    1
            );

            System.out.println("Заказ успешно оформлен.");
            System.out.println("Покупатель: " + order.getCustomer().getName());
            System.out.println("Товар: " + order.getProduct().getName());
            System.out.println("Количество: " + order.getQuantity());
            System.out.println("Стоимость: " + order.getTotalPrice());
            System.out.println(
                    "Осталось на складе: " +
                            order.getProduct().getStockQuantity()
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Ошибка оформления заказа: " + e.getMessage()
            );
        }
    }
}