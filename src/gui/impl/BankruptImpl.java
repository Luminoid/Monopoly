package gui.impl;

import action.command.BankruptCommand;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Created by Ethan on 16/4/30.
 */
public class BankruptImpl extends BankruptCommand {
    @Override
    public void action() {
        Platform.runLater(() -> {
            getPlayer().setBankrupt(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("破产");
            alert.setHeaderText(null);
            alert.setContentText(getPlayer().getName() + " 已破产！");

            alert.showAndWait();
        });
    }
}
