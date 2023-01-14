package pl.ibobek.chowmaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public Button findRecipesButton;
    @FXML
    public Button addRecipeButton;
    @FXML
    public Button exitButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void changeToFindRecipesScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/find-recipes-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToAddRecipeScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/add-recipe-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitApplication() {
        System.exit(0);
    }
}
