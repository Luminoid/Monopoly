package gui;/**
 * Created by Ethan on 16/6/18.
 */

import gui.impl.PromptImpl;
import gui.init.InitViewController;
import gui.view.MapViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Kernel;

import java.io.IOException;

public class ViewController extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private static Group mapView;

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

            // Give RootLayoutController to other Class
            PromptImpl prompt = new PromptImpl();
            prompt.setViewController(loader.getController());

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
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
        Canvas canvas = new Canvas(556, 546);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        MapViewController controller = new MapViewController();
        controller.drawMap(gc);
        mapView.getChildren().add(canvas);

        // Set person overview into the center of root layout.
        rootLayout.setCenter(mapView);

    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public static void expectUserCommand() {
    }

    public static Group getMapView() {
        return mapView;
    }
}
