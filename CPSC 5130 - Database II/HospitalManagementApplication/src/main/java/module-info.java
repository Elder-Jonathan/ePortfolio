module cpsc.au.cpsc5130.hospitalmanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens cpsc.au.cpsc5130.hospitalmanagementapplication to javafx.fxml;
    exports cpsc.au.cpsc5130.hospitalmanagementapplication;
}