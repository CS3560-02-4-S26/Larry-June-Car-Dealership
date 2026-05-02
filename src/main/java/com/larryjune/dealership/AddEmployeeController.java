package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddEmployeeController {
    @FXML private Stage addEmployeeStage;
    @FXML private TextField accountId;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private TextField shippingAddress;
    @FXML private TextField salesPerMonth;
    @FXML private TextField password;

    @FXML
    private void confirmAction(ActionEvent event) {
        try
        {
            if(
                accountId.getText().isBlank() ||
                firstName.getText().isBlank() ||
                lastName.getText().isBlank() ||
                email.getText().isBlank() ||
                phoneNumber.getText().isBlank() ||
                shippingAddress.getText().isBlank() ||
                salesPerMonth.getText().isBlank() ||
                password.getText().isBlank() ){
                    
                    showError("Error", "Missing fields", "Please fill in all fields.");
                    return;
                }



            int id = Integer.parseInt(accountId.getText());
            String empFirstName = firstName.getText();
            String empLastName = lastName.getText();
            String empEmail = email.getText();
            String empPhoneNumber = phoneNumber.getText();
            String empShippingAddress = shippingAddress.getText();
            String empPassword = password.getText();
            double empSalesPerMonth = Double.parseDouble(salesPerMonth.getText());
            DBControl.InsertEmployee(new Employee(id, empFirstName, empLastName, empEmail, empPhoneNumber, empShippingAddress, empSalesPerMonth, empPassword));

            Stage stage = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/ManagerUi.fxml")
            );
            stage.show();
        }
        catch(Exception err)
        {
            err.printStackTrace();
            showError("Error","Error","One of your values is either null, or is inputed incorrectly");
        }
        

    }
    @FXML
    private void exitAction(ActionEvent event) {
        addEmployeeStage.close();
    }
    
    private void showError(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

}