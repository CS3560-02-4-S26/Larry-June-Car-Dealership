package com.larryjune.dealership;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreenController {

    public void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
            getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 900));
        stage.setTitle("Login");
        stage.show();
    }
}