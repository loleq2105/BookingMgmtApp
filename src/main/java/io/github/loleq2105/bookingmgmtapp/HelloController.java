package io.github.loleq2105.bookingmgmtapp;

import io.github.loleq2105.bookingmgmtapp.model.dao.DaoFactory;
import io.github.loleq2105.bookingmgmtapp.model.dao.GuestDao;
import io.github.loleq2105.bookingmgmtapp.model.entities.Guest;
import io.github.loleq2105.bookingmgmtapp.model.service.BookingService;
import io.github.loleq2105.bookingmgmtapp.model.service.ServiceFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableView<Guest> guestTable;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn<Guest, Number> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellDataFeatures -> new SimpleIntegerProperty(cellDataFeatures.getValue().getId()));

        TableColumn<Guest, String> nameColumn = new TableColumn<>("Imię i nazwisko");
        nameColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getName()));

        TableColumn<Guest, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getEmail()));

        TableColumn<Guest, String> phoneColumn = new TableColumn<>("Telefon");
        phoneColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getPhone()));

        guestTable.getColumns().add(idColumn);
        guestTable.getColumns().add(nameColumn);
        guestTable.getColumns().add(emailColumn);
        guestTable.getColumns().add(phoneColumn);

        GuestDao guestDao = DaoFactory.createGuestDao();

        List<Guest> guests =  guestDao.findAll();

        ObservableList<Guest> tableGuestList = FXCollections.observableList(guests);

        guestTable.setItems(tableGuestList);

    }
}