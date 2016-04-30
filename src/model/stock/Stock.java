package model.stock;

import java.util.Random;

/**
 * Created by Ethan on 16/5/1.
 */
public class Stock {
    private int id;
    private String name;
    private double price;

    public Stock(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void randomChange(){
        Random random = new Random();
        double rate = 0.9 + random.nextDouble()*0.2;
        setPrice(price*rate);
    }

    public double getFloatRate(){
        return (price - )/price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
