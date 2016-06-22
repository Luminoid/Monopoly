package gui;/**
 * Created by Ethan on 16/6/18.
 */

import gui.impl.PromptImpl;
import gui.init.InitViewController;
import gui.menu.StockMenu;
import gui.view.MapViewController;
import gui.view.RootLayoutController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Kernel;

import java.io.IOException;

public class ViewController extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private static Scene mainScene;
    private static Group mapView;
    private static MapViewController mapViewController;
    private static RootLayoutController rootLayoutController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Monopoly");

        showInitView();
    }

    /**
     * Init View
     */
    public void showInitView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("init/InitView.fxml"));

            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);

            // Give InitViewController access to the ViewController
            InitViewController initViewController = loader.getController();
            initViewController.setViewController(this);

            primaryStage.show();
            primaryStage.setOnCloseRequest(e -> System.exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Game View
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            rootLayoutController = loader.getController();
            rootLayoutController.setViewController(this);

            // Give RootLayoutController to other Class
            PromptImpl prompt = new PromptImpl();
            prompt.setViewController(loader.getController());

            // Show the scene containing the root layout.
            mainScene = new Scene(rootLayout);
            primaryStage.setScene(mainScene);
            showMapView();
            Thread thread = new Thread(() -> {
                Kernel.getInstance().addCards();
                Kernel.getInstance().circulate();
            });
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMapView() {
        mapView = new Group();
        mapViewController = new MapViewController();
        mapViewController.drawMap(mapView);

        // Set person overview into the center of root layout.
        rootLayout.setCenter(mapView);
    }

    public void showStockMenu(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu/StockMenu.fxml"));
            Scene scene = new Scene(loader.load());
            StockMenu stockMenu = loader.getController();
            stockMenu.setViewController(this);
            primaryStage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void showMainView(){
        primaryStage.setScene(mainScene);
    }

    public static void repaint() {
        Platform.runLater(() -> {
            mapViewController.drawMap(mapView);
        });
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public static void expectUserCommand() {
    }

    public static Group getMapView() {
        return mapView;
    }

    public static MapViewController getMapViewController() {
        return mapViewController;
    }

    public static RootLayoutController getRootLayoutController() {
        return rootLayoutController;
    }
}
