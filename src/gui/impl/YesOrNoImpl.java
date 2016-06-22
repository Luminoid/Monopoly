package gui.impl;

import action.request.YesOrNoRequest;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Ethan on 16/4/30.
 */
public class YesOrNoImpl extends YesOrNoRequest {

    @Override
    public void action() {
        if (yesOrNoRequest(getQuestionStr())) {
            setAnswer(true);
        } else {
            setAnswer(false);
        }
    }

    public boolean yesOrNoRequest(String s) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("请选择");
        alert.setHeaderText(null);
        alert.setContentText(s);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
