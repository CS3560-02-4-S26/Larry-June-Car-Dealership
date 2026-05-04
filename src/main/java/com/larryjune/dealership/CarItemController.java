package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Image;
import com.larryjune.dealership.model.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.NoSuchElementException;

public class CarItemController {
    @FXML
    private VBox rootBox;

    @FXML
    private ImageView carImage;

    @FXML
    private Label carTitle;

    @FXML
    private Label carMsrp;

    @FXML
    private Label carDescription;

    public void setInfo(Vehicle vehicleInfo) {
        rootBox.setOnMouseClicked(e -> openVehicleDetail(vehicleInfo));

        javafx.scene.image.Image carImg = null;
        // Load the vehicle image on a background thread and then
        try {
            // Fetch the vehicle image URLs
            Image vehicleImage = DBControl.fetchImagesAt(
                "vehicleID", Integer.toString(vehicleInfo.getVehicleID())
            ).getFirst();

            // Load the image
            carImg = new javafx.scene.image.Image(vehicleImage.getImagePath(), true);
        } catch (NoSuchElementException e) {
            // Generally OK, no available images to show
            System.out.println("No available vehicle images for vehicleID = " + vehicleInfo.getVehicleID());
        } catch (Exception e) {
            // Some other error occurred, show the details.
            System.err.println("Failed to load image with vehicleID = " + vehicleInfo.getVehicleID());
            e.printStackTrace();
        } finally {
            if (carImg == null) {
                carImg = new javafx.scene.image.Image(
                    getClass().getResource("/com/larryjune/dealership/NoImageAvailable.png").toString(),
                    true
                );
            }

            carImage.setImage(carImg);
        }

        carTitle.setText(
            vehicleInfo.getYear() + " " + vehicleInfo.getMake() + " " +
            vehicleInfo.getModel()
        );

        carMsrp.setText("MSRP: $" + vehicleInfo.getPrice());
        carDescription.setText(MiscUtilities.mapCarStatus(vehicleInfo.getCarStatus()));
    }

    private void openVehicleDetail(Vehicle vehicle) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/larryjune/dealership/VehicleDetail.fxml"));
            Parent detailRoot = loader.load();
            VehicleDetailController ctrl = loader.getController();
            ctrl.setVehicle(vehicle);

            Stage stage = new Stage();
            Scene scene = rootBox.getScene();
            if (scene != null && scene.getWindow() instanceof Stage owner) {
                stage.initOwner(owner);
                stage.initModality(Modality.WINDOW_MODAL);
            }
            stage.setTitle(vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel());
            stage.setScene(new Scene(detailRoot));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setImage(String url) {
        this.carImage = new ImageView(url);
    }
}
