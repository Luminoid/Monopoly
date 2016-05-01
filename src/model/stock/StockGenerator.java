package model.stock;

/**
 * Created by Ethan on 16/5/1.
 */
public class StockGenerator {
    public static StockMarket generate() {
        StockMarket stockMarket = new StockMarket();
        stockMarket.addStock(new Stock(600000, "浦发银行", 17.83));
        stockMarket.addStock(new Stock(600004, "白云机场", 12.02));
        stockMarket.addStock(new Stock(600005, "武钢股份", 3.31));
        stockMarket.addStock(new Stock(600006, "东风汽车", 6.35));
        stockMarket.addStock(new Stock(600007, "中国国贸", 15.58));
        stockMarket.addStock(new Stock(600008, "首创股份", 8.12));
        stockMarket.addStock(new Stock(600009, "上海机场", 27.21));
        stockMarket.addStock(new Stock(600010, "包钢股份", 3.04));
        stockMarket.addStock(new Stock(600011, "华能国际", 7.48));
        stockMarket.addStock(new Stock(600012, "皖通高速", 13.80));
        stockMarket.addStock(new Stock(600015, "华夏银行", 10.17));
        stockMarket.addStock(new Stock(600016, "民生银行", 9.30));
        stockMarket.addStock(new Stock(600017, "日照港", 4.38));
        stockMarket.addStock(new Stock(600018, "上港集团", 4.91));
        stockMarket.addStock(new Stock(600019, "宝钢股份", 5.57));

        return stockMarket;
    }
}
