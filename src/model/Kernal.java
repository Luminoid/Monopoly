package model;

import action.command.CommandType;
import action.command.FindWinnerCommand;
import action.command.SimpleCommamdFactory;
import model.card.CardType;
import util.playerTool;
import view.map.MapGenerator;
import view.menu.MainMenu;
import view.menu.RoundStartMenu;

import java.util.*;

/**
 * Created by Ethan on 16/4/28.
 */
public class Kernal {
    private Map map;
    private GregorianCalendar date;
    private GregorianCalendar endDate;
    private int playerNum;
    private List<Player> players;

    private volatile static Kernal uniqueInstance;

    private Kernal(int playerNum) {
        this.playerNum = playerNum;
        players = new ArrayList<>();
        date = new GregorianCalendar();
        endDate = new GregorianCalendar();
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
        while (date.before(endDate)) {
            RoundStartMenu.displayRoundMenu();
            for (Iterator<Player> it = players.iterator(); it.hasNext(); ) {
                Player player = it.next();
                if (players.size() == 1) {
                    FindWinnerCommand command = (FindWinnerCommand) SimpleCommamdFactory.createCommand(CommandType.FIND_WINNER_COMMAND);
                    command.setCommandStr(player.getName());
                    return;
                }
                MainMenu.displayMainMenu(player);
                if (player.isBankrupt()) {
                    it.remove();
                }
            }
            date.add(Calendar.DATE, 1);
        }
        // Time is over
        Player winner = players.stream().max((a, b) -> {
            if (playerTool.getAsset(a) - playerTool.getAsset(b) > 0) {
                return 1;
            } else {
                return -1;
            }
        }).get();
        FindWinnerCommand command = (FindWinnerCommand) SimpleCommamdFactory.createCommand(CommandType.FIND_WINNER_COMMAND);
        command.setCommandStr(winner.getName());
    }

    public Map getMap() {
        return map;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setDate(int year, int month, int day) {
        date.set(year, month - 1, day);
    }

    public void setEndDate(int year, int month, int day) {
        endDate.set(year, month - 1, day);
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
                e.put(cardType, 10);
            }
        });
    }
}
