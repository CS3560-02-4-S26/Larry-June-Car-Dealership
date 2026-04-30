package com.larryjune.dealership;

import com.larryjune.dealership.model.Account;
import com.larryjune.dealership.model.Customer;
import com.larryjune.dealership.model.DBControl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.ArrayList;

public class InsertAccountController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML 
    private TextField emailField;

    @FXML
    private TextField phoneField;


    @FXML
    private PasswordField passwordField;

    @FXML 
    private PasswordField confirmPasswordField;

    @FXML 
    private TextField shippingAddressField;

    @FXML 
    private Label statusLabel;
    

    @FXML
    private void handleBack(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
    

    @FXML 
    private void handleSignUp(){
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = shippingAddressField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        //check for all completed fields 
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || 
            phone.isEmpty() || address.isEmpty()||
             password.isEmpty() || confirmPassword.isEmpty()){
                statusLabel.setText("Please complete all Fields");
                return;
            }
            //error handeling if not correct password
        if(!password.equals(confirmPassword)){
            statusLabel.setText("Password Does Not Match");
            return;
        }

        
            //Fetches from the DB if account already Created
            try{
                ArrayList<Account> existingAccounts = DBControl.fetchAccountsAt("email", email);
              
                if(!existingAccounts.isEmpty()){
                    statusLabel.setText("Ac account has already been created ");
                    return;
                }

                Customer account = new Customer(0, firstName, lastName, email, phone, address, password);
               
                //Inserting into the DB
                DBControl.InsertCustomer(account);

                FXMLLoader loader= new FXMLLoader(
                   getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml"));

                   Parent root = loader.load();
                   LoginScreenController controller = loader.getController();

                   controller.setStatusMessage("Account Successfull. Please Log in");

                   Stage stage = (Stage) firstNameField.getScene().getWindow();
                   stage.setScene(new Scene(root, 1000,900));
                   stage.setTitle("Login");
                   stage.show();
                
            }catch (Exception e){
                e.printStackTrace();
                statusLabel.setText("ERROR " + e.getMessage());
            }
   
    }

}
