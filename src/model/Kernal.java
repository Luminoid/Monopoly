package model;

import action.command.CommandType;
import action.command.FindWinnerCommand;
import action.command.SimpleCommamdFactory;
import model.card.CardType;
import model.card.SimpleCardFactory;
import view.map.MapGenerator;
import view.menu.MainMenu;
import view.menu.RoundStartMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 16/4/28.
 */
public class Kernal {
    private Map map;
    private GregorianCalendar date;
    private int playerNum;
    private List<Player> players;

    private volatile static Kernal uniqueInstance;

    private Kernal(int playerNum) {
        this.playerNum = playerNum;
        players = new ArrayList<>();
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

    public void circulate() {
        while (!findWinner(players)) {
            RoundStartMenu.displayRoundMenu();
            players.stream().filter(player -> !player.isBankrupt()).forEach(MainMenu::displayMainMenu);
            date.add(Calendar.DATE, 1);
        }
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

    public void addCards() {
        players.stream().map(Player::getCards).forEach(e -> {
            for (CardType cardType : CardType.values()) {
                e.put(SimpleCardFactory.createCard(cardType), 10);
            }
        });
    }

    public static boolean findWinner(List<Player> players) {
        List<Player> activePlayers = players.stream().filter(e -> !e.isBankrupt()).collect(Collectors.toList());
        if (activePlayers.size() == 1) {
            FindWinnerCommand command = (FindWinnerCommand) SimpleCommamdFactory.createCommand(CommandType.FIND_WINNER_COMMAND);
            command.setCommandStr(activePlayers.get(0).getName());
            command.action();
            return true;
        } else {
            return false;
        }
    }
}
