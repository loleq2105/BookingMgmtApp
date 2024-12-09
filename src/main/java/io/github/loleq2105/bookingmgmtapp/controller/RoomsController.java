package io.github.loleq2105.bookingmgmtapp.controller;

import io.github.loleq2105.bookingmgmtapp.model.dto.RoomDetails;
import io.github.loleq2105.bookingmgmtapp.model.service.BookingService;
import io.github.loleq2105.bookingmgmtapp.model.service.ServiceFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for managing the rooms view.
 */
public class RoomsController implements Initializable {

    @FXML
    private TableView<RoomDetails> roomTable;

    @FXML
    private TableColumn<RoomDetails, Number> idColumn;

    @FXML
    private TableColumn<RoomDetails, String> numberColumn;

    @FXML
    private TableColumn<RoomDetails, Number> capacityColumn;

    @FXML
    private TableColumn<RoomDetails, String> typeColumn;

    @FXML
    private TableColumn<RoomDetails, Number> priceColumn;

    private BookingService bookingService;

    /**
     * Initializes the controller class.
     * Sets up the table columns and populates the room table.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingService = ServiceFactory.createBookingService();

        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoom().getId()));
        numberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoom().getNumber()));
        capacityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoom().getCapacity()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoomType().getName()));
        priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoom().getPrice()));

        populateRoomTable();
    }

    /**
     * Populates the room table with data from the booking service.
     */
    private void populateRoomTable() {
        List<RoomDetails> rooms = bookingService.findAllRooms();
        ObservableList<RoomDetails> roomList = FXCollections.observableArrayList(rooms);
        roomTable.setItems(roomList);
    }

    /**
     * Handles the action when the add room button is clicked.
     *
     * @param event the action event
     */
    @FXML
    void onAddRoom(ActionEvent event) {
        // Implement the action for adding a room
    }

    /**
     * Handles the action when the remove room button is clicked.
     *
     * @param event the action event
     */
    @FXML
    void onRemove(ActionEvent event) {
        // Implement the action for removing a room
    }

}