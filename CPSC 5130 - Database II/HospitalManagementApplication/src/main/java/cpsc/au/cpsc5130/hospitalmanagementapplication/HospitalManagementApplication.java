package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HospitalManagementApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainLayout.fxml"));
        Scene scene = new Scene(loader.load(), 850, 850);
        primaryStage.setTitle("Hospital Management Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}