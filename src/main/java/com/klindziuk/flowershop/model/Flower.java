package com.klindziuk.flowershop.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Flower implements Serializable {
    private static final long serialVersionUID = 3496552795406068119L;
    private int id;
    private String name;
    private float price;
    private String country;
    private boolean isAvailable;

    public Flower() {
    }

    public Flower(String name, float price, String country) {
        this.name = name;
        this.price = price;
        this.country = country;
    }

    public Flower(int id, String name, float price, String country, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flower flower = (Flower) o;

        if (id != flower.id) return false;
        if (Float.compare(flower.price, price) != 0) return false;
        if (isAvailable != flower.isAvailable) return false;
        if (name != null ? !name.equals(flower.name) : flower.name != null) return false;
        return country != null ? country.equals(flower.country) : flower.country == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (isAvailable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", country='" + country + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
