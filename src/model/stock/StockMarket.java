package model.stock;

import action.command.CommandType;
import action.command.ErrorCommand;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.Player;

import java.util.ArrayList;

/**
 * Created by Ethan on 16/5/1.
 */
public class StockMarket {
    private ArrayList<Stock> stocks;

    public StockMarket() {
        stocks = new ArrayList<>();
    }

    public void open() {
        stocks.forEach(Stock::change);
    }

    public boolean buyStock(Player player, int stockId, int amount) {
        Stock stock = stocks.stream().filter(e -> e.getId() == stockId).findFirst().get();
        ErrorCommand errorCommand = (ErrorCommand) SimpleCommandFactory.createCommand(CommandType.ERROR_COMMAND);
        if (stock == null) {
            errorCommand.setCommandStr("您要买的股票不存在！");
            return false;
        }
        double cost = stock.getPrice() * amount;
        int preAmount = amount;
        if (player.payWithDepositPriority(cost)) {
            if (player.getStocks().containsKey(stock)) {
                amount += player.getStocks().get(stock);
            }
            player.getStocks().put(stock, amount);
            PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
            command.setCommandStr("买进 " + stock.getName() + " " + preAmount + "股");
            return true;
        } else {
            errorCommand.setCommandStr("您的资金不足！");
            return false;
        }
    }

    public boolean sellStock(Player player, int stockId, int amount) {
        Stock stock = stocks.stream().filter(e -> e.getId() == stockId).findFirst().get();
        ErrorCommand errorCommand = (ErrorCommand) SimpleCommandFactory.createCommand(CommandType.ERROR_COMMAND);
        if (player.getStocks().containsKey(stock)) {
            if (player.getStocks().get(stock) > amount) {
                int postAmount = player.getStocks().get(stock) - amount;
                player.getStocks().put(stock, postAmount);
                PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
                command.setCommandStr("卖出 " + stock.getName() + " " + amount + "股");
                return true;
            } else {
                errorCommand.setCommandStr("卖出数多于持有数！");
                return false;
            }
        } else {
            errorCommand.setCommandStr("您手上没有这支股票！");
            return false;
        }
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }
}
