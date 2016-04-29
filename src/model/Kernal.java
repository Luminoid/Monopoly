package model;

import view.map.MapGenerator;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Ethan on 16/4/28.
 */
public class Kernal {
    private Map map;
    private GregorianCalendar date;
    private int playerNum;
    private List<Player> players;
    private int currentPlayer;

    private volatile static Kernal uniqueInstance;

    private Kernal(int playerNum) {
        this.playerNum = playerNum;
        date = new GregorianCalendar();
        map = MapGenerator.generate();
    }

    public static Kernal getInstance() {
        return uniqueInstance;
    }

    public static void createInstance(int playerNum) {
        if (uniqueInstance == null) {
            synchronized (Kernal.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Kernal(playerNum);
                }
            }
        }
    }

    public void startRound() {

    }

    public Map getMap() {
        return map;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(int year, int month, int day) {
        date.set(year, month - 1, day);
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
