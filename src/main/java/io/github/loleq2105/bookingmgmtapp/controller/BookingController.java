package io.github.loleq2105.bookingmgmtapp.controller;

import io.github.loleq2105.bookingmgmtapp.model.dto.BookingDetails;
import io.github.loleq2105.bookingmgmtapp.model.dto.BookingSortOption;
import io.github.loleq2105.bookingmgmtapp.model.entities.Booking;
import io.github.loleq2105.bookingmgmtapp.model.entities.Guest;
import io.github.loleq2105.bookingmgmtapp.model.service.BookingService;
import io.github.loleq2105.bookingmgmtapp.model.service.ServiceFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for managing the bookings view.
 */
public class BookingController implements Initializable {

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private Label guestMailLabel;

    @FXML
    private Label guestNameLabel;

    @FXML
    private Label guestPhoneLabel;

    @FXML
    private Label roomCapacityLabel;

    @FXML
    private Label roomNumberLabel;

    @FXML
    private Label roomPriceLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private ComboBox<BookingSortOption> sortByComboBox;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TableView bookingTable;

    // Define property to represent selected booking
    private SimpleObjectProperty<BookingDetails> selectedBooking = new SimpleObjectProperty<>();

    // Define property to represent the (potentially filtered and sorted) list of BookingDetails to be displayed in the table
    private SimpleObjectProperty<ObservableList<BookingDetails>> filteredAndSortedBookingDetails = new SimpleObjectProperty<>();

    // Define property to represent the list of all BookingDetails
    private SimpleObjectProperty<ObservableList<BookingDetails>> allBookingDetails = new SimpleObjectProperty<>();

    /**
     * Updates the list of all booking details.
     */
    private void updateAllBookingDetails(){
        BookingService bookingService = ServiceFactory.createBookingService();

        List<BookingDetails> bookingDetailsList = bookingService.findAllBookings();

        ObservableList<BookingDetails> bookingDetailsObservableList = FXCollections.observableArrayList(bookingDetailsList);

        allBookingDetails.set(bookingDetailsObservableList);
    }

    /**
     * Sets the details labels for the selected booking.
     *
     * @param bookingDetails the booking details
     */
    private void setDetailsLabels(BookingDetails bookingDetails){
        Guest guest = bookingDetails.getGuest();
        roomNumberLabel.setText(bookingDetails.getRoomDetails().getRoom().getNumber());
        roomTypeLabel.setText(bookingDetails.getRoomDetails().getRoomType().getName());
        roomCapacityLabel.setText(String.valueOf(bookingDetails.getRoomDetails().getRoom().getCapacity()));
        roomPriceLabel.setText(String.valueOf(bookingDetails.getRoomDetails().getRoom().getPrice()));
        guestNameLabel.setText(guest.getName());
        guestMailLabel.setText(guest.getEmail());
        guestPhoneLabel.setText(guest.getPhone());
    }

    /**
     * Handles the action when the add button is clicked.
     *
     * @param event the action event
     */
    @FXML
    void onAddButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/io/github/loleq2105/bookingmgmtapp/booking-dialog.fxml"));
            Parent root = loader.load();

            BookingDialogController controller = loader.getController();
            controller.setBookingService(ServiceFactory.createBookingService());

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Booking");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            dialogStage.setScene(new Scene(root));

            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

            Booking newBooking = controller.getBooking();
            if (newBooking != null) {
                // Handle the new booking (e.g., save it to the database and update the table)
                BookingService bookingService = ServiceFactory.createBookingService();
                bookingService.insertBooking(newBooking);
                updateAllBookingDetails();
                // Show ControlsFX notification confirming successful booking in bottom-right corner of current stage
                Window owner = ((Node) event.getSource()).getScene().getWindow();
                Notifications.create()
                        .title("Rezerwacja dodana")
                        .text("Rezerwacja została dodana do bazy danych")
                        .owner(owner)
                        .position(Pos.BOTTOM_RIGHT)
                        .showInformation();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the delete button is clicked.
     *
     * @param event the action event
     */
    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        // Show alert if no booking is selected
        if(selectedBooking.get() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText("Nie wybrano rezerwacji");
            alert.setContentText("Proszę zaznaczyć rezerwację do usunięcia");
            alert.showAndWait();
            return;
        }
        // Ask user to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie usunięcia");
        alert.setHeaderText("Czy na pewno chcesz usunąć rezerwację?");
        alert.setContentText("Rezerwacja zostanie trwale usunięta z bazy danych");
        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK){
                BookingService bookingService = ServiceFactory.createBookingService();
                bookingService.deleteBooking(selectedBooking.get().getBooking().getId());
                updateAllBookingDetails();
            }
        });
    }

    /**
     * Sorts the booking details based on the selected sort option.
     */
    private void sortBookingDetails() {
        BookingSortOption sortOption = sortByComboBox.getValue();

        if (sortOption == null) {
            System.out.println("Sort option is null");
            return;
        }

        Comparator<BookingDetails> comparator = sortOption.getComparator();

        ObservableList<BookingDetails> sortedList = FXCollections.observableArrayList(filteredAndSortedBookingDetails.get());
        System.out.println("Sorting booking details");
        FXCollections.sort(sortedList, comparator);
        filteredAndSortedBookingDetails.set(sortedList);
    }

    /**
     * Filters the booking details based on the selected date range.
     */
    private void filterBookingDetails() {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate == null && toDate == null) {
            filteredAndSortedBookingDetails.set(allBookingDetails.get());
            return;
        }

        System.out.println("Filtering booking details");

        ObservableList<BookingDetails> filteredList = FXCollections.observableArrayList();

        for (BookingDetails bookingDetails : allBookingDetails.get()) {
            LocalDate bookingStart = bookingDetails.getBooking().getStartDate();
            LocalDate bookingEnd = bookingDetails.getBooking().getEndDate();

            boolean isWithinRange = (fromDate == null || !bookingEnd.isBefore(fromDate)) &&
                    (toDate == null || !bookingStart.isAfter(toDate));

            if (isWithinRange) {
                filteredList.add(bookingDetails);
            }
        }

        filteredAndSortedBookingDetails.set(filteredList);
    }

    /**
     * Filters and sorts the booking details.
     */
    private void filterAndSortBookingDetails() {
        filterBookingDetails();
        sortBookingDetails();
    }

    /**
     * Initializes the controller class.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Create table columns
        TableColumn<BookingDetails, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellDataFeatures -> new SimpleIntegerProperty(cellDataFeatures.getValue().getBooking().getId()));
        idColumn.setSortable(false);

        TableColumn<BookingDetails, String> roomNumberColumn = new TableColumn<>("Numer pokoju");
        roomNumberColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getRoomDetails().getRoom().getNumber()));
        roomNumberColumn.setSortable(false);

        TableColumn<BookingDetails, String> guestNameColumn = new TableColumn<>("Gość");
        guestNameColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getGuest().getName()));
        guestNameColumn.setSortable(false);

        TableColumn<BookingDetails, String> fromColumn = new TableColumn<>("Od");
        fromColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getBooking().getStartDate().toString()));
        fromColumn.setSortable(false);

        TableColumn<BookingDetails, String> toColumn = new TableColumn<>("Do");
        toColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getBooking().getEndDate().toString()));
        toColumn.setSortable(false);

        bookingTable.getColumns().add(idColumn);
        bookingTable.getColumns().add(roomNumberColumn);
        bookingTable.getColumns().add(guestNameColumn);
        bookingTable.getColumns().add(fromColumn);
        bookingTable.getColumns().add(toColumn);

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

            filterAndSortBookingDetails();
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

            filterAndSortBookingDetails();
        });

        // Populate sortByComboBox with sorting options
        sortByComboBox.setItems(FXCollections.observableArrayList(BookingSortOption.values()));

        // Add listener to sortByComboBox
        sortByComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            sortBookingDetails();
        });

        bookingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBooking.set((BookingDetails) newSelection);
            }
        });

        selectedBooking.addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setDetailsLabels(newSelection);
            }
            System.out.println("Selected booking changed");
        });

        // Bind the table to the filtered list of BookingDetails
        bookingTable.itemsProperty().bind(filteredAndSortedBookingDetails);

        // Add listener to update filteredAndSortedBookingDetails when allBookingDetails changes
        allBookingDetails.addListener((obs, oldList, newList) -> {
            filteredAndSortedBookingDetails.set(newList);
            filterAndSortBookingDetails();
            System.out.println("All booking details changed");
        });

        // Update allBookingDetails to populate the table
        updateAllBookingDetails();

    }
}