package gui.impl;

import action.command.FindWinnerCommand;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Created by Ethan on 16/4/30.
 */
public class FindWinnerImpl extends FindWinnerCommand {
    @Override
    public void action() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("游戏结束");
            alert.setHeaderText(null);
            alert.setContentText(getCommandStr() + " 获胜！\n游戏结束");
            alert.showAndWait();
            Platform.exit();
        });
    }
}
