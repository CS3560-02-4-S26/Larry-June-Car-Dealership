package com.larryjune.dealership;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Vehicle;

public class Add {

    @FXML
    private Button vehicle;
    @FXML
    private Button employee;

    @FXML
    private void addVehicle(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/larryjune/dealership/Car_Maker.fxml")
        );
        Parent root = loader.load();

        Scene scene = new Scene(root, 1000, 900);
        stage.setTitle("Larry June Dealership");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addEmployee(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/larryjune/dealership/AddEmployee.fxml")
        );
        Parent root = loader.load();

        Scene scene = new Scene(root, 1000, 900);
        stage.setTitle("Larry June Dealership");
        stage.setScene(scene);
        stage.show();
    }
}


