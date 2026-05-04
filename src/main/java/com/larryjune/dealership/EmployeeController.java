package com.larryjune.dealership;

import com.larryjune.dealership.model.DBControl;
import com.larryjune.dealership.model.Vehicle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class EmployeeController implements Initializable {

    @FXML
    private TableView<Vehicle> vehicleTable;

    @FXML
    private TableColumn<Vehicle, String> makeModelColumn;

    @FXML
    private TableColumn<Vehicle, Integer> yearColumn;

    @FXML
    private TableColumn<Vehicle, String> vinColumn;

    @FXML
    private TableColumn<Vehicle, String> statusColumn;

    @FXML
    private TableColumn<Vehicle, Integer> mileageColumn;

    @Override
    public void initialize(URL location, ResourceBundle resource){
        
        makeModelColumn.setCellValueFactory(new PropertyValueFactory<>("makeModel"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<>("vinNumber"));
        statusColumn.setCellValueFactory(
            p -> new ReadOnlyObjectWrapper<   >(MiscUtilities.mapCarStatus(p.getValue().getCarStatus()))
        );
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        styleStatusColumn();
        loadVehicleData();
    }

    //fetches vehicle info from database
    //displayes the information in the table 
    private void loadVehicleData(){
        try {
            ArrayList<Vehicle> vehicleData = DBControl.fetchVehicleData();
            vehicleTable.setItems(FXCollections.observableArrayList(vehicleData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //styling for the table
    //color selection for each of the cells 
     private void styleStatusColumn() {
        statusColumn.setCellFactory(column -> new TableCell<Vehicle, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    switch (item.toLowerCase()) {
                        case "available":
                            setStyle("-fx-background-color: #5ca35c; -fx-text-fill: white; -fx-alignment: CENTER;");
                            break;
                        case "in service":
                            setStyle("-fx-background-color: #e6c65b; -fx-text-fill: black; -fx-alignment: CENTER;");
                            break;
                        case "in repair":
                            setStyle("-fx-background-color: #c94b4b; -fx-text-fill: white; -fx-alignment: CENTER;");
                            break;
                        default:
                            setStyle("-fx-alignment: CENTER;");
                            break;
                    }
                }
            }
        });
    }

    //button handeling for employeee UI 
    //will be used to pop up and function different buttons

    //handels the transaction
   // handles the transaction popup
@FXML
private void handleTransaction() {
    Vehicle selected = vehicleTable.getSelectionModel().getSelectedItem();

    if (selected == null) {
        showAlert("Select a vehicle first.");
        return;
    }

    try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/larryjune/dealership/EmployeeTransactionPopup.fxml")
        );

        Parent root = loader.load();

        TransactionController controller = loader.getController();
        controller.setVehicle(selected);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Transaction");
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Could not open Transaction.");
    }
}

//change when implementation is done

@FXML
private void handleAppointment(ActionEvent event){
    showAlert("Appointment feture not implemented yet");
}

private void showAlert(String message){
    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    
}





//handles the logout button back to login screen
@FXML
private void handleLogout(ActionEvent event){
    try{
     Parent root = FXMLLoader.load(getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml"));

     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     stage.setScene(new Scene(root, 1000,900));
     stage.setTitle("Login");
     stage.show();

    }catch(Exception e){
        e.printStackTrace();
        showAlert("Could not logout");
    }
}
}