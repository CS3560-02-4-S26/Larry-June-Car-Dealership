package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Image;
import com.larryjune.dealership.model.Vehicle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
        // Load the vehicle image on a background thread and then
        Thread imageLoadingThread = new Thread(
            new Task<Boolean>() {
                @Override
                public Boolean call() {
                    try {
                        // Fetch the vehicle image
                        Image vehicleImage = DBControl.fetchImagesAt(
                            "vehicleID", Integer.toString(vehicleInfo.getVehicleID())
                        ).getFirst();

                        // If we reach this point, set the image back on the main UI thread
                        Platform.runLater(
                            new Task<Boolean>() {
                                @Override
                                public Boolean call() {
                                    carImage.setImage(new javafx.scene.image.Image(vehicleImage.getImagePath()));
                                    return true;
                                }
                            }
                        );
                    } catch (IndexOutOfBoundsException e) {
                        // Generally OK, no available images to show
                        System.err.println("No available vehicle images for vehicleID = " + vehicleInfo.getVehicleID());
                        Platform.runLater(
                            new Task<Void>() {
                                @Override
                                public Void call() {
                                    carImage.setImage(
                                        new javafx.scene.image.Image(
                                            getClass().getResource("/com/larryjune/dealership/NoImageAvailable.png").toString()
                                        )
                                    );

                                    return null;
                                }
                            }
                        );
                    } catch (Exception e) {
                        // Some other error occurred, show the details.
                        System.err.println("Failed to load image with vehicleID = " + vehicleInfo.getVehicleID());
                        e.printStackTrace();
                        Platform.runLater(
                            new Task<Void>() {
                                @Override
                                public Void call() {
                                    carImage.setImage(
                                        new javafx.scene.image.Image(
                                            getClass().getResource("/com/larryjune/dealership/NoImageAvailable.png").toString()
                                        )
                                    );

                                    return null;
                                }
                            }
                        );
                        return false;
                    }

                    return true;
                }
            }
        );

        imageLoadingThread.start();

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
