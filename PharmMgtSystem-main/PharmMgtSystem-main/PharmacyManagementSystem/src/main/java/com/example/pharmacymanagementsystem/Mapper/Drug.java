package com.example.pharmacymanagementsystem.Mapper;

public class Drug {
    private final int id;
    private final String name;
    private final String genericName;
    private final String dosage;
    private final String strength;
    private final Supplier supplier;
    private final Stock stock;

    public Drug(
            int id,
            String name,
            String genericName,
            String dosage,
            String strength,
            Supplier supplier,
            Stock stock) {
        this.id = id;
        this.name = name;
        this.genericName = genericName;
        this.dosage = dosage;
        this.strength = strength;
        this.supplier = supplier;
        this.stock = stock;
    }

    public Drug(
            String name,
            String genericName,
            String dosage,
            String strength,
            int supplierId,
            int quantity,
            int price) {
        this.id = -1;
        this.name = name;
        this.genericName = genericName;
        this.dosage = dosage;
        this.strength = strength;
        this.supplier = new Supplier(supplierId, "", "", "", "");
        this.stock = new Stock(quantity, price);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getStrength() {
        return strength;
    }

    public Supplier getSupplier() {
        return supplier;
    }


    public Stock getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genericName='" + genericName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", strength='" + strength + '\'' +
                ", supplier=" + supplier + '\'' +
                ", stock=" + stock +
                '}';
    }
}
