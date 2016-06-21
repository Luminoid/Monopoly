package model;

import action.command.*;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.card.CardType;
import model.spot.EstateSpot;
import model.stock.Stock;
import util.FormatTool;
import util.NumberUtil;
import util.PlayerOrientation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 16/4/27.
 */
public class Player {
    private static int nextId = 1;

    private StringProperty name;
    private int id;
    private IntegerProperty position;
    private PlayerOrientation orientation;
    private DoubleProperty cash;
    private DoubleProperty deposit;
    private IntegerProperty ticket;
    private ObservableList<EstateSpot> houses;
    private Hashtable<CardType, Integer> cards;
    private Hashtable<Stock, Integer> stocks;
    private boolean isBankrupt;

    public Player() {
        this(null, 0.0);
    }

    public Player(String name, double cash) {
        this.name = new SimpleStringProperty(name);
        this.id = nextId++;
        this.position = new SimpleIntegerProperty(0);
        this.orientation = PlayerOrientation.FORWARD;
        this.cash = new SimpleDoubleProperty(cash);
        this.deposit = new SimpleDoubleProperty(0);
        this.ticket = new SimpleIntegerProperty(20);
        this.houses = FXCollections.observableArrayList();
        this.cards = new Hashtable<>();
        this.stocks = new Hashtable<>();
        this.isBankrupt = false;
    }

    public void move() {
        addPosition(1);
    }

    public void giveUp() {
        this.cash.set(0);
        this.deposit.set(0);
        this.getHouses().clear();
        this.isBankrupt = true;
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr(name + "已认输");
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringBinding cashProperty() {
        return cash.asString("%.1f");
    }

    public StringBinding depositProperty() {
        return deposit.asString("%.1f");
    }

    public StringBinding ticketProperty() {
        return ticket.asString();
    }

    public DoubleBinding estateProperty() {
        return NumberUtil.sum(houses, EstateSpot::getPriceBinding);
    }

    public DoubleBinding assestProperty() {
        return cash.add(deposit).add(estateProperty());
    }

    public boolean pay(double fee) {
        if (cash.get() >= fee) {
            cash.set(cash.get() - fee);
            return true;
        } else {
            return false;
        }
    }

    public boolean payWithDepositPriority(double fee) {
        if (deposit.get() >= fee) {
            deposit.set(deposit.get() - fee);
            return true;
        } else {
            deposit.set(0);
            fee -= deposit.get();
            if (cash.get() >= fee) {
                cash.set(cash.get() - fee);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean charge(double fee) {
        if (cash.get() >= fee) {
            cash.set(cash.get() - fee);
            return true;
        } else {
            fee -= cash.get();
            cash.set(0);

            if (deposit.get() >= fee) {
                deposit.set(deposit.get() - fee);
                return true;
            } else {
                fee -= deposit.get();
                deposit.set(0);

                ArrayList<EstateSpot> housesForSell = houses.stream().sorted((a, b) -> {
                    if (a.getPurchasingPrice() - b.getPurchasingPrice() > 0)
                        return -1;
                    else
                        return 1;
                }).collect(Collectors.toCollection(ArrayList<EstateSpot>::new));

                for (EstateSpot estateSpot : housesForSell) {
                    if (estateSpot.getPurchasingPrice() >= fee) {
                        cash.set(cash.get() + estateSpot.getPurchasingPrice());
                        cash.set(cash.get() - fee);
                        estateSpot.sell();
                        houses.remove(estateSpot);
                        return true;
                    } else {
                        fee -= estateSpot.getPurchasingPrice();
                        estateSpot.sell();
                        houses.remove(estateSpot);
                    }
                    PromptCommand command = (PromptCommand) SimpleCommandFactory.
                            createCommand(CommandType.PROMPT_COMMAND);
                    command.setCommandStr("因为现金不足，" + name + "出售了" + estateSpot.getLocName() + "的地产");
                }
                if (fee > 0) {
                    BankruptCommand command = (BankruptCommand) SimpleCommandFactory.
                            createCommand(CommandType.BANKRUPT_COMMAND);
                    command.setPlayer(this);
                }
                return false;
            }
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position.get();
    }

    public void addPosition(int distance) {
        int mapSize = Kernel.getInstance().getMap().getSize();
        position.set(position.get() + FormatTool.distanceWithOrientation(this, distance));
        if (position.get() >= mapSize) {
            position.set(position.get() - mapSize);
        } else if (position.get() < 0) {
            position.set(position.get() + mapSize);
        }
    }

    public int checkPosition(int distance) {
        int mapSize = Kernel.getInstance().getMap().getSize();
        int ret = position.get();
        distance *= (orientation == PlayerOrientation.FORWARD ? 1 : -1);
        ret += distance;
        if (ret >= mapSize) {
            ret -= mapSize;
        } else if (ret < 0) {
            ret += mapSize;
        }
        return ret;
    }

    public PlayerOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation() {
        orientation = (orientation == PlayerOrientation.FORWARD ? PlayerOrientation.BACKWARD : PlayerOrientation.FORWARD);
    }

    public double getCash() {
        return cash.get();
    }

    public void setCash(double cash) {
        this.cash.set(cash);
    }

    public void addCash(double cash) {
        this.cash.set(this.cash.get() + cash);
    }

    public double getDeposit() {
        return deposit.get();
    }

    public void setDeposit(double deposit) {
        this.deposit.set(deposit);
    }

    public int getTicket() {
        return ticket.get();
    }

    public void setTicket(int ticket) {
        this.ticket.set(ticket);
    }

    public ObservableList<EstateSpot> getHouses() {
        return houses;
    }

    public void addHouse(EstateSpot house) {
        houses.add(house);
    }

    public Hashtable<CardType, Integer> getCards() {
        return cards;
    }

    public void addCard(CardType cardType) {
        cards.put(cardType, cards.get(cardType) + 1);
    }

    public void useCard(CardType cardType) {
        cards.put(cardType, cards.get(cardType) - 1);
    }

    public Hashtable<Stock, Integer> getStocks() {
        return stocks;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
