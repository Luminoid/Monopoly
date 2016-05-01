package model;

import action.command.*;
import model.card.CardType;
import model.spot.EstateSpot;
import model.stock.Stock;
import util.PlayerOrientation;
import util.FormatTool;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 16/4/27.
 */
public class Player {
    private static int nextId = 1;

    private String name;
    private int id;
    private int position;
    private PlayerOrientation orientation;
    private double cash;
    private double deposit;
    private int ticket;
    private ArrayList<EstateSpot> houses;
    private Hashtable<CardType, Integer> cards;
    private Hashtable<Stock, Integer> stocks;
    private boolean isBankrupt;

    public Player(String name, double cash) {
        this.name = name;
        this.id = nextId++;
        this.position = 0;
        this.orientation = PlayerOrientation.FORWARD;
        this.cash = cash;
        this.deposit = 0;
        this.ticket = 20;
        this.houses = new ArrayList<>();
        this.cards = new Hashtable<>();
        this.stocks = new Hashtable<>();
        this.isBankrupt = false;
    }

    public void move() {
        addPosition(1);
    }

    public void giveUp() {
        this.cash = 0;
        this.deposit = 0;
        this.getHouses().clear();
        this.isBankrupt = true;
        PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
        command.setCommandStr(name + "已认输");
    }

    public boolean pay(double fee) {
        if (cash >= fee) {
            cash -= fee;
            return true;
        } else {
            return false;
        }
    }

    public boolean payWithDepositPriority(double fee) {
        if (deposit >= fee) {
            deposit -= fee;
            return true;
        } else {
            deposit = 0;
            fee -= deposit;
            if (cash >= fee) {
                cash -= fee;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean charge(double fee) {
        if (cash >= fee) {
            cash -= fee;
            return true;
        } else {
            fee -= cash;
            cash = 0;

            if (deposit >= fee) {
                deposit -= fee;
                return true;
            } else {
                fee -= deposit;
                deposit = 0;

                ArrayList<EstateSpot> housesForSell = houses.stream().sorted((a, b) -> {
                    if (a.getPurchasingPrice() - b.getPurchasingPrice() > 0)
                        return -1;
                    else
                        return 1;
                }).collect(Collectors.toCollection(ArrayList<EstateSpot>::new));

                for (EstateSpot estateSpot : housesForSell) {
                    if (estateSpot.getPurchasingPrice() >= fee) {
                        cash += estateSpot.getPurchasingPrice();
                        cash -= fee;
                        estateSpot.sell();
                        houses.remove(estateSpot);
                        return true;
                    } else {
                        fee -= estateSpot.getPurchasingPrice();
                        estateSpot.sell();
                        houses.remove(estateSpot);
                    }
                    SellEstateCommand command = (SellEstateCommand) SimpleCommandFactory.
                            createCommand(CommandType.SELL_ESTATE_COMMAND);
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void addPosition(int distance) {
        int mapSize = Kernel.getInstance().getMap().getSize();
        position += FormatTool.distanceWithOrientation(this, distance);
        if (position >= mapSize) {
            position -= mapSize;
        } else if (position < 0) {
            position += mapSize;
        }
    }

    public int checkPosition(int distance) {
        int mapSize = Kernel.getInstance().getMap().getSize();
        int ret = position;
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
        return cash;
    }

    public void addCash(double cash) {
        this.cash += cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public ArrayList<EstateSpot> getHouses() {
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
