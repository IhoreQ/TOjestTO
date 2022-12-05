package pl.ibobek.virus_simulation.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimulationApplication.class.getResource("simulation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 675);
        stage.setTitle("Virus Simulation");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}