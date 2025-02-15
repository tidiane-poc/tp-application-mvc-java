module ma.enset.iidbcc.tpapplicationmvc {
    requires javafx.fxml;
    requires java.sql;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;

    opens ma.enset.iidbcc.tpapplicationmvc to javafx.fxml;
    opens ma.enset.iidbcc.tpapplicationmvc.entites to javafx.base;
    exports ma.enset.iidbcc.tpapplicationmvc;
    exports ma.enset.iidbcc.tpapplicationmvc.controllers;
    opens ma.enset.iidbcc.tpapplicationmvc.controllers to javafx.fxml;
}