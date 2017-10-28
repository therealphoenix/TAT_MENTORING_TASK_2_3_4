package com.klindziuk.flowershop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel_Klindziuk on 22/10/2017.
 */
public class Bouquet implements Serializable {
    private float price;
    private Map<Flower, Float> flowers;

    public Bouquet(Map<Integer, Integer> orderMap, List<Flower> fullList) {
       this.flowers = createFlowerMap(orderMap,fullList);
       this.price = calculatePrice(getFlowers());
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Map<Flower, Float> getFlowers() {
        return flowers;
    }

    public void setFlowers(Map<Flower, Float> flowers) {
        this.flowers = flowers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bouquet bouquet = (Bouquet) o;

        if (Float.compare(bouquet.price, price) != 0) return false;
        return flowers != null ? flowers.equals(bouquet.flowers) : bouquet.flowers == null;

    }

    @Override
    public int hashCode() {
        int result = (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (flowers != null ? flowers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  "\n\t" + "Bouquet { " + "\n" +
                "price = " + price + "\n" +
                "flowers : " + flowers + "\n\t"+
                '}';
    }

    private Map<Flower, Float> createFlowerMap(Map<Integer, Integer> orderMap, List<Flower> fullList) {
        Map<Flower, Float> result = new HashMap<>();
        for (Integer flowerId : orderMap.keySet()) {
            for (Flower flower : fullList) {
                if (flower.getId() == flowerId && flower.isAvailable()) {
                    result.put(flower, flower.getPrice() * orderMap.get(flowerId));
                }
            }
        }
        return result;
    }

    private float calculatePrice(Map<Flower, Float> flowerMap) {
        float result = 0;
        for (Float price : flowerMap.values()) {
            result = result + price;
        }
        return result;
    }
}
