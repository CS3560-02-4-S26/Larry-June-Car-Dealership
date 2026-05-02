package com.larryjune.dealership;

import com.larryjune.dealership.model.Vehicle;
import com.larryjune.dealership.model.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    private final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resource){
        
        makeModelColumn.setCellValueFactory(new PropertyValueFactory<>("makeModel"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        vinColumn.setCellValueFactory(new PropertyValueFactory<>("vinNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("carStatus"));
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        styleStatusColumn();

        loadVehicleData();


    }
    //fetches vehicle info from database
    private void loadVehicleData(){
        String sql = "SELECT * FROM vehicleData";

        try (Connection conn = DBConnection.connect();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

            vehicleList.clear();

             while (rs.next()) {
            Vehicle v = new Vehicle(
                    rs.getInt("vehicleID"),
                    rs.getString("vinNumber"),
                    rs.getDouble("price"),
                    rs.getString("maker"),
                    rs.getString("model"),
                    rs.getString("color"),
                    rs.getInt("modelYear"),
                    rs.getString("bodyStyle"),
                    rs.getBoolean("isUsed"),
                    rs.getInt("mileage"),
                    mapCarStatus(rs.getString("carStatus")),
                    rs.getInt("prevOwnerCount")
            );

            vehicleList.add(v);
        }
        vehicleTable.setItems(vehicleList);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String mapCarStatus(String dbStatus){
        switch(dbStatus){
            case "1":
                return "Available";

            case "2":
                return "In service";

            case "0":
                return "In repair";
            
            default:
                return dbStatus;

        }
    }
    
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
}
