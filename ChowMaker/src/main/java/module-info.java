module pl.ibobek.chowmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.lang3;

    opens pl.ibobek.chowmaker to javafx.fxml;
    exports pl.ibobek.chowmaker;
}