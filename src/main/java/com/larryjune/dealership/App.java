package com.larryjune.dealership;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    //Starts the main Application and loads ManScree.Fxml
    public void start(Stage stage) throws Exception {
        // Load all FXML resources
        FXMLLoader mainScreenLoader = new FXMLLoader(
            getClass().getResource("/com/larryjune/dealership/MainScreen.fxml")
        );

        // Load the main screen and create a scene for it
        VBox root = mainScreenLoader.load();
        Scene mainScene = new Scene(root);

        // Setup our stage and show everything
        stage.setTitle("Larry June Dealership");
        stage.getIcons().add(new Image(getClass().getResource("/com/larryjune/dealership/logo.png").toString()));
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}