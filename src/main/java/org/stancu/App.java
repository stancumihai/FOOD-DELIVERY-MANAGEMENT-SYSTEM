package org.stancu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.stancu.util.InitApp;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = new Scene(loadFXML("login"), 360, 650);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml, int width, int height) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stage.setHeight(height);
        stage.setWidth(width);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        InitApp.serializeInitialProducts();
        InitApp.deserializeInitialUsers("users.txt");
        InitApp.deserializeInitialClients("clients.txt");
        InitApp.deserializeInitialEmployees("employees.txt");
        InitApp.deserializeInitialAdmins("admins.txt");
        InitApp.deserializeInitialOrders("orders.txt");
        InitApp.initProductIds();
        launch();
    }
}