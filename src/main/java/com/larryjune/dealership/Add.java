package com.larryjune.dealership;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Vehicle;

public class Add {
    @FXML
    private FlowPane carItems;

    @FXML
    private Button vehicle;
    @FXML
    private Button employee;

    public void initialize() {
        // TODO: Fetch all vehicle items from the database
        try {
            List<Vehicle> vehicleData = DBControl.fetchVehicleData();
            carItems.getChildren().add(new Label("Successfully fetched vehicles (TODO: UI)"));
        } catch (Exception e) {
            carItems.getChildren().add(
                new Label("Failed to fetch vehicles (unimplemented)")
            );
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    private void addVehicle(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/larryjune/dealership/Car_Maker.fxml")
        );

        Stage stage = loader.load();
        stage.show();
    }

    @FXML
    private void addEmployee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/larryjune/dealership/AddEmployee.fxml")
        );

        Stage stage = loader.load();
        stage.show();
    }
}


