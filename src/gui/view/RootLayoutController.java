package gui.view;

import action.command.CommandType;
import action.command.ErrorCommand;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import gui.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Dice;
import model.Kernel;
import model.Player;
import model.card.Card;
import model.card.SimpleCardFactory;
import model.spot.SpotType;
import util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private void handleUseCardBtn(){
        Player player = Kernel.getInstance().getCurrentPlayer();
        ArrayList<Card> usableCards = new ArrayList<>();
        player.getCards().keySet().stream().forEach(e -> usableCards.add(SimpleCardFactory.createCard(e)));
        int cardNum = usableCards.size();
        ErrorCommand errorCommand = (ErrorCommand) SimpleCommandFactory.createCommand(CommandType.ERROR_COMMAND);
        if (cardNum == 0) {
            errorCommand.setCommandStr("您现在没有道具卡可以使用！");
        } else {
            List<String> choices = new ArrayList<>();

            for (int i = 0; i < cardNum; i++) {
                choices.add(usableCards.get(i).getName() + " × " + player.getCards().get(usableCards.get(i).getCardType()));
            }

            ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
            dialog.setTitle("选择");
            dialog.setHeaderText(null);
            dialog.setContentText("请选择要使用的卡片：");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(letter -> {
                Card usedCard = usableCards.stream().filter(e -> e.getName().equals(letter.split(" ")[0])).findFirst().get();
                if (usedCard.use(player)) {
                    player.useCard(usedCard.getCardType());
                    ViewController.repaint();
                } else {
                    errorCommand.setCommandStr("本情形不能使用该卡片！");
                }
            });
        }
    }

    @FXML
    private void handleThrowDiceBtn() {
        Player currentPlayer = Kernel.getInstance().getCurrentPlayer();
        int diceValue = Dice.getInstance().rollDice();
        new Thread(() -> {
            int step;
            for (step = 0; step < diceValue; step++) {
                if (PlayerUtil.getPlayerSpot(currentPlayer).getSpotType() == SpotType.BankSpot) {
//                    Platform.runLater(() -> {
                        PlayerUtil.getPlayerSpot(currentPlayer).passByEvent(currentPlayer);
//                    });
                } else if (PlayerUtil.getDistantSpot(currentPlayer, 1).isBlocked()) {
                    PlayerUtil.getDistantSpot(currentPlayer, 1).setBlocked(false);
                    currentPlayer.move();
                    ViewController.repaint();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    step++;
                    break;
                }
                currentPlayer.move();
                ViewController.repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
            command.setCommandStr("玩家 " + currentPlayer.getName() + " 前进了" + step + "步");

            PlayerUtil.getPlayerSpot(currentPlayer).arriveEvent(currentPlayer);
            ViewController.repaint();
            Kernel.getInstance().getSemaphore().release();
        }).start();
    }

    @FXML
    private void handleGiveUpBtn() {
        Kernel.getInstance().getCurrentPlayer().giveUp();
        Kernel.getInstance().getSemaphore().release();
    }

    public void promptText(String s) {
        promptTxt.appendText(s + "\n");
    }
}
