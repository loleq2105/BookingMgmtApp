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

/**
 * Controller class for the main application view.
 */
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

    /**
     * Handles the action when the booking button is clicked.
     * Switches the view to the booking view.
     */
    @FXML
    private void onBooking() {
        switchView(View.BOOKING);
    }

    /**
     * Handles the action when the rooms button is clicked.
     * Switches the view to the rooms view.
     */
    @FXML
    private void onRooms() {
        switchView(View.ROOMS);
    }

    /**
     * Handles the action when the guests button is clicked.
     * Switches the view to the guests view.
     */
    @FXML
    private void onGuests() {
        // Implement the switch to the guests view when available
    }

    /**
     * Handles the action when the exit button is clicked.
     * Exits the application.
     */
    @FXML
    private void onExit() {
        // Exit the application
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Switches the view to the specified view.
     *
     * @param view the view to switch to
     */
    private void switchView(View view) {
        try {
            Parent root = FXMLLoader.load(BookingMgmtApplication.class.getResource(view.getFileName()));
            viewAnchorPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     * Sets the initial view to the booking view.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchView(View.BOOKING);
    }
}