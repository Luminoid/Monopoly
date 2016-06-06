package model;

import action.command.CommandType;
import action.command.FindWinnerCommand;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import model.card.CardType;
import model.stock.StockGenerator;
import model.stock.StockMarket;
import util.DateTool;
import util.PlayerTool;
import view.map.MapGenerator;
import view.menu.MainMenu;
import view.menu.RoundStartMenu;

import java.util.*;

/**
 * Created by Ethan on 16/4/28.
 */
public class Kernel {
    private int playerNum;
    private List<Player> players;
    private GregorianCalendar date;
    private GregorianCalendar endDate;
    private Map map;
    private StockMarket stockMarket;

    private volatile static Kernel uniqueInstance;

    private Kernel(int playerNum) {
        this.playerNum = playerNum;
        players = new ArrayList<>();
        date = new GregorianCalendar();
        endDate = new GregorianCalendar();
        map = MapGenerator.generate();
        stockMarket = StockGenerator.generate();
    }

    public static Kernel getInstance() {
        return uniqueInstance;
    }

    public static void createInstance(int playerNum) {
        if (uniqueInstance == null) {
            synchronized (Kernel.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Kernel(playerNum);
                }
            }
        }
    }

    public void circulate() {
        while (date.before(endDate)) {
            // Month info
            if (DateTool.isEndOfMonth(date)) {
                LotterySystem.drawLottery();
                players.stream().forEach(e -> e.setDeposit(e.getDeposit() * 1.1));
                PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
                command.setCommandStr("银行利息已发放");
            }
            if (DateTool.isWeekday(date)) {
                stockMarket.open();
            }
            RoundStartMenu.displayRoundMenu();
            for (Iterator<Player> it = players.iterator(); it.hasNext(); ) {
                Player player = it.next();
                if (players.size() == 1) {
                    FindWinnerCommand command = (FindWinnerCommand) SimpleCommandFactory.createCommand(CommandType.FIND_WINNER_COMMAND);
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
            if (PlayerTool.getAsset(a) - PlayerTool.getAsset(b) > 0) {
                return 1;
            } else {
                return -1;
            }
        }).get();
        FindWinnerCommand command = (FindWinnerCommand) SimpleCommandFactory.createCommand(CommandType.FIND_WINNER_COMMAND);
        command.setCommandStr(winner.getName());
    }

    public Map getMap() {
        return map;
    }

    public StockMarket getStockMarket() {
        return stockMarket;
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
