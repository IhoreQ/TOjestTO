module pl.ibobek.chowmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens pl.ibobek.chowmaker to javafx.fxml;
    exports pl.ibobek.chowmaker;
}