package shop.model;

public class Payment {

    private final int id;
    private final Order order;
    private final double amount;
    private boolean paid;

    public Payment(int id, Order order) {
        this.id = id;
        this.order = order;
        this.amount = order.getTotalPrice();
        this.paid = false;
    }

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void markAsPaid() {
        this.paid = true;
    }
}