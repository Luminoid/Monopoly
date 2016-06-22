package gui.menu;

import gui.ViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Kernel;
import model.Player;
import model.stock.Stock;
import util.FormatTool;
import util.NumberUtil;

import java.util.Optional;

/**
 * Created by Ethan on 16/5/1.
 */
public class StockMenu {
    private ViewController viewController;

    @FXML
    private TableView<Stock> stockTable;

    @FXML
    private TableColumn<Stock, String> stockIndexColumn;

    @FXML
    private TableColumn<Stock, String> stockNameColumn;

    @FXML
    private TableColumn<Stock, String> stockPriceColumn;

    @FXML
    private TableColumn<Stock, String> stockFloatRateColumn;

    @FXML
    private TableColumn<Stock, String> stockHoldingNumColumn;

    @FXML
    private LineChart<Integer, Double> stockTrend;

    @FXML
    private void initialize() {
        stockTable.setItems(Kernel.getInstance().getStockMarket().getStocks());

        stockIndexColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        stockNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        stockPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString("%.1f"));
        stockFloatRateColumn.setCellValueFactory(cellData -> cellData.getValue().getFloatRateBinding());
    }

    @FXML
    private void showMyHoldingBtn(){
        stockHoldingNumColumn.setCellValueFactory(cellData ->
                cellData.getValue().getHoldingNumProperty().asString("%.1f"));
    }

    @FXML
    private void buyStockBtn(){
        Platform.runLater(() -> {
            Stock stock = stockTable.getSelectionModel().getSelectedItem();
            if (stock != null){
                Player player = Kernel.getInstance().getCurrentPlayer();
                // Create the custom dialog.
                Dialog<Double> dialog = new Dialog<>();
                dialog.setTitle("买股票");
                dialog.setHeaderText("请输入您想买入的数量（每股 ¥"+ FormatTool.formatMoney(stock.getPrice())+"）：");

                // Set the button types.
                ButtonType okButtonType = new ButtonType("购买", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                // Create the content
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);

                TextField value = new TextField();
                Label promptLabel = new Label();
                promptLabel.setTextFill(Color.RED);

                grid.add(value, 0, 0);
                grid.add(promptLabel, 0, 1);

                // Enable/Disable ok button depending on whether a value was in the right format.
                Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
                okButton.setDisable(false);

                // Validation
                value.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!NumberUtil.isInt(newValue)) {
                        promptLabel.textProperty().set("请输入正确的数字！");
                        okButton.setDisable(true);
                    } else if (Integer.parseInt(newValue) * stock.getPrice() > player.getCash()) {
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
                    int num = Integer.parseInt(value.textProperty().get());
                    Kernel.getInstance().getStockMarket().buyStock(player, stock.getId(), num);
                });
            }
        });
    }

    @FXML
    private void sellStockBtn(){
        Platform.runLater(() -> {
            Stock stock = stockTable.getSelectionModel().getSelectedItem();
            if (stock != null){
                Player player = Kernel.getInstance().getCurrentPlayer();
                // Create the custom dialog.
                Dialog<Double> dialog = new Dialog<>();
                dialog.setTitle("卖股票");
                dialog.setHeaderText("请输入您想卖出的数量（每股 ¥"+ FormatTool.formatMoney(stock.getPrice())+"）：");

                // Set the button types.
                ButtonType okButtonType = new ButtonType("卖出", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

                // Create the content
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);

                TextField value = new TextField();
                Label promptLabel = new Label();
                promptLabel.setTextFill(Color.RED);

                grid.add(value, 0, 0);
                grid.add(promptLabel, 0, 1);

                // Enable/Disable ok button depending on whether a value was in the right format.
                Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
                okButton.setDisable(false);

                // Validation
                value.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!NumberUtil.isInt(newValue)) {
                        promptLabel.textProperty().set("请输入正确的数字！");
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
                    int num = Integer.parseInt(value.textProperty().get());
                    Kernel.getInstance().getStockMarket().sellStock(player, stock.getId(), num);
                });
            }
        });
    }

    @FXML
    private void showTrend(){
        stockTrend.getData().clear();
        Stock stock = stockTable.getSelectionModel().getSelectedItem();
        ObservableList<XYChart.Series<Integer, Double>> lineChartData = FXCollections.observableArrayList();
        LineChart.Series<Integer, Double> series = new LineChart.Series<>();
        series.setName(stock.getName());

        for (int i = 0; i < stock.getPriceRecord().size(); i++){
            series.getData().add(new XYChart.Data<>(i, stock.getPriceRecord().get(i)));
        }
        lineChartData.add(series);
        stockTrend.setData(lineChartData);
        stockTrend.createSymbolsProperty();
    }

    @FXML
    private void exitBtn(){
        viewController.showMainView();
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }
}
