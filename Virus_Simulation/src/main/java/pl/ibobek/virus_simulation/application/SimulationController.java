package pl.ibobek.virus_simulation.application;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import pl.ibobek.virus_simulation.individual.Memento;
import pl.ibobek.virus_simulation.simulation.Simulation;

import java.util.ArrayList;

public class SimulationController {

    public static final long fps = 25L;
    public static final int scale = 4;
    private final int populationSize = 1000;
    @FXML
    Pane area;
    @FXML
    Button startButton;
    @FXML
    Button stopButton;
    @FXML
    Button spawnButton;
    @FXML
    Button restoreButton;
    @FXML
    ComboBox<Integer> savedTimesComboBox;
    @FXML
    ComboBox<String> casesComboBox;
    ObservableList<Integer> savedTimes = FXCollections.observableArrayList();
    ObservableList<String> initationCases = FXCollections.observableArrayList();
    Simulation simulation;
    Timer timer;

    ArrayList<Memento> populationHistory = new ArrayList<>();
    private int elapsedFrames = 0;

    private class Timer extends AnimationTimer {

        private final long interval = 1000000000L / fps;
        private long lastTime = 0;

        @Override
        public void handle(long now) {
            if (now - lastTime > interval) {
                simulation.move();
                simulation.update(area);

                if (elapsedFrames++ % 25 == 0) {
                    populationHistory.add(simulation.save());
                    savedTimes.add(elapsedFrames / (int)fps);
                }

                lastTime = now;
            }
        }
    }

    @FXML
    public void initialize() {
        savedTimesComboBox.setItems(savedTimes);
        initationCases.add("Początkowe osobniki nie posiadają odporności");
        initationCases.add("Losowe osobniki posiadają odporność");
        casesComboBox.setItems(initationCases);

        timer = new Timer();
        startButton.setDisable(true);
        stopButton.setDisable(true);
        savedTimesComboBox.setDisable(true);
        restoreButton.setDisable(true);
        spawnButton.setDisable(true);
        casesComboBox.setDisable(false);
    }

    @FXML
    public void onSpawnButtonClick() {
        timer.stop();
        area.getChildren().clear();
        if (casesComboBox.getValue().equals("Początkowe osobniki nie posiadają odporności"))
            simulation = new Simulation(area, populationSize, false);
        else
            simulation = new Simulation(area, populationSize, true);

        elapsedFrames = 0;
        savedTimes.clear();
        startButton.setDisable(false);
        stopButton.setDisable(true);
        casesComboBox.setDisable(false);
    }

    @FXML
    public void onStopButtonClick() {
        timer.stop();
        spawnButton.setDisable(false);
        startButton.setDisable(false);
        stopButton.setDisable(true);
        savedTimesComboBox.setDisable(false);
        casesComboBox.setDisable(false);
    }

    @FXML
    public void onStartButtonClick() {
        timer.start();
        spawnButton.setDisable(true);
        stopButton.setDisable(false);
        startButton.setDisable(true);
        savedTimesComboBox.setDisable(true);
        casesComboBox.setDisable(true);
    }

    @FXML
    public void onRestoreButtonClick() {
        area.getChildren().clear();
        int snapshot = savedTimesComboBox.getValue();
        simulation.setPopulation(populationHistory.get(snapshot));
        elapsedFrames = savedTimesComboBox.getValue() * (int)fps;
        savedTimes.clear();
        for (int i = 0; i < snapshot; ++i)
            savedTimes.add(i);

        int populationHistorySize = populationHistory.size();
        for (int i = snapshot; i < populationHistorySize; ++i) {
            populationHistory.remove(snapshot);
        }
        restoreButton.setDisable(true);
    }
    @FXML
    public void onItemSelected() {
        restoreButton.setDisable(false);
    }

    @FXML
    public void onCaseSelected() {
        spawnButton.setDisable(false);
    }


}