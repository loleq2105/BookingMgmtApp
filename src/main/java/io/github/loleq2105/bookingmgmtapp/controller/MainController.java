package io.github.loleq2105.bookingmgmtapp.controller;

import io.github.loleq2105.bookingmgmtapp.BookingMgmtApplication;
import io.github.loleq2105.bookingmgmtapp.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane viewAnchorPane;

    @FXML
    private Button bookingButton;

    @FXML
    private Button roomsButton;

    @FXML
    private Button guestsButton;

    @FXML
    private Button exitButton;

    @FXML
    private void onBooking() {
        switchView(View.BOOKING);
    }

    @FXML
    private void onRooms() {
        switchView(View.ROOMS);
    }

    @FXML
    private void onGuests() {
        // Implement the switch to the guests view when available
    }

    @FXML
    private void onExit() {
        //Exit the application
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void switchView(View view) {
        try {
            Parent root = FXMLLoader.load(BookingMgmtApplication.class.getResource(view.getFileName()));
            viewAnchorPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchView(View.BOOKING);
    }
}