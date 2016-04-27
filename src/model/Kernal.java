package model;

import java.util.GregorianCalendar;

/**
 * Created by Ethan on 16/4/27.
 */
public class Kernal {
    private Map map;
    private GregorianCalendar date;
    private Player[] players;
    private int currentPlayer;

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(int year, int month, int day) {
        date.set(year, month - 1, day);
    }


}
