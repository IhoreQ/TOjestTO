package pl.ibobek.chowmaker;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import pl.ibobek.chowmaker.alerts.AlertShower;
import pl.ibobek.chowmaker.models.Ingredient;
import pl.ibobek.chowmaker.models.Recipe;
import pl.ibobek.chowmaker.repository.IngredientRepository;
import pl.ibobek.chowmaker.repository.RecipeProxy;
import pl.ibobek.chowmaker.repository.RecipeRepository;
import pl.ibobek.chowmaker.repository.RecipeService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecipesController {

    @FXML
    public ComboBox<Ingredient> basicIngredientsComboBox;
    @FXML
    public ComboBox<Ingredient> vegetablesComboBox;
    @FXML
    public ComboBox<Ingredient> dairyComboBox;
    @FXML
    public ComboBox<Ingredient> fruitComboBox;
    @FXML
    public ComboBox<Ingredient> meatComboBox;
    @FXML
    public ComboBox<Ingredient> spicesComboBox;
    @FXML
    public ComboBox<Ingredient> saucesComboBox;
    @FXML
    public Button basicIngredientsAddButton;
    @FXML
    public Button vegetablesAddButton;
    @FXML
    public Button fruitAddButton;
    @FXML
    public Button meatAddButton;
    @FXML
    public Button dairyAddButton;
    public Button spicesAddButton;
    public Button saucesAddButton;
    @FXML
    public ComboBox<Ingredient> chosenIngredientsComboBox;
    @FXML
    public Button deleteIngredientButton;
    @FXML
    public Button searchButton;
    @FXML
    public Button chooseRecipeButton;
    @FXML
    public ComboBox<Recipe> recipeComboBox;
    @FXML
    public TextArea recipeTextArea;
    @FXML
    public Button menuReturnButton;
    @FXML
    public TextArea newRecipeDescriptionTextArea;
    @FXML
    public TextField newRecipeNameTextField;
    @FXML
    public Button addRecipeButton;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    private RecipeService recipeProxy;

    private AlertShower alertShower;

    @FXML
    public void initialize() {
        ingredientRepository = new IngredientRepository();
        recipeRepository = new RecipeRepository();
        recipeProxy = new RecipeProxy(recipeRepository);
        alertShower = new AlertShower();

        initIngredientsComboBoxes();
    }

    private void initIngredientsComboBoxes() {
        ObservableList<Ingredient> basic = ingredientRepository.getIngredientsByType("Basic");
        basicIngredientsComboBox.setItems(basic);
        setFirstItem(basicIngredientsComboBox);

        ObservableList<Ingredient> vegetables = ingredientRepository.getIngredientsByType("Vegetables");
        vegetablesComboBox.setItems(vegetables);
        setFirstItem(vegetablesComboBox);

        ObservableList<Ingredient> fruit = ingredientRepository.getIngredientsByType("Fruit");
        fruitComboBox.setItems(fruit);
        setFirstItem(fruitComboBox);

        ObservableList<Ingredient> meat = ingredientRepository.getIngredientsByType("Meat");
        meatComboBox.setItems(meat);
        setFirstItem(meatComboBox);

        ObservableList<Ingredient> dairy = ingredientRepository.getIngredientsByType("Dairy");
        dairyComboBox.setItems(dairy);
        setFirstItem(dairyComboBox);

        ObservableList<Ingredient> spices = ingredientRepository.getIngredientsByType("Spices");
        spicesComboBox.setItems(spices);
        setFirstItem(spicesComboBox);

        ObservableList<Ingredient> sauces = ingredientRepository.getIngredientsByType("Sauces");
        saucesComboBox.setItems(sauces);
        setFirstItem(saucesComboBox);
    }

    private void addToChosenIngredients(ComboBox<Ingredient> comboBox) {

        ObservableList<Ingredient> chosenIngredients = chosenIngredientsComboBox.getItems();
        Ingredient ingredient = comboBox.getValue();

        if (ingredient != null && !chosenIngredients.contains(ingredient)) {
            chosenIngredients.add(ingredient);
            chosenIngredientsComboBox.setItems(chosenIngredients);
            if (chosenIngredients.size() == 1)
                setFirstItem(chosenIngredientsComboBox);
        }

    }

    private void setFirstItem(ComboBox comboBox) {
        comboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void onBasicIngredientsAddButtonClick() {
        addToChosenIngredients(basicIngredientsComboBox);
    }

    @FXML
    public void onVegetablesAddButtonClick() {
        addToChosenIngredients(vegetablesComboBox);
    }

    @FXML
    public void onFruitAddButtonClick() {
        addToChosenIngredients(fruitComboBox);
    }

    @FXML
    public void onMeatAddButtonClick() {
        addToChosenIngredients(meatComboBox);
    }

    @FXML
    public void onDairyAddButtonClick() {
        addToChosenIngredients(dairyComboBox);
    }

    @FXML
    public void onSpicesAddButtonClick() {
        addToChosenIngredients(spicesComboBox);
    }

    @FXML
    public void onSaucesAddButtonClick() {
        addToChosenIngredients(saucesComboBox);
    }

    @FXML
    public void onChooseRecipeButtonClick() {

        Recipe recipe = recipeComboBox.getValue();

        if (recipe != null) {
            recipeTextArea.setText(recipe.getDescription());
        } else {
            alertShower.showWarningAlert("None recipe selected!");
        }
    }

    @FXML
    public void onDeleteIngredientButtonClick() {

        Ingredient ingredient = chosenIngredientsComboBox.getValue();

        if (ingredient != null) {
            ObservableList<Ingredient> ingredients = chosenIngredientsComboBox.getItems();
            ingredients.remove(ingredient);
            chosenIngredientsComboBox.setItems(ingredients);
        } else {
            alertShower.showWarningAlert("None ingredient selected!");

        }
    }

    @FXML
    public void onSearchButtonClick() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        int recipesFound = 0;
        ObservableList<Ingredient> chosenIngredients = chosenIngredientsComboBox.getItems();

        if (chosenIngredients.size() > 0) {

            ArrayList<Integer> ingredientsIDs = new ArrayList<>();

            for (Ingredient ingredient : chosenIngredients) {
                ingredientsIDs.add(ingredient.getIngredientID());
            }

            ObservableList<Recipe> recipes = recipeRepository.getRecipesByIngredients(ingredientsIDs);
            recipeComboBox.setItems(recipes);

            alertShower.showInformationAlert("Recipes found: " + recipes.size());
            setFirstItem(recipeComboBox);

        } else {
            recipeComboBox.setItems(null);
            alertShower.showWarningAlert("Ingredients list is empty!");
        }
    }

    public void returnToMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/main-menu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRecipe() {

        ArrayList<Integer> ingredientsIDs = new ArrayList<>();
        ObservableList<Ingredient> chosenIngredients = chosenIngredientsComboBox.getItems();
        String name = newRecipeNameTextField.getText();
        String description = newRecipeDescriptionTextArea.getText();

        for (Ingredient ingredient : chosenIngredients) {
            ingredientsIDs.add(ingredient.getIngredientID());
        }

        Pair<Boolean, String> result = recipeProxy.addRecipe(ingredientsIDs, name, description);

        if (result.getKey())
            alertShower.showConfirmationAlert(result.getValue());
        else
            alertShower.showWarningAlert(result.getValue());
    }
}