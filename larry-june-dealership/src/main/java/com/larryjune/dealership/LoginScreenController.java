package com.larryjune.dealership;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScreenController {
    @FXML
    private void handleBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(
            getClass().getResource("/com/larryjune/dealership/MainScreen.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000,900));
            stage.setTitle("Larry June Dealership");
            stage.show();

    }
    @FXML
    private void handleSubmitLogin(ActionEvent event){
        System.out.println("Login Button pressed ");
    }
}
