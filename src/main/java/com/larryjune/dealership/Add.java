package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Add {
    @FXML
    private GridPane carItems;

    @FXML
    private Button vehicle;
    @FXML
    private Button employee;

    public void initialize() {
        try {
            List<Vehicle> vehicleData = DBControl.fetchVehicleData();
            Vehicle vehicle;
            for (int i = 0; i < vehicleData.size(); i++) {
                vehicle = vehicleData.get(i);

                // TODO: Improve management UI
//                try {
//                    ImageView image = new ImageView(
//                        new javafx.scene.image.Image(
//                            DBControl.fetchImagesAt("vehicleID", Integer.toString(vehicle.getVehicleID())).getFirst().getImagePath(),
//                            true
//                        )
//                    );
//
//                    carItems.add(image, 0, i);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    carItems.add(new Label("N/A"), 0, i);
//                }

                carItems.add(new Label("N/A"), 0, i);

                Label makeModel = new Label(vehicle.getMake() + " " + vehicle.getModel());
                Label modelYear = new Label(Integer.toString(vehicle.getYear()));
                Label plate = new Label(vehicle.getVinNumber());
                Label status = new Label(vehicle.getCarStatus());
                Label mileage = new Label(Integer.toString(vehicle.getMileage()));

                carItems.add(makeModel, 1, i);
                carItems.add(modelYear, 2, i);
                carItems.add(plate, 3, i);
                carItems.add(status, 4, i);
                carItems.add(mileage, 5, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            carItems.getChildren().add(
                new Label("Failed to fetch vehicles")
            );
        }
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).   getScene().getWindow();
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


