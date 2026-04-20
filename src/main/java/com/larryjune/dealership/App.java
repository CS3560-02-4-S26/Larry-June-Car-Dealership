package com.larryjune.dealership;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        FXMLLoader carItemLoader = new FXMLLoader(
                getClass().getResource("/com/larryjune/dealership/CarItem.fxml")
        );

        // Load the main screen and create a scene for it
        VBox root = mainScreenLoader.load();
        Scene mainScene = new Scene(root);

        // Load a car item card and add it to the main scene
        Parent carItem = carItemLoader.load();
        VBox carItemLayout = new VBox(carItem);
        root.getChildren().add(carItemLayout);

        // Setup our stage and show everything
        stage.setTitle("Larry June Dealership");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}