package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Image;
import com.larryjune.dealership.model.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.List;

public class CarItemController {
    @FXML
    private ImageView carImage;

    @FXML
    private Label carTitle;

    @FXML
    private Label carMsrp;

    @FXML
    private Label carDescription;

    public void setInfo(Vehicle vehicleInfo) {
        // TODO: Move image loading into a background thread. Loading them here slows the program down
//        try {
//            List<Image> vehicleImages = DBControl.fetchImagesAt(
//                "vehicleID", Integer.toString(vehicleInfo.getVehicleID())
//            );
//
//            carImage = new ImageView(vehicleImages.getFirst().getImagePath());
//        } catch (Exception e) {
//            System.err.println("Failed to load image with vehicleID = " + vehicleInfo.getVehicleID());
//        }
        carImage = new ImageView();

        carTitle.setText(
            vehicleInfo.getYear() + " " + vehicleInfo.getMake() + " " +
            vehicleInfo.getModel()
        );

        carMsrp.setText("MSRP: $" + vehicleInfo.getPrice());
        carDescription.setText(vehicleInfo.getCarStatus());
    }

    public void setImage(String url) {
        this.carImage = new ImageView(url);
    }
}
