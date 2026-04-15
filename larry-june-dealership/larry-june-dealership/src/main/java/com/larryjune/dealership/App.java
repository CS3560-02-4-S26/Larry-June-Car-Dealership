package com.larryjune.dealership;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/larryjune/dealership/MainScreen.fxml")
        );
        Parent root = loader.load();

        Scene scene = new Scene(root, 1000, 900);
        stage.setTitle("Larry June Dealership");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}