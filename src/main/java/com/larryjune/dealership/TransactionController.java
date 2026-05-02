package com.larryjune.dealership;

import com.larryjune.dealership.model.Vehicle;
import com.larryjune.dealership.model.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionController {
    @FXML
    private TextField vehicleIDField;
    @FXML
    private TextField vinField;
    @FXML
    private TextField makeModelField;
    @FXML
    private TextField accountIDField;
    @FXML
    private TextField paymentAmountField;
    @FXML
    private TextField confirmAmountField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    private Vehicle selectedVehicle;


    //gets called from employee controller
    public void setVehicle(Vehicle vehicle){
        this.selectedVehicle = vehicle;

        vehicleIDField.setText(String.valueOf(vehicle.getVehicleID()));
        vinField.setText(vehicle.getVinNumber());
        makeModelField.setText(vehicle.getMakeModel());


    }

    //Confirm Transactions
    @FXML
    private void handleConfirmTransaction(){
        try{
             if (accountIDField.getText().isBlank() ||
                paymentAmountField.getText().isBlank() ||
                confirmAmountField.getText().isBlank()){
                    showAlert("Please fill in all required fields");
                    return;
                }

                double amount = Double.parseDouble(paymentAmountField.getText());
                double confirm = Double.parseDouble(confirmAmountField.getText());

                if(amount != confirm){
                    showAlert("Payment amount do not matxh");
                    return;
                }

                //Updates the vehicle status in DB
                updateVehicleStatus(selectedVehicle.getVehicleID(), "Bought");
                showAlert("Transaction successful");
                closeWindow();

        }catch(Exception e){
            e.printStackTrace();
            showAlert("Transaction failed: " + e.getMessage());
        }
    }

    //Update vehicle status
    private void updateVehicleStatus(int vehicleID, String status) throws Exception{
        String sql = "UPDATE vehicleDATA SET carStatus = ? WHERE vehicleID = ?";

        try(Connection conn = DBConnection.connect();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,mapStatusToDB(status));
            stmt.setInt(2, vehicleID);

            stmt.executeUpdate();


        }
    }

    private String mapStatusToDB(String status){
        switch(status.toLowerCase()){
            case "available": return "1";
            case "in service": return "2";
            case "in repair": return "0";
            case "bought": return "1";
            default: return "1";
        }
    }

    @FXML
    private void handleCancel(){
        closeWindow();
    }

    private void closeWindow(){
        Stage stage = (Stage) vehicleIDField.getScene().getWindow();
        stage.close();
    }

    //Alert Helper
    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transaction");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
