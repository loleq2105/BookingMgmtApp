package io.github.loleq2105.bookingmgmtapp.controller;

import io.github.loleq2105.bookingmgmtapp.model.dto.RoomDetails;
import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;
import io.github.loleq2105.bookingmgmtapp.model.entities.Guest;
import io.github.loleq2105.bookingmgmtapp.model.service.BookingService;
import io.github.loleq2105.bookingmgmtapp.model.service.ServiceFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for managing the booking dialog.
 */
public class BookingDialogController implements Initializable {

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TableView<Guest> guestsTable;

    @FXML
    private TableColumn<Guest, Number> guestIdColumn;

    @FXML
    private TableColumn<Guest, String> guestNameColumn;

    @FXML
    private TableColumn<Guest, String> guestEmailColumn;

    @FXML
    private TableView<RoomDetails> roomsTable;

    @FXML
    private TableColumn<RoomDetails, String> roomNumberColumn;

    @FXML
    private TableColumn<RoomDetails, String> roomTypeColumn;

    @FXML
    private TableColumn<RoomDetails, Number> roomCapacityColumn;

    @FXML
    private Button addBookingButton;

    private BookingService bookingService;
    private Stage dialogStage;

    private Booking booking;

    /**
     * Populates the guests table with data from the booking service.
     */
    private void populateGuestsTable() {
        bookingService = ServiceFactory.createBookingService();
        List<Guest> guests = bookingService.findAllGuests();
        ObservableList<Guest> guestList = FXCollections.observableArrayList(guests);
        guestsTable.setItems(guestList);
    }

    /**
     * Handles the change in date pickers and updates the rooms table.
     */
    private void onDateChanged() {
        LocalDate startDate = fromDatePicker.getValue();
        LocalDate endDate = toDatePicker.getValue();

        if (startDate != null && endDate != null) {
            List<RoomDetails> rooms = bookingService.findRoomsFreeInRange(startDate, endDate);
            ObservableList<RoomDetails> roomList = FXCollections.observableArrayList(rooms);
            roomsTable.setItems(roomList);
        } else {
            roomsTable.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Handles the action when the add booking button is clicked.
     */
    @FXML
    private void onAddBooking() {
        Guest selectedGuest = guestsTable.getSelectionModel().getSelectedItem();
        RoomDetails selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
        LocalDate startDate = fromDatePicker.getValue();
        LocalDate endDate = toDatePicker.getValue();

        if (selectedGuest != null && selectedRoom != null && startDate != null && endDate != null) {
            booking = new Booking();
            booking.setGuestId(selectedGuest.getId());
            booking.setRoomId(selectedRoom.getRoom().getId());
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            dialogStage.close();
        }
    }

    /**
     * Shows the booking dialog.
     *
     * @param parentStage the parent stage
     * @return the booking created
     */
    public Booking showDialog(Stage parentStage) {
        try {
            dialogStage = new Stage();
            dialogStage.initOwner(parentStage);
            dialogStage.setTitle("Dodaj rezerwację");
            dialogStage.initModality(javafx.stage.Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("booking-dialog.fxml"))));
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return booking;
    }

    /**
     * Initializes the controller class.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guestIdColumn.setCellValueFactory(cellDataFeatures -> new SimpleIntegerProperty(cellDataFeatures.getValue().getId()));
        guestNameColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getName()));
        guestEmailColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getEmail()));

        roomNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoom().getNumber()));
        roomTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRoomType().getName()));
        roomCapacityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getRoom().getCapacity()));

        populateGuestsTable();

        // Add listeners to the date pickers
        fromDatePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                toDatePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(newDate));
                    }
                });
            } else {
                toDatePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(false);
                    }
                });
            }
            onDateChanged();
            updateAddBookingButtonState();
        });

        toDatePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                fromDatePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isAfter(newDate));
                    }
                });
            } else {
                fromDatePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(false);
                    }
                });
            }
            onDateChanged();
            updateAddBookingButtonState();
        });

        guestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateAddBookingButtonState();
        });

        roomsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            updateAddBookingButtonState();
        });

        updateAddBookingButtonState();
    }

    /**
     * Updates the state of the add booking button.
     */
    private void updateAddBookingButtonState() {
        boolean disable = fromDatePicker.getValue() == null ||
                toDatePicker.getValue() == null ||
                guestsTable.getSelectionModel().getSelectedItem() == null ||
                roomsTable.getSelectionModel().getSelectedItem() == null;
                addBookingButton.setDisable(disable);
    }

    /**
     * Sets the booking service.
     *
     * @param bookingService the booking service
     */
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Gets the booking.
     *
     * @return the booking
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     * Sets the dialog stage.
     *
     * @param dialogStage the dialog stage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}