package com.example.pharmacymanagementsystem.Mapper;

public class Stock {
    private final int quantity;
    private final int price;

    public Stock(int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
