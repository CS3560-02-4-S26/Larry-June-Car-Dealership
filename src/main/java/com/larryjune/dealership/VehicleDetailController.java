package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Image;
import com.larryjune.dealership.model.Vehicle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

public class VehicleDetailController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private ImageView detailImage;

    @FXML
    private Label detailHeadline;

    @FXML
    private Label detailPrice;

    @FXML
    private VBox detailRows;

    public void setVehicle(Vehicle v) {
        detailHeadline.setText(v.getYear() + " " + v.getMake() + " " + v.getModel());
        detailPrice.setText(String.format(Locale.US, "$%,.0f", v.getPrice()));

        detailRows.getChildren().clear();
        addRow("Vehicle ID", Integer.toString(v.getVehicleID()));
        addRow("VIN", v.getVinNumber());
        addRow("Color", v.getColor());
        addRow("Body style", v.getBodyStyle());
        addRow("Condition", v.isUsed() ? "Used" : "New");
        addRow("Mileage", String.format(Locale.US, "%,d mi", v.getMileage()));
        addRow("Inventory status", v.getCarStatus());
        addRow("Previous owners", Integer.toString(v.getPreviousOwnerCount()));

        loadDetailImage(v.getVehicleID());
    }

    private void addRow(String title, String value) {
        Label titleLbl = new Label(title);
        titleLbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #444444;");
        Label valLbl = new Label(value == null || value.isBlank() ? "—" : value);
        valLbl.setWrapText(true);
        valLbl.setStyle("-fx-text-fill: #222222;");
        VBox pair = new VBox(2, titleLbl, valLbl);
        detailRows.getChildren().add(pair);
    }

    private void loadDetailImage(int vehicleId) {
        Thread t = new Thread(new Task<Boolean>() {
            @Override
            protected Boolean call() {
                try {
                    Image vehicleImage = DBControl.fetchImagesAt(
                            "vehicleID", Integer.toString(vehicleId)
                    ).getFirst();
                    Platform.runLater(() ->
                            detailImage.setImage(new javafx.scene.image.Image(vehicleImage.getImagePath())));
                } catch (IndexOutOfBoundsException e) {
                    Platform.runLater(() ->
                            detailImage.setImage(new javafx.scene.image.Image(
                                    getClass().getResource("/com/larryjune/dealership/NoImageAvailable.png").toString())));
                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() ->
                            detailImage.setImage(new javafx.scene.image.Image(
                                    getClass().getResource("/com/larryjune/dealership/NoImageAvailable.png").toString())));
                }
                return true;
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
