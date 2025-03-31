package edu.au.cpsc.module4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightScheduleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FlightScheduleApplication.class.getResource("flightScheduleView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 500);
        stage.setTitle("Module 4: Flight Schedule App");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
