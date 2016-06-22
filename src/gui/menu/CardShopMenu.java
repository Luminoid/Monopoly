package gui.menu;

import action.command.CommandType;
import action.command.PromptCommand;
import action.command.SimpleCommandFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Player;
import model.card.Card;
import model.card.CardType;
import model.card.SimpleCardFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 16/5/1.
 */
public class CardShopMenu {
    public static void displayCardShopMenu(Player player) {
        Platform.runLater(() -> {
            // Create the custom dialog.
            Dialog dialog = new Dialog<>();
            dialog.setTitle("道具店");
            dialog.setHeaderText("欢迎来到道具店，您有" + player.getTicket() + "张点券\n您现在可以购买以下道具卡：");

            // Set the button types.
            ButtonType okButtonType = new ButtonType("购买", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            // Create the content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            List<CardType> cardTypes = Arrays.asList(CardType.values());
            List<Card> cards = cardTypes.stream().map(SimpleCardFactory::createCard).collect(Collectors.toList());
            List<String> choiceItems = cards.stream().map(CardShopMenu::getCardPriceTag).collect(Collectors.toList());

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll(choiceItems);
            Label promptLabel = new Label();
            promptLabel.setTextFill(Color.RED);

            grid.add(choiceBox, 0, 0);
            grid.add(promptLabel, 0, 1);

            Node okButton = dialog.getDialogPane().lookupButton(okButtonType);

            // Validation
            choiceBox.valueProperty().addListener((ObservableValue, oldValue, newValue) -> {
                if (player.getTicket() <
                        cards.stream().filter(e -> e.getName().equals(choiceBox.getValue().split(" ")[0])).findFirst().get().getValue()){
                    promptLabel.setText("您没有足够的点券！");
                    okButton.setDisable(true);
                } else {
                    promptLabel.setText("");
                    okButton.setDisable(false);
                }
            });

            // Add Listener on BuyButton
            okButton.addEventFilter(ActionEvent.ACTION, event -> {
                Card card = cards.stream().filter(e -> e.getName().equals(choiceBox.getValue().split(" ")[0])).findFirst().get();
                player.setTicket(player.getTicket() - card.getValue());
                player.addCard(card.getCardType());
                PromptCommand command = (PromptCommand) SimpleCommandFactory.createCommand(CommandType.PROMPT_COMMAND);
                command.setCommandStr("购买成功");
                dialog.setHeaderText("欢迎来到道具店，您有" + player.getTicket() + "张点券\n您现在可以购买以下道具卡：");
                event.consume();
            });

            dialog.getDialogPane().setContent(grid);

            dialog.showAndWait();
        });

    }

    public static String getCardPriceTag(Card card){
        return card.getName() + " \t -> $" + card.getValue();
    }
}
