module edu.au.cpsc.module3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires commons.csv;

    requires org.controlsfx.controls;

    opens edu.au.cpsc.module3 to javafx.fxml;
    exports edu.au.cpsc.module3;
}