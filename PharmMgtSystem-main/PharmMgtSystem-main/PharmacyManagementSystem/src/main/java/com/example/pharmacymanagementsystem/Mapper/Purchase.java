package com.example.pharmacymanagementsystem.Mapper;

public class Purchase {
    private final int id;
    private final Drug drug;
    private final int quantity;
    private final int price;
    private final Buyer buyer;
    private final String date;

    public Purchase(int id, Drug drug, int quantity, int price, Buyer buyer, String date) {
        this.id = id;
        this.drug = drug;
        this.quantity = quantity;
        this.price = price;
        this.buyer = buyer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Drug getDrug() {
        return drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", drug=" + drug +
                ", quantity=" + quantity +
                ", price=" + price +
                ", buyer=" + buyer +
                ", date='" + date + '\'' +
                '}';
    }
}
