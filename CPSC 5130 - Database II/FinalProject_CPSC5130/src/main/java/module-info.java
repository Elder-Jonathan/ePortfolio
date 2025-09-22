module com.example.finalproject_cpsc5130 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.finalproject_cpsc5130 to javafx.fxml;
    exports com.example.finalproject_cpsc5130;
}