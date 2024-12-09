package io.github.loleq2105.bookingmgmtapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class for the Booking Management Application.
 * This class extends the JavaFX Application class and serves as the entry point for the application.
 */
public class BookingMgmtApplication extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * This method is called when the application is launched.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException if the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookingMgmtApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BookingMgmtApp");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method which serves as the entry point for the application.
     * This method launches the JavaFX application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}