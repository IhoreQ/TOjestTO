module pl.ibobek.chowmaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.ibobek.chowmaker to javafx.fxml;
    exports pl.ibobek.chowmaker;
}