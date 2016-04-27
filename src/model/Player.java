package model;

import model.card.Card;
import model.spot.HouseSpot;
import util.Orientation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ethan on 16/4/27.
 */
public class Player {
    private String name;
    private int position;
    private Orientation orientation;
    private double cash;
    private double deposit;
    private int ticket;
    private ArrayList<HouseSpot> houses;
    private Hashtable<Card, Integer> cards;
    private HashMap<Stock, Integer> stocks;
    private boolean isBankrupt;

    public Player(String name, double cash) {
        this.name = name;
        this.position = 1;
        this.orientation = Orientation.FORWARD;
        this.cash = cash;
        this.deposit = 0;
        this.ticket = 0;
        this.houses = new ArrayList<HouseSpot>();
        this.cards = new Hashtable<Card, Integer>();
        this.stocks = new HashMap<Stock, Integer>();
        this.isBankrupt = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getCash() {
        return cash;
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

    public ArrayList<HouseSpot> getHouses() {
        return houses;
    }

    public void addHouse(HouseSpot house) {
        houses.add(house);
    }

    public Hashtable<Card, Integer> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.put(card, cards.get(card) + 1);
    }

    public Map<Stock, Integer> getStocks() {
        return stocks;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
