package gui.init;

import gui.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Kernel;
import model.Player;

import java.time.LocalDate;

/**
 * Created by Ethan on 16/6/19.
 */
public class InitViewController {
    private ViewController viewController;

    @FXML
    private TextField cashTxt;

    @FXML
    private DatePicker beginDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField playerATxt;

    @FXML
    private TextField playerBTxt;

    @FXML
    private TextField playerCTxt;

    @FXML
    private TextField playerDTxt;

    @FXML
    private Label promptLabel;

    @FXML
    private Button startBtn;

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    @FXML
    private void handleStartBtn() {
        // Clear
        promptLabel.setText("");

        // Player Num
        int playNum = 0;
        if (playerATxt.getText().length() != 0) playNum++;
        if (playerBTxt.getText().length() != 0) playNum++;
        if (playerCTxt.getText().length() != 0) playNum++;
        if (playerDTxt.getText().length() != 0) playNum++;
        if (playNum >= 2) {
            Kernel.createInstance(playNum, true);
        } else {
            promptLabel.setText("最小玩家个数为2！");
            return;
        }

        // Cash
        double cash = 0.0;
        if (cashTxt.getText().length() == 0) cash = 20000;
        else {
            try {
                cash = Double.parseDouble(cashTxt.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
                promptLabel.setText("现金输入格式不正确");
            }
        }

        // Time
        try {
            LocalDate ldBegin = beginDatePicker.getValue();
            LocalDate ldEnd = endDatePicker.getValue();
            if (ldBegin.isAfter(ldEnd)) {
                promptLabel.setText("游戏结束时间一定要晚于开始时间！");
                return;
            }
            Kernel.getInstance().setDate(ldBegin.getYear(), ldBegin.getMonthValue(), ldBegin.getDayOfMonth());
            Kernel.getInstance().setEndDate(ldEnd.getYear(), ldEnd.getMonthValue(), ldEnd.getDayOfMonth());
        } catch (Exception ex) {
            ex.printStackTrace();
            promptLabel.setText("请输入游戏的开始和结束时间！");
        }

        // Player Name
        if (playerATxt.getText().length() != 0) Kernel.getInstance().addPlayer(new Player(playerATxt.getText(), cash));
        if (playerBTxt.getText().length() != 0) Kernel.getInstance().addPlayer(new Player(playerBTxt.getText(), cash));
        if (playerCTxt.getText().length() != 0) Kernel.getInstance().addPlayer(new Player(playerCTxt.getText(), cash));
        if (playerDTxt.getText().length() != 0) Kernel.getInstance().addPlayer(new Player(playerDTxt.getText(), cash));

        // Switch to Game View
        viewController.initRootLayout();
    }
}
