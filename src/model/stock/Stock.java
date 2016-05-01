package model.stock;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ethan on 16/5/1.
 */
public class Stock {
    private int id;
    private String name;
    private double price;
    private ArrayList<Double> priceRecord;

    public Stock(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        priceRecord = new ArrayList<>();
    }

    public void change() {
        int opp = new Random().nextInt(100);
        Random random = new Random();
        double rate;
        if (opp >= 0 && opp <= 50) {
            rate = random.nextDouble() * 0.02;
        } else if (opp > 50 && opp <= 75) {
            rate = random.nextDouble() * 0.05;
        } else {
            rate = random.nextDouble() * 0.1;
        }

        int trend = new Random().nextInt(100);
        if (getFloatRate() > 0) {
            if (trend >= 0 && trend <= 25) {
                rate *= -1;
            }
        } else {
            if (trend >= 0 && trend <= 75) {
                rate *= -1;
            }
        }

        setPrice(price * (1 + rate));
    }

    public double getFloatRate() {
        if (priceRecord.size()>=2){
            return (price - priceRecord.get(priceRecord.size() - 2)) / priceRecord.get(priceRecord.size() - 2);
        } else {
            return 0;
        }
    }

    public void setPrice(double price) {
        this.price = price;
        priceRecord.add(price);
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
}
