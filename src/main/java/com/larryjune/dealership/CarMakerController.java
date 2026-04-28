package com.larryjune.dealership;

import com.larryjune.dealership.model.Accident;
import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Vehicle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class CarMakerController {

    @FXML
    private Button vehicleConfirm;

    @FXML
    private TextField vehicleId;

    @FXML
    private TextField vehicleVinNumber;

    @FXML
    private TextField vehiclePrice;

    @FXML
    private TextField vehicleMaker;

    @FXML
    private TextField vehicleModel;

    @FXML
    private TextField vehicleColor;

    @FXML
    private TextField vehicleBuiltYear;

    @FXML
    private TextField vehicleBodyStyle;

    @FXML
    private TextField vehicleMileage;

    @FXML
    private CheckBox vehicleUsed;

    @FXML
    private ComboBox<String> vehicleStatus;

    @FXML
    private TextField previousCounter;

    @FXML
    private TextField accidentId;

    @FXML
    private DatePicker dateOfAccident;

    @FXML
    private ComboBox<String> severity;

    @FXML
    private CheckBox airBagDeployment;

    @FXML
    private TextField description;
   
   

    @FXML
    public void initialize() {
        vehicleStatus.getItems().addAll("Out of Stock", "In Service", "Sold", "Pending","Reserved","Available");
        severity.getItems().addAll("Minor","Moderate","Severe","Total Loss");
    }

    @FXML
    private void confirmVehicle(ActionEvent event) {
         System.out.println("PRESSED");
        try {
            
          
            java.sql.Date accidentDate = java.sql.Date.valueOf(dateOfAccident.getValue());

            String vin = vehicleVinNumber.getText();
            String maker = vehicleMaker.getText();
            String model = vehicleModel.getText();
            String color = vehicleColor.getText();
            String status = vehicleStatus.getValue();
            String bodyStyle = vehicleBodyStyle.getText();
            int accId = Integer.parseInt(accidentId.getText());

            String accidentSeverity = severity.getValue();
            String accidentDescription = description.getText();
            
            int mileage = Integer.parseInt(vehicleMileage.getText());
            double price = Double.parseDouble(vehiclePrice.getText());

            boolean used = vehicleUsed.isSelected();
            boolean airBagDeployed = airBagDeployment.isSelected();

            int builtYear = Integer.parseInt(vehicleBuiltYear.getText());
            int previousOwners = Integer.parseInt(previousCounter.getText());
            int vId = Integer.parseInt(vehicleId.getText());
           
            DBControl.InsertVehicle(new Vehicle(vId, vin, price, maker, model, color, builtYear, bodyStyle, used, mileage, status, previousOwners));
            DBControl.InsertAccident(new Accident(accId, vId, accidentDate, accidentSeverity, airBagDeployed, accidentDescription));
            
            //Sends the user back to the manager screen
            Parent root = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/ManagerUi.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 900));
            stage.setTitle("Manager UI");
            stage.show();
          
        } 
        catch (Exception e) 
        {
            
            showError("Error","Error","One of your values is either null, or is inputed incorrectly");
           
        }
    }
    private void showError(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}