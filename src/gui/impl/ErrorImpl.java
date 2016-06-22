package gui.impl;

import action.command.ErrorCommand;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Created by Ethan on 16/4/30.
 */
public class ErrorImpl extends ErrorCommand {
    @Override
    public void action() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("错误");
            alert.setHeaderText(null);
            alert.setContentText(getCommandStr());

            alert.showAndWait();
        });

    }
}
