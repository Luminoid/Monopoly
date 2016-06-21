package gui.view;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import gui.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import model.Dice;
import model.Kernel;
import model.Player;
import model.spot.SpotType;
import util.PlayerUtil;

/**
 * Created by Ethan on 16/6/18.
 */
public class RootLayoutController {
    @FXML
    private TableView<Player> playerTable;

    @FXML
    private TableColumn<Player, String> playerNameColumn;

    @FXML
    private TableColumn<Player, String> playerCashColumn;

    @FXML
    private TableColumn<Player, String> playerDepositColumn;

    @FXML
    private TableColumn<Player, String> playerTicketColumn;

    @FXML
    private TableColumn<Player, String> playerEstateColumn;

    @FXML
    private TableColumn<Player, String> playerAssetColumn;

    @FXML
    private Label playerLabel;

    @FXML
    private Label orientationLabel;

    @FXML
    private TextArea promptTxt;

    @FXML
    private void initialize() {
        playerTable.setItems(Kernel.getInstance().getPlayers());

        playerNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playerCashColumn.setCellValueFactory(cellData -> cellData.getValue().cashProperty());
        playerDepositColumn.setCellValueFactory(cellData -> cellData.getValue().depositProperty());
        playerTicketColumn.setCellValueFactory(cellData -> cellData.getValue().ticketProperty());
        playerEstateColumn.setCellValueFactory(cellData -> cellData.getValue().estateProperty().asString("%.1f"));
        playerAssetColumn.setCellValueFactory(cellData -> cellData.getValue().assestProperty().asString("%.1f"));

        playerLabel.textProperty().bind(Kernel.getInstance().getCurrentPlayerName());
        orientationLabel.textProperty().bind(Kernel.getInstance().getCurrentOrientationString());
    }

    public static void initializePlayerInfo() {

    }

    @FXML
    private void handleThrowDiceBtn() {
        Player currentPlayer = Kernel.getInstance().getCurrentPlayer();
        int diceValue = Dice.getInstance().rollDice();
        new Thread (() -> {
            int step;
            for (step = 0; step < diceValue; step++) {
                if (PlayerUtil.getPlayerSpot(currentPlayer).getSpotType() == SpotType.BankSpot) {
                    PlayerUtil.getPlayerSpot(currentPlayer).passByEvent(currentPlayer);
                } else if (PlayerUtil.getDistantSpot(currentPlayer, 1).isBlocked()) {
                    PlayerUtil.getDistantSpot(currentPlayer, 1).setBlocked(false);
                    currentPlayer.move();
                    ViewController.getGc().clearRect(0, 0, 556, 546);
                    ViewController.getMapViewController().drawMap(ViewController.getGc());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    step++;
                    break;
                }
                currentPlayer.move();
                ViewController.getGc().clearRect(0, 0, 556, 546);
                ViewController.getMapViewController().drawMap(ViewController.getGc());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
            command.setCommandStr("玩家 " + currentPlayer.getName() + " 前进了" + step + "步");

            PlayerUtil.getPlayerSpot(currentPlayer).arriveEvent(currentPlayer);
            Kernel.getInstance().getSemaphore().release();

        }).start();
    }

    public void promptText(String s) {
        promptTxt.appendText(s + "\n");
    }
}
