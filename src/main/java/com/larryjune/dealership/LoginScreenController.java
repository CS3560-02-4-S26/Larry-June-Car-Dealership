package com.larryjune.dealership;

import java.io.IOException;
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
    private TextField usernameField;
     @FXML
    private PasswordField passwordField;
    
    //Hardcoded credentials for the Manager PW and Username
    private static final String ManagerUsername = "YunoMiles";
    private static final String ManagerPassword = "676921";

    @FXML
    //Handles the back button to return to main screen in Login Page
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/MainScreen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 900));
        stage.setTitle("Larry June Dealership");
        stage.show();
    }

   @FXML
   //Handles submit button to check if Username and Password correct
   //If not print error into Terminal 
private void handleSubmitLogin(ActionEvent event) throws IOException {
    String username = usernameField.getText().trim();
    String password = passwordField.getText().trim();
    //Includes the inputed Username PW to the terminal 
    System.out.println("Username entered: [" + username + "]");
    System.out.println("Password entered: [" + password + "]");
    
    //If corret print 
    if (username.equals(ManagerUsername) && password.equals(ManagerPassword)) {
        System.out.println("Login matched");
            //Load the Manager UI if correct PW and Username 
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/ManagerUi.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 900));
        stage.setTitle("Manager UI");
        stage.show();

    } else {
        System.out.println("Invalid username or password");
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
    
    
}

}


