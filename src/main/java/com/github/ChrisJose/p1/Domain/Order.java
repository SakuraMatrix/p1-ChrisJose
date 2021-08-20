package com.github.ChrisJose.p1.Domain;

public class Order {

    private int orderId;
    private int customerId;
    private String comment;
    private String salesAssociate;
    private String orderDate;
    private String products;
    private float totalAmt;

    public Order(int orderId, int customerId, String comment, String salesAssociate, String orderDate, String products, float totalAmt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.comment = comment;
        this.salesAssociate = salesAssociate;
        this.orderDate = orderDate;
        this.totalAmt = totalAmt;
        this.products = products;
        }

    public Order() {};

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSalesAssociate() {
        return salesAssociate;
    }

    public void setSalesAssociate(String salesAssociate) {
        this.salesAssociate = salesAssociate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public float getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(float totalAmt) {
        this.totalAmt = totalAmt;
    }

    }


