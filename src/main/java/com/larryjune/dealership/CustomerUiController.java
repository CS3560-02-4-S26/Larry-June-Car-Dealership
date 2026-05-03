package com.larryjune.dealership;

import com.larryjune.dealership.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerUiController {

    @FXML
    private TabPane customerTabPane;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label accountIdLabel;

    @FXML
    private TextField accountFirstNameField;

    @FXML
    private TextField accountLastNameField;

    @FXML
    private TextField accountEmailField;

    @FXML
    private TextField accountPhoneField;

    @FXML
    private TextArea accountAddressArea;

    @FXML
    private PasswordField accountNewPasswordField;

    @FXML
    private PasswordField accountConfirmPasswordField;

    @FXML
    private Label accountProfileStatusLabel;

    @FXML
    private TilePane inventoryGrid;

    @FXML
    private TextField searchField;

    @FXML
    private TextField minPriceField;

    @FXML
    private TextField maxPriceField;

    @FXML
    private ComboBox<String> accidentFilterCombo;

    @FXML
    private Label inventoryStatusLabel;

    @FXML
    private ListView<String> purchasesList;

    @FXML
    private ComboBox<Sale> purchasePickCombo;

    @FXML
    private TextField paymentAmountField;

    @FXML
    private Label paymentHintLabel;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<String> appointmentTypeCombo;

    @FXML
    private Label appointmentStatusLabel;

    @FXML
    private ListView<String> appointmentsListView;

    @FXML
    private ListView<Vehicle> leasedVehiclesList;

    @FXML
    private TextArea leaseReturnNotes;

    @FXML
    private Label leaseReturnStatusLabel;

    private final ObservableList<Sale> customerSales = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        refreshWelcomeBanner();

        customerTabPane.getSelectionModel().selectedItemProperty().addListener((obs, previous, selected) -> {
            if (selected instanceof Tab tab && "My account".equals(tab.getText())) {
                populateAccountFormFromDatabase(true);
            }
        });

        accidentFilterCombo.setItems(FXCollections.observableArrayList(
                "Any",
                "No accident history on file",
                "Accident history reported"
        ));
        accidentFilterCombo.getSelectionModel().selectFirst();

        appointmentTypeCombo.setItems(FXCollections.observableArrayList(
                "Test Drive",
                "Financing Consultation",
                "Vehicle Inquiry",
                "Service Follow-up",
                "Trade-in Evaluation",
                "Purchase Discussion",
                "Final Paperwork",
                "Warranty Consultation",
                "Vehicle Pickup",
                "Lease return"
        ));

        configureSaleComboBox();
        leasedVehiclesList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Vehicle item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getYear() + " " + item.getMake() + " " + item.getModel()
                            + " · " + item.getCarStatus());
                }
            }
        });
        loadPurchasesAndLeaseLists();
        refreshAppointmentsList();
        handleApplyInventoryFilters();

        paymentHintLabel.setText(
                "Staff will confirm payment against your financing agreement. This button only sends a request."
        );

        populateAccountFormFromDatabase(true);
    }

    private void refreshWelcomeBanner() {
        Account session = CustomerSession.getLoggedIn();
        if (session != null) {
            welcomeLabel.setText("Welcome, " + session.getFirstName() + " " + session.getLastName());
        } else {
            welcomeLabel.setText("Welcome");
        }
    }

    /**
     * Reloads account fields from the database. When resetFeedback is true, clears any prior status message
     * (used when opening the tab). After a successful save, pass false so a success message can stay visible.
     */
    private void populateAccountFormFromDatabase(boolean resetFeedback) {
        if (resetFeedback) {
            accountProfileStatusLabel.setText("");
            accountProfileStatusLabel.setStyle("-fx-text-fill: #555555;");
        }
        if (accountNewPasswordField != null) {
            accountNewPasswordField.clear();
        }
        if (accountConfirmPasswordField != null) {
            accountConfirmPasswordField.clear();
        }

        Account session = CustomerSession.getLoggedIn();
        if (session == null) {
            accountIdLabel.setText("");
            accountFirstNameField.clear();
            accountLastNameField.clear();
            accountEmailField.clear();
            accountPhoneField.clear();
            accountAddressArea.clear();
            accountProfileStatusLabel.setStyle("-fx-text-fill: #555555;");
            accountProfileStatusLabel.setText("Sign in to manage your account.");
            return;
        }

        try {
            ArrayList<Account> rows = DBControl.fetchAccountsAt(
                    "accountID", Integer.toString(session.getAccountID()));
            if (rows.isEmpty()) {
                accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                accountProfileStatusLabel.setText("Could not load account from the database.");
                return;
            }
            Account a = rows.get(0);
            CustomerSession.setLoggedIn(a);

            accountIdLabel.setText("Account #" + a.getAccountID());
            accountFirstNameField.setText(a.getFirstName());
            accountLastNameField.setText(a.getLastName());
            accountEmailField.setText(a.getEmail());
            accountPhoneField.setText(a.getPhoneNum());
            accountAddressArea.setText(a.getShippingAddress());
        } catch (Exception e) {
            e.printStackTrace();
            accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
            accountProfileStatusLabel.setText("Could not load account. Check your connection.");
        }
    }

    @FXML
    private void handleSaveAccountDetails() {
        Account session = CustomerSession.getLoggedIn();
        if (session == null) {
            accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
            accountProfileStatusLabel.setText("You are not signed in.");
            return;
        }

        String first = trimOrEmpty(accountFirstNameField.getText());
        String last = trimOrEmpty(accountLastNameField.getText());
        String email = trimOrEmpty(accountEmailField.getText());
        String phone = trimOrEmpty(accountPhoneField.getText());
        String address = accountAddressArea.getText() == null ? "" : accountAddressArea.getText().trim();

        if (first.isEmpty() || last.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            accountProfileStatusLabel.setText("Please fill in every field except password (unless changing it).");
            accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
            return;
        }
        if (!email.contains("@")) {
            accountProfileStatusLabel.setText("Enter a valid email address.");
            accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
            return;
        }

        String newPw = accountNewPasswordField.getText() == null ? "" : accountNewPasswordField.getText();
        String confirmPw = accountConfirmPasswordField.getText() == null ? "" : accountConfirmPasswordField.getText();

        if (!newPw.isBlank() || !confirmPw.isBlank()) {
            if (!newPw.equals(confirmPw)) {
                accountProfileStatusLabel.setText("New password and confirmation must match.");
                accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                return;
            }
            if (newPw.length() < 4) {
                accountProfileStatusLabel.setText("Password must be at least 4 characters.");
                accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
                return;
            }
        }

        int accountId = session.getAccountID();

        try {
            DBControl.updateAccount(accountId, "firstName", first);
            DBControl.updateAccount(accountId, "lastName", last);
            DBControl.updateAccount(accountId, "email", email);
            DBControl.updateAccount(accountId, "phone", phone);
            DBControl.updateAccount(accountId, "shippingAddress", address);

            if (!newPw.isBlank()) {
                DBControl.updateAccount(accountId, "accountPassword", newPw);
            }

            ArrayList<Account> refreshed = DBControl.fetchAccountsAt("accountID", Integer.toString(accountId));
            if (!refreshed.isEmpty()) {
                CustomerSession.setLoggedIn(refreshed.get(0));
            }
            refreshWelcomeBanner();
            populateAccountFormFromDatabase(false);
            accountProfileStatusLabel.setStyle("-fx-text-fill: #2e7d32; -fx-font-weight: bold;");
            accountProfileStatusLabel.setText("Your profile was updated.");
        } catch (Exception e) {
            e.printStackTrace();
            accountProfileStatusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
            accountProfileStatusLabel.setText("Could not save. That email may already be in use, or the database is unreachable.");
        }
    }

    private static String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private void configureSaleComboBox() {
        purchasePickCombo.setItems(customerSales);
        purchasePickCombo.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Sale item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : formatSaleOneLine(item));
            }
        });
        purchasePickCombo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Sale item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : formatSaleOneLine(item));
            }
        });
    }

    private static String formatSaleOneLine(Sale s) {
        Vehicle v = s.getVehicle();
        return v.getYear() + " " + v.getMake() + " " + v.getModel()
                + " · Sale #" + s.getSaleID()
                + " · Paid $" + (int) s.getSaleAmount();
    }

    private void loadPurchasesAndLeaseLists() {
        Account session = CustomerSession.getLoggedIn();
        purchasesList.getItems().clear();
        customerSales.clear();
        leasedVehiclesList.getItems().clear();

        if (session == null) {
            purchasesList.getItems().add("Sign in to see your purchases.");
            return;
        }

        try {
            ArrayList<Sale> sales = DBControl.fetchSalesAt(
                    "customerAccountID",
                    Integer.toString(session.getAccountID()),
                    "="
            );
            customerSales.setAll(sales);

            if (sales.isEmpty()) {
                purchasesList.getItems().add("No purchases on file yet.");
            } else {
                for (Sale s : sales) {
                    purchasesList.getItems().add(formatSaleDetail(s));
                }
            }

            List<Vehicle> leased = sales.stream()
                    .map(Sale::getVehicle)
                    .filter(v -> v.getCarStatus() != null
                            && v.getCarStatus().toLowerCase(Locale.ROOT).contains("lease"))
                    .collect(Collectors.toList());
            leasedVehiclesList.getItems().setAll(leased);

            if (leased.isEmpty()) {
                leaseReturnStatusLabel.setText("No active leases matched to your account from inventory status.");
            } else {
                leaseReturnStatusLabel.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            purchasesList.getItems().add("Could not load purchases.");
        }
    }

    private static String formatSaleDetail(Sale s) {
        Vehicle v = s.getVehicle();
        return "Sale #" + s.getSaleID() + " · "
                + v.getYear() + " " + v.getMake() + " " + v.getModel()
                + " · $" + (int) s.getSaleAmount()
                + " · " + s.getSaleDate();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        CustomerSession.clear();
        Parent root = FXMLLoader.load(getClass().getResource("/com/larryjune/dealership/LoginScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 1000, 900));
        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    private void handleApplyInventoryFilters() {
        try {
            List<Vehicle> filtered = filterInventory(DBControl.fetchVehicleData());
            inventoryGrid.getChildren().clear();
            inventoryStatusLabel.setText(filtered.size() + " vehicle(s) match your filters.");

            if (filtered.isEmpty()) {
                inventoryGrid.getChildren().add(new Label("No vehicles match. Try clearing filters."));
                return;
            }

            FXMLLoader loader;
            for (Vehicle vehicle : filtered) {
                loader = new FXMLLoader(getClass().getResource("/com/larryjune/dealership/CarItem.fxml"));
                VBox carItem = loader.load();
                CarItemController controller = loader.getController();
                controller.setInfo(vehicle);
                inventoryGrid.getChildren().add(carItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            inventoryGrid.getChildren().clear();
            inventoryGrid.getChildren().add(new Label("Could not load inventory."));
            inventoryStatusLabel.setText("");
        }
    }

    private List<Vehicle> filterInventory(ArrayList<Vehicle> all) throws Exception {
        String q = searchField.getText() == null ? "" : searchField.getText().trim().toLowerCase(Locale.ROOT);
        Integer minPrice = parseInteger(minPriceField.getText());
        Integer maxPrice = parseInteger(maxPriceField.getText());
        final String accidentChoice =
                accidentFilterCombo.getSelectionModel().getSelectedItem() == null
                        ? "Any"
                        : accidentFilterCombo.getSelectionModel().getSelectedItem();

        Set<Integer> withAccidents = accidentChoice.equals("Any") ? null : loadVehicleIdsWithAccidents();

        return all.stream()
                .filter(v -> matchesSearch(v, q))
                .filter(v -> matchesPrice(v, minPrice, maxPrice))
                .filter(v -> matchesAccident(v.getVehicleID(), accidentChoice, withAccidents))
                .collect(Collectors.toList());
    }

    private static Integer parseInteger(String raw) {
        if (raw == null) {
            return null;
        }
        String t = raw.trim();
        if (t.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(t.replace(",", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static boolean matchesSearch(Vehicle v, String q) {
        if (q.isEmpty()) {
            return true;
        }
        String blob = (v.getMake() + " " + v.getModel()).toLowerCase(Locale.ROOT);
        return blob.contains(q);
    }

    private static boolean matchesPrice(Vehicle v, Integer min, Integer max) {
        int price = (int) v.getPrice();
        if (min != null && price < min) {
            return false;
        }
        if (max != null && price > max) {
            return false;
        }
        return true;
    }

    private static boolean matchesAccident(int vehicleId, String accidentChoice, Set<Integer> withAccidents) {
        if ("Any".equals(accidentChoice) || withAccidents == null) {
            return true;
        }
        boolean has = withAccidents.contains(vehicleId);
        if ("No accident history on file".equals(accidentChoice)) {
            return !has;
        }
        if ("Accident history reported".equals(accidentChoice)) {
            return has;
        }
        return true;
    }

    private Set<Integer> loadVehicleIdsWithAccidents() throws Exception {
        HashSet<Integer> ids = new HashSet<>();
        for (Accident a : DBControl.fetchAccidents()) {
            ids.add(a.getVehicle().getVehicleID());
        }
        return ids;
    }

    @FXML
    private void handleClearInventoryFilters() {
        searchField.clear();
        minPriceField.clear();
        maxPriceField.clear();
        accidentFilterCombo.getSelectionModel().selectFirst();
        handleApplyInventoryFilters();
    }

    @FXML
    private void handleSubmitPaymentRequest() {
        Sale selected = purchasePickCombo.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Choose a purchase from the list.").showAndWait();
            return;
        }
        String amtRaw = paymentAmountField.getText() == null ? "" : paymentAmountField.getText().trim();
        if (amtRaw.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Enter a payment amount.").showAndWait();
            return;
        }
        double amount;
        try {
            amount = Double.parseDouble(amtRaw.replace(",", ""));
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Enter a valid amount.").showAndWait();
            return;
        }
        if (amount <= 0) {
            new Alert(Alert.AlertType.WARNING, "Amount must be greater than zero.").showAndWait();
            return;
        }

        Vehicle v = selected.getVehicle();
        new Alert(Alert.AlertType.INFORMATION,
                "Payment request submitted for $" + String.format(Locale.US, "%.2f", amount)
                        + " toward your "
                        + v.getYear() + " " + v.getMake() + " " + v.getModel()
                        + ". A salesperson will confirm and post it to your account."
        ).showAndWait();
        paymentAmountField.clear();
    }

    @FXML
    private void handleScheduleAppointment() {
        Account session = CustomerSession.getLoggedIn();
        if (session == null) {
            appointmentStatusLabel.setText("Session expired. Please log in again.");
            return;
        }
        if (appointmentDatePicker.getValue() == null) {
            appointmentStatusLabel.setText("Pick an appointment date.");
            return;
        }
        String type = appointmentTypeCombo.getSelectionModel().getSelectedItem();
        if (type == null || type.isBlank()) {
            appointmentStatusLabel.setText("Pick an appointment type.");
            return;
        }

        try {
            ArrayList<Employee> employees = DBControl.fetchEmployee();
            if (employees.isEmpty()) {
                appointmentStatusLabel.setText("No staff accounts available to assign. Try again later.");
                return;
            }

            Date sqlDate = Date.valueOf(appointmentDatePicker.getValue());
            Appointment newAppointment = new Appointment(
                employees.getFirst(), (Customer) CustomerSession.getLoggedIn(), Date.valueOf(appointmentDatePicker.getValue()), type
            );

            DBControl.InsertAppointment(newAppointment);

            appointmentStatusLabel.setText("Appointment booked for " + sqlDate + ".");
            appointmentDatePicker.setValue(null);
            refreshAppointmentsList();
        } catch (Exception e) {
            e.printStackTrace();
            appointmentStatusLabel.setText("Could not save appointment.");
        }
    }

    private void refreshAppointmentsList() {
        appointmentsListView.getItems().clear();
        Account session = CustomerSession.getLoggedIn();
        if (session == null) {
            return;
        }

        try {
            ArrayList<Appointment> appointments = DBControl.fetchAppointments();
            for (Appointment appointment : appointments) {
                String line = appointment.getAppointmentDate().toString()
                        + " · " + appointment.getTypeOfAppointment()
                        + " · Staff #" + appointment.getEmployeeAccountID().getAccountID();
                appointmentsListView.getItems().add(line);
            }
            if (appointmentsListView.getItems().isEmpty()) {
                appointmentsListView.getItems().add("No appointments scheduled yet.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            appointmentsListView.getItems().add("Could not load appointments.");
        }
    }

    @FXML
    private void handleLeaseReturnRequest() {
        Vehicle selected = leasedVehiclesList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            leaseReturnStatusLabel.setText("Select a leased vehicle from the list.");
            return;
        }
        String notes = leaseReturnNotes.getText() == null ? "" : leaseReturnNotes.getText().trim();
        new Alert(Alert.AlertType.INFORMATION,
                "Return request sent for your "
                        + selected.getYear() + " " + selected.getMake() + " " + selected.getModel()
                        + ". "
                        + (notes.isEmpty() ? "Staff will contact you to confirm drop-off."
                        : "Notes: " + notes)
        ).showAndWait();
        leaseReturnNotes.clear();
        leaseReturnStatusLabel.setText("Request logged for staff follow-up.");
    }
}
