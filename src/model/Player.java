package model;

import model.card.Card;
import model.spot.EstateSpot;
import util.Orientation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ethan on 16/4/27.
 */
public class Player {
    private static int nextId = 1;

    private String name;
    private int id;
    private int position;
    private Orientation orientation;
    private double cash;
    private double deposit;
    private int ticket;
    private ArrayList<EstateSpot> houses;
    private Hashtable<Card, Integer> cards;
    private HashMap<Stock, Integer> stocks;
    private boolean isBankrupt;

    public Player(String name, double cash) {
        this.name = name;
        this.id = nextId++;
        this.position = 1;
        this.orientation = Orientation.FORWARD;
        this.cash = cash;
        this.deposit = 0;
        this.ticket = 0;
        this.houses = new ArrayList<EstateSpot>();
        this.cards = new Hashtable<Card, Integer>();
        this.stocks = new HashMap<Stock, Integer>();
        this.isBankrupt = false;
    }

    public void giveUp() {
        this.isBankrupt = true;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void addPosition(int distance) {
        int mapSize = Kernal.getInstance().getMap().getSize();
        distance *= (orientation == Orientation.FORWARD ? 1 : -1);
        position += distance;
        if (position >= mapSize) {
            position -= mapSize;
        } else if (position < 0) {
            position += mapSize;
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
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

    public ArrayList<EstateSpot> getHouses() {
        return houses;
    }

    public void addHouse(EstateSpot house) {
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
