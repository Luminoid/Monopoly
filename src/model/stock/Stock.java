package model.stock;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Kernel;

import java.util.Random;

/**
 * Created by Ethan on 16/5/1.
 */
public class Stock {
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private ObservableList<Double> priceRecord;

    public Stock(int id, String name, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        priceRecord = FXCollections.observableArrayList();
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

        setPrice(price.get() * (1 + rate));
    }

    public IntegerProperty getHoldingNumProperty(){
        return Kernel.getInstance().getCurrentPlayer().getStocks().get(this);
    }

    public double getFloatRate() {
        if (priceRecord.size() >= 2) {
            return (price.get() - priceRecord.get(priceRecord.size() - 2)) / priceRecord.get(priceRecord.size() - 2);
        } else {
            return 0;
        }
    }

    public StringBinding getFloatRateBinding(){
        if (priceRecord.size() >= 2) {
            return (price.subtract(priceRecord.get(priceRecord.size() - 2))).divide(priceRecord.get(priceRecord.size() - 2)).
                    multiply(100).asString("%.1f");
        } else {
            return new SimpleDoubleProperty(0).asString();
        }
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
        priceRecord.add(price);
    }

    public ObservableList<Double> getPriceRecord() {
        return priceRecord;
    }
}
