package gui.view;

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
        Player player = Kernel.getInstance().getCurrentPlayer();
        int diceValue = Dice.getInstance().rollDice();
        for (int step = 0; step < diceValue; step++) {
            if (PlayerUtil.getPlayerSpot(player).getSpotType() == SpotType.BankSpot) {
                PlayerUtil.getPlayerSpot(player).passByEvent(player);
            } else if (PlayerUtil.getDistantSpot(player, 1).isBlocked()) {
                PlayerUtil.getDistantSpot(player, 1).setBlocked(false);
                player.move();
                ViewController.getMapView().requestLayout();
                step++;
                break;
            }
            player.move();
        }
        PlayerUtil.getPlayerSpot(player).arriveEvent(player);

        Kernel.getInstance().getSemaphore().release();
    }

    public void promptText(String s) {
        promptTxt.appendText(s + "\n");
    }
}
