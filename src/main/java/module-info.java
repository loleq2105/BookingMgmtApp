module io.github.loleq2105.bookingmgmtapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens io.github.loleq2105.bookingmgmtapp to javafx.fxml;
    exports io.github.loleq2105.bookingmgmtapp;
    exports io.github.loleq2105.bookingmgmtapp.controller;
    opens io.github.loleq2105.bookingmgmtapp.controller to javafx.fxml;
}