package gui.impl;

import action.request.IntRangeRequestWithLock;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * Created by Ethan on 16/4/30.
 */
public class IntRangeImplWithLock extends IntRangeRequestWithLock {
    @Override
    public void action() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("请选择");
        dialog.setHeaderText(getQuestionStr());
        ButtonType okButtonType = new ButtonType("确认", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        GridPane grid = new GridPane();
        Slider slider = new Slider();
        slider.setMin(floor);
        slider.setMax(ceiling);
        slider.setValue(floor);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(5);
        slider.setPrefWidth(240);
        Label sliderValue = new Label();
        slider.valueProperty().addListener((observable, oldValue, newValue) -> sliderValue.setText(newValue.intValue() + ""));
        grid.add(slider, 0, 0);
        grid.add(sliderValue, 0, 1);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return (int) slider.getValue();
            }
            return null;
        });
        Optional<Integer> result = dialog.showAndWait();
        result.ifPresent(it -> setAnswer((int) slider.getValue()));
    }
}
