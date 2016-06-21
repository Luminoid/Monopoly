package gui.impl;

import action.request.IntRangeRequest;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

/**
 * Created by Ethan on 16/4/30.
 */
public class IntRangeImpl extends IntRangeRequest {
    @Override
    public void action() {

        Dialog dialog = new Dialog<>();
        dialog.setTitle("请选择");
        dialog.setHeaderText(getQuestionStr());

        GridPane pane = new GridPane();
        Slider slider = new Slider();
        slider.setMin(floor);
        slider.setMax(ceiling);
        slider.setValue(floor);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        pane.add(slider, 0, 0);

        // Button
        final Button btnOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnOk.addEventHandler(ActionEvent.ACTION, event -> {
            setAnswer((int) slider.getValue());
        });

        dialog.getDialogPane().setContent(pane);
        dialog.showAndWait();
    }
}
