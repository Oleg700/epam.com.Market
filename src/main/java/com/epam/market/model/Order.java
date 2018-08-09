package com.epam.market.model;

 public class Order {
    private int orderId;
    private int productId;
    private int customerId;
    private Customer customer;
    private Product product;
    private String orderDate;
    private String orderTime;

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Order  setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public Order  setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public int getProductId() {
        return productId;
    }

    public Order setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Order setCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(String orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public Order setOrderTime(String orderTime) {
        this.orderTime = orderTime;
        return this;
    }
}
