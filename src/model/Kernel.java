package model;

import action.command.CommandType;
import action.command.FindWinnerCommand;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.card.CardType;
import model.map.MapGenerator;
import model.spot.Spot;
import model.spot.SpotType;
import model.stock.StockGenerator;
import model.stock.StockMarket;
import tui.menu.MainMenu;
import util.DateTool;
import util.PlayerOrientation;
import util.PlayerUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/**
 * Created by Ethan on 16/4/28.
 */
public class Kernel {
    private int playerNum;
    private ObservableList<Player> players;
    private GregorianCalendar date;
    private GregorianCalendar endDate;
    private model.map.Map map;
    private StockMarket stockMarket;
    private int hospitalPosition;
    private boolean isGui;
    private ObjectProperty<Player> currentPlayer;
    private ObjectProperty<PlayerOrientation> currentOrientation;
    private Semaphore semaphore = new Semaphore(0);

    private volatile static Kernel uniqueInstance;

    private Kernel(int playerNum, boolean isGui) {
        this.playerNum = playerNum;
        players = FXCollections.observableArrayList();
        date = new GregorianCalendar();
        endDate = new GregorianCalendar();
        map = MapGenerator.generate();
        stockMarket = StockGenerator.generate();
        hospitalPosition = findHospitalPosition();
        currentPlayer = new SimpleObjectProperty<>(new Player());
        currentOrientation = new SimpleObjectProperty<>(PlayerOrientation.FORWARD);
        this.isGui = isGui;
    }

    public static Kernel getInstance() {
        return uniqueInstance;
    }

    public static void createInstance(int playerNum, boolean isGui) {
        if (uniqueInstance == null) {
            synchronized (Kernel.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Kernel(playerNum, isGui);
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
            PromptCommand dateCommand = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
            dateCommand.setCommandStr("今天是" + date.get(Calendar.YEAR) + "年" + (date.get(Calendar.MONTH) + 1) + "月" + date.get(Calendar.DAY_OF_MONTH) + "日");
            for (Iterator<Player> it = players.iterator(); it.hasNext(); ) {
                Player player = it.next();

                Platform.runLater(() -> {
                    currentPlayer.set(player);
                    currentOrientation.set(player.getOrientation());
                });

                if (players.size() == 1) {
                    FindWinnerCommand command = (FindWinnerCommand) SimpleCommandFactory.createCommand(CommandType.FIND_WINNER_COMMAND);
                    command.setCommandStr(player.getName());
                    return;
                }
                if (isGui) {
                    try {
                        if (player.getInjureValue() == 0) {
                            semaphore.acquire();
                        } else {
                            int injureValue = player.getInjureValue();
                            PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
                            command.setCommandStr("玩家" + player.getName() + "受伤，还需停止" + injureValue + "回合");
                            player.setInjureValue(injureValue - 1);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    MainMenu.displayMainMenu(player);
                }
                if (player.isBankrupt()) {
                    it.remove();
                }
            }
            date.add(Calendar.DATE, 1);
        }
        // Time is over
        Player winner = players.stream().max((a, b) -> {
            if (PlayerUtil.getAsset(a) - PlayerUtil.getAsset(b) > 0) {
                return 1;
            } else {
                return -1;
            }
        }).get();
        FindWinnerCommand command = (FindWinnerCommand) SimpleCommandFactory.createCommand(CommandType.FIND_WINNER_COMMAND);
        command.setCommandStr(winner.getName());
    }

    public void addCards() {
        players.stream().map(Player::getCards).forEach(e -> {
            for (CardType cardType : CardType.values()) {
                e.put(cardType, 10);
            }
        });
    }

    public int findHospitalPosition() {
        ArrayList<Spot> spots = map.getSpotList();
        for (int i = 0; i < spots.size(); i++) {
            if (spots.get(i).getSpotType() == SpotType.HospitalSpot) {
                return i;
            }
        }
        return 0;
    }

    public model.map.Map getMap() {
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

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getHospitalPosition() {
        return hospitalPosition;
    }

    public PlayerOrientation getCurrentOrientation() {
        return currentOrientation.get();
    }

    public StringBinding getCurrentOrientationString() {
        return Bindings.createStringBinding(
                () -> currentOrientation.get() == PlayerOrientation.FORWARD ? "顺时针" : "逆时针", currentOrientation
        );
    }

    public ObjectProperty<PlayerOrientation> currentOrientationProperty() {
        return currentOrientation;
    }

    public void setCurrentOrientation(PlayerOrientation currentOrientation) {
        this.currentOrientation.set(currentOrientation);
    }

    public Player getCurrentPlayer() {
        return currentPlayer.get();
    }

    public StringBinding getCurrentPlayerName() {
        return Bindings.createStringBinding(() -> currentPlayer.get().getName(), currentPlayer);
    }

    public ObjectProperty<Player> currentPlayerProperty() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer.set(currentPlayer);
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}
