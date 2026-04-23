package com.larryjune.dealership;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreenController {
    @FXML
    public TilePane carGrid;

    @FXML
    public void initialize() {
        // Add dummy car items
        FXMLLoader carItemLoader;
        for (int i = 0; i < 20; i++) {
            try {
                carItemLoader = new FXMLLoader(
                        getClass().getResource("/com/larryjune/dealership/CarItem.fxml")
                );

                VBox carItem = carItemLoader.load();
                ((Label) carItem.getChildren().get(1)).setText("Car " + (i + 1));

                carGrid.getChildren().add(carItem);
            } catch (IOException e) {
                // TODO: Better error handling
                System.err.println("ERROR: Failed to load cars!");
            }
        }
    }

    //Handles the login button in Main Screen
    public void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 900));
        stage.setTitle("Login");
        stage.show();
    }
}