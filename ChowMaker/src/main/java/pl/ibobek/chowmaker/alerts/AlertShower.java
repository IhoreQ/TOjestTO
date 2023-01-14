package pl.ibobek.chowmaker.alerts;

import javafx.scene.control.Alert;

public class AlertShower {

    private final Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
    private final Alert warningAlert = new Alert(Alert.AlertType.WARNING);
    private final Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private final Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private final Alert noneAlert = new Alert(Alert.AlertType.NONE);

    public void showInformationAlert(String message) {
        informationAlert.setHeaderText(null);
        informationAlert.setContentText(message);
        informationAlert.show();
    }

    public void showWarningAlert(String message) {
        warningAlert.setHeaderText(null);
        warningAlert.setContentText(message);
        warningAlert.show();
    }

    public void showConfirmationAlert(String message) {
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText(message);
        confirmationAlert.show();
    }

    public void showErrorAlert(String message) {
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.show();
    }

    public void showNoneAlert(String message) {
        noneAlert.setHeaderText(null);
        noneAlert.setContentText(message);
        noneAlert.show();
    }
}
