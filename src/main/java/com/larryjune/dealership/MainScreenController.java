package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Image;
import com.larryjune.dealership.model.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {
    @FXML
    public TilePane carGrid;

    @FXML
    public void initialize() {
        // Read items from the database
        ArrayList<Vehicle> featuredVehicleData;
        try {
            // TODO: use DBControl.fetchVehicleDataAt() to make a more filtered
            featuredVehicleData = DBControl.fetchVehicleDataAt(
                    "mileage", "50000", "<");
        } catch (Exception e) {
            System.err.println("Featured vehicles: Failed to fetch vehicle data!!!!");
            e.printStackTrace();

            Label errorLabel = new Label("Failed to load featured vehicles");
            carGrid.getChildren().add(errorLabel);
            return;
        }

        if (featuredVehicleData.isEmpty()) {
            Label noFeaturedCars = new Label("No featured cars are available at this time.");
            carGrid.getChildren().add(noFeaturedCars);
            return;
        }

        // Add dummy car items
        FXMLLoader carItemLoader;
        ArrayList<Image> currentVehicleImages;
        CarItemController controller;
        VBox carItem;
        for (Vehicle vehicle : featuredVehicleData) {
            try {
                carItemLoader = new FXMLLoader(
                        getClass().getResource("/com/larryjune/dealership/CarItem.fxml"));

                carItem = carItemLoader.load();
                controller = carItemLoader.getController();

                controller.setInfo(vehicle);
                carGrid.getChildren().add(carItem);
            } catch (IOException e) {
                // Skip the current entry
                System.err.println("ERROR: Failed to add entry to featured cars");
            }
        }
    }

    // Handles the login button in Main Screen
    public void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 900));
        stage.setTitle("Login");
        stage.show();
    }

    // Handles the Information button in Main Screen
    public void handleInformation(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(
            getClass().getResource("/com/larryjune/dealership/InformationScreen.fxml"));

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root, 1000, 900));
    stage.setTitle("Information");
    stage.show();
    }
    }
}