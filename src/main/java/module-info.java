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

    opens io.github.loleq2105.bookingmgmtapp to javafx.fxml;
    exports io.github.loleq2105.bookingmgmtapp;
}