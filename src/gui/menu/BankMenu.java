package gui.menu;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Player;
import util.FormatTool;
import util.NumberUtil;

import java.util.Optional;

/**
 * Created by Ethan on 16/5/1.
 */
public class BankMenu {
    public static void displayBankMenu(Player player) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("银行");
            alert.setHeaderText(null);
            alert.setContentText("欢迎来到银行，您有现金¥" +
                    FormatTool.formatMoney(player.getCash()) + "元，存款¥" + FormatTool.formatMoney(player.getDeposit()) + "元。" +
                    "\n请选择您的操作：");

            ButtonType buttonTypeOne = new ButtonType("存款");
            ButtonType buttonTypeTwo = new ButtonType("取款");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                depositMoney(player);
            } else if (result.get() == buttonTypeTwo) {
                withdrawMoney(player);
            } else {

            }
        });
    }

    private static void depositMoney(Player player) {
        Platform.runLater(() -> {
            // Create the custom dialog.
            Dialog<Double> dialog = new Dialog<>();
            dialog.setTitle("存款");
            dialog.setHeaderText("请输入您想存入的金额：");

            // Set the button types.
            ButtonType okButtonType = new ButtonType("确认", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(okButtonType);

            // Create the content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField value = new TextField();
            Label promptLabel = new Label();
            promptLabel.setTextFill(Color.RED);

            grid.add(new Label("金额:"), 0, 0);
            grid.add(value, 1, 0);
            grid.add(promptLabel, 0, 1, 2, 1);

            // Enable/Disable ok button depending on whether a value was in the right format.
            Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
            okButton.setDisable(false);

            // Validation
            value.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!NumberUtil.isDouble(newValue)) {
                    promptLabel.textProperty().set("请输入正确地数字！");
                    okButton.setDisable(true);
                } else if (Double.parseDouble(newValue) > player.getCash()) {
                    promptLabel.textProperty().set("您没有足够的现金！");
                    okButton.setDisable(true);
                } else {
                    promptLabel.textProperty().set("");
                    okButton.setDisable(false);
                }
            });

            dialog.getDialogPane().setContent(grid);

            // Request focus on the value field by default.
            Platform.runLater(() -> value.requestFocus());

            // Convert the result to a Double when the ok button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == okButtonType) {
                    return Double.parseDouble(value.textProperty().get());
                }
                return null;
            });
            Optional<Double> result = dialog.showAndWait();

            result.ifPresent(it -> {
                double num = Double.parseDouble(value.textProperty().get());
                player.setCash(player.getCash() - num);
                player.setDeposit(player.getDeposit() + num);
            });
        });
    }

    private static void withdrawMoney(Player player) {
        Platform.runLater(() -> {
            // Create the custom dialog.
            Dialog<Double> dialog = new Dialog<>();
            dialog.setTitle("取款");
            dialog.setHeaderText("请输入您想取出的金额：");

            // Set the button types.
            ButtonType okButtonType = new ButtonType("确认", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(okButtonType);

            // Create the content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField value = new TextField();
            Label promptLabel = new Label();
            promptLabel.setTextFill(Color.RED);

            grid.add(new Label("金额:"), 0, 0);
            grid.add(value, 1, 0);
            grid.add(promptLabel, 0, 1, 2, 1);

            // Enable/Disable ok button depending on whether a value was in the right format.
            Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
            okButton.setDisable(false);

            // Validation
            value.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!NumberUtil.isDouble(newValue)) {
                    promptLabel.textProperty().set("请输入正确地数字！");
                    okButton.setDisable(true);
                } else if (Double.parseDouble(newValue) > player.getDeposit()) {
                    promptLabel.textProperty().set("您没有足够的存款！");
                    okButton.setDisable(true);
                } else {
                    promptLabel.textProperty().set("");
                    okButton.setDisable(false);
                }
            });

            dialog.getDialogPane().setContent(grid);

            // Request focus on the value field by default.
            Platform.runLater(() -> value.requestFocus());

            // Convert the result to a Double when the ok button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == okButtonType) {
                    return Double.parseDouble(value.textProperty().get());
                }
                return null;
            });
            Optional<Double> result = dialog.showAndWait();

            result.ifPresent(it -> {
                double num = Double.parseDouble(value.textProperty().get());
                player.setCash(player.getCash() + num);
                player.setDeposit(player.getDeposit() - num);
            });
        });
    }
}
