package com.larryjune.dealership;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.larryjune.dealership.model.*;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenController {
    @FXML 
    private Label statusLabel;
   
  
    @FXML
    private TextField usernameField;

     @FXML
    private PasswordField passwordField;
    
    //Hardcoded credentials for the Manager PW and Username
    private static final String ManagerUsername = "YunoMiles";
    private static final String ManagerPassword = "676921";

    public void setStatusMessage(String message){
        statusLabel.setText(message);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/larryjune/dealership/MainScreen.fxml")
            );

            Scene scene = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Larry June Dealership");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Could not open main screen: " + e.getMessage());
        }
    }

   // Handles submit button to check if Username and Password correct
   // If not print error into Terminal
    @FXML
    private void handleSubmitLogin(ActionEvent event) throws IOException{

        String email = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if(email.isEmpty() || password.isEmpty()){
            statusLabel.setText("Please enter email and Password");
            return;
        }
        
        if(email.equals(ManagerUsername) && password.equals(ManagerPassword)){
            statusLabel.setText("Manager login Successful");

            Parent root = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/ManagerUi.fxml"));
            
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(new Scene(root, 1000, 900));
                stage.setTitle("Manager UI");
                stage.show();

                return;
        }

        try {
            // First, check if the account exists
            ArrayList<Account> matchingAccounts = DBControl.fetchAccountsAt("email", email);
            if (matchingAccounts.isEmpty()) {
                statusLabel.setText("Account not found.");
                return;
            }

            // Next, check the account's password
            Account account = matchingAccounts.getFirst();
            if (!passwordField.getText().equals(account.getPassword())) {
                statusLabel.setText("Invalid username or password.");
                return;
            }

            // Then, check if a customer account exists
            String accountId = Integer.toString(account.getAccountID());
            ArrayList<Customer> matchingCustomerAccounts = DBControl.fetchCustomerAt(
                "customerAccountID", accountId, "="
            );

            if (!matchingCustomerAccounts.isEmpty()) {
                statusLabel.setText("Login Successful");

                CustomerSession.setLoggedIn(matchingAccounts.getFirst());
                Parent root = FXMLLoader.load(getClass().getResource("/com/larryjune/dealership/CustomerUi.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 1000, 900));
                stage.setTitle("Customer Dashboard");
                stage.show();
            }

            // Otherwise, check if they're an employee
            ArrayList<Employee> matchingEmployeeAccounts = DBControl.fetchEmployeeAt(
                "employeeAccountID", accountId, "="
            );

            // Then, check if they're a manager. (This must be done before we check if they're an employee because
            // managers *are* employees).
            ArrayList<Manager> matchingManagerAccounts = DBControl.fetchManagersAt(
                    "managerAccountID", accountId, "="
            );

            if (!matchingManagerAccounts.isEmpty()) {
                System.out.println("Logging in manager...");
                Parent root = FXMLLoader.load(
                        getClass().getResource("/com/larryjune/dealership/ManagerUi.fxml")
                );

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 1000, 900));
                stage.setTitle("Manager UI");
                stage.show();

                return;
            }

            // Finally, check if they're an employee
            if (!matchingEmployeeAccounts.isEmpty()) {
                statusLabel.setText("Employee Login Successful");

                Parent root = FXMLLoader.load(getClass().getResource("/com/larryjune/dealership/EmployeePage.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 1000, 900));
                stage.setTitle("Employee DashBoard");
                stage.show();

                return;
            }

            // If all else fails (we shouldn't get here), set the label text
            statusLabel.setText("Account doesn't exist.");
        } catch (NoSuchElementException e) {
            statusLabel.setText("That account does not exist.");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error while logging in.");
        }
    }

    @FXML
    //handles the sign up button to go to the sign up screen 
    private void handleSignUp(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(
            getClass().getResource("/com/larryjune/dealership/SignUpUi.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000,900));
            stage.setTitle("Sign up");
            stage.show();
    
    
    // Note from backend (Henry):
    // Here is some general psudocode I wrote up that can possibly help for the login system

    // Method Header: Handle Login
    // Get the email and the password from the form
    // Fetch the email from the database since its a unqiue key now using DBControl. 
    // You can use this as a template: 
    // ArrayList<Accounts> temp = DBControl.fetchAccountsAt("email",<EMAIL>); 
    // if the temp.get(0).getPassword().equals(password) then log them in and update
    // Otherwise, just prompt them by saying login failed or invaild password
    // Also, if there is an error or exception, thats probally because the email does not exist
    // To fix that please do error handing, and tell the user invaild email
}

}


