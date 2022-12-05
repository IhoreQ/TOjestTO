module pl.ibobek.virus_simulation {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.ibobek.virus_simulation.application to javafx.fxml;
    exports pl.ibobek.virus_simulation.application;
}