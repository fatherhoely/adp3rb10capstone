package za.ac.cput.domain;

import java.util.*;

public class Order {

    private int orderId;
    private int customerId;
    private Date orderDate;
    private String status;
    private double totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    private List<OrderItem> items;

    // Private constructor that takes a Builder instance.
    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.customerId = builder.customerId;
        this.orderDate = new Date(); // Automatically set the current date.
        this.status = builder.status;
        this.totalAmount = 0.0;
        this.shippingAddress = builder.shippingAddress;
        this.paymentMethod = builder.paymentMethod;
        this.items = builder.items != null ? builder.items : new ArrayList<>();
    }

    public String getStatus() {
        return null;
    }

    public Collection<Object> getItems() {
        return null;
    }

    public int getOrderId() {
        return 0;
    }

    public int getCustomerId() {
        return 0;
    }

    public String getShippingAddress() {
        return null;
    }

    public String getPaymentMethod() {
        return null;
    }


    // Builder static nested class
    public static class Builder {
        private int orderId;
        private int customerId;
        private String shippingAddress;
        private String paymentMethod;
        private String status = "Pending";  // Default status.
        private List<OrderItem> items = new ArrayList<>();

        public Builder orderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder shippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder items(List<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(OrderItem item) {
            this.items.add(item);
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    // Calculate the total amount of the order.
    public double calculateTotal() {
        totalAmount = items.stream().mapToDouble(OrderItem::totalPrice).sum();
        return totalAmount;
    }

    // Update the status of the order.
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Add an item to the order and recalculate the total.
    public void addItem(OrderItem item) {
        items.add(item);
        calculateTotal();
    }

    // Remove an item from the order and recalculate the total.
    public void removeItem(OrderItem item) {
        items.remove(item);
        calculateTotal();
    }

    // Retrieve the order details in a formatted string.
    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(orderId).append("\n");
        details.append("Customer ID: ").append(customerId).append("\n");
        details.append("Order Date: ").append(orderDate).append("\n");
        details.append("Status: ").append(status).append("\n");
        details.append("Total Amount: ").append(String.format("%.2f", totalAmount)).append("\n");
        details.append("Shipping Address: ").append(shippingAddress).append("\n");
        details.append("Payment Method: ").append(paymentMethod).append("\n");
        details.append("Items:\n");
        for (OrderItem item : items) {
            details.append(" - Item ID: ").append(item.getItemId())
                    .append(", Quantity: ").append(item.getQuantity())
                    .append(", Price: ").append(String.format("%.2f", item.getPrice())).append("\n");
        }
        return details.toString();
    }
}