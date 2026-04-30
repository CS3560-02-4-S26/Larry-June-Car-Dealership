package com.larryjune.dealership;

import java.io.IOException;
import java.util.ArrayList;
import com.larryjune.dealership.model.Account;
import com.larryjune.dealership.model.DBControl;
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

        Scene scene = loader.load();  // ✅ NOW matches your <Scene> root

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);        // ✅ no new Scene() needed
        stage.setTitle("Larry June Dealership");
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
        statusLabel.setText("Could not open main screen: " + e.getMessage());
    }
}


   
   //Handles submit button to check if Username and Password correct
   //If not print error into Terminal 
   
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
        //Checks DB for account 
        try{
           ArrayList<Account> accounts = DBControl.fetchAccountsAt("email", email);

           if(accounts.isEmpty()){
            statusLabel.setText("Invalid email");
            return;
           }

           Account account = accounts.get(0);

           if(account.getPassword().equals(password)){
            statusLabel.setText("Login Successful");

            //change later to Customer Ui when finished!!!
            Parent root = FXMLLoader.load(getClass().getResource("/com/larryjune/dealership/ManagerUi.fxml"));
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 900));
            stage.setTitle("User Dashboard");
            stage.show();
           }else{
            statusLabel.setText("Invalid password");
           }

           } catch(Exception e){
            e.printStackTrace();
            statusLabel.setText("Login Failed");
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


