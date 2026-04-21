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

    private static final String ManagerUsername = "YunoMiles";
    private static final String ManagerPassword = "676921";

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/com/larryjune/dealership/MainScreen.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 900));
        stage.setTitle("Larry June Dealership");
        stage.show();
    }

   @FXML
private void handleSubmitLogin(ActionEvent event) throws IOException {
    String username = usernameField.getText().trim();
    String password = passwordField.getText().trim();

    System.out.println("Username entered: [" + username + "]");
    System.out.println("Password entered: [" + password + "]");

    if (username.equals(ManagerUsername) && password.equals(ManagerPassword)) {
        System.out.println("Login matched");

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

}


