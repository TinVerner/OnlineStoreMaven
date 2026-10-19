package shop.model;

public class Order {

    private final int id;
    private final Customer customer;
    private final Product product;
    private final int quantity;
    private final double totalPrice;

    public Order(int id, Customer customer, Product product, int quantity) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}