package pl.ibobek.chowmaker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pl.ibobek.chowmaker.database.Database;
import pl.ibobek.chowmaker.models.Ingredient;
import pl.ibobek.chowmaker.models.Recipe;
import pl.ibobek.chowmaker.repository.IngredientRepository;
import pl.ibobek.chowmaker.repository.RecipeRepository;

import java.util.ArrayList;

public class ChowMakerController {

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

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    @FXML
    public void initialize() {
        ingredientRepository = new IngredientRepository();
        recipeRepository = new RecipeRepository();

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
        }
    }

    @FXML
    public void onDeleteIngredientButtonClick() {

        Ingredient ingredient = chosenIngredientsComboBox.getValue();

        if (ingredient != null) {
            ObservableList<Ingredient> ingredients = chosenIngredientsComboBox.getItems();
            ingredients.remove(ingredient);
            chosenIngredientsComboBox.setItems(ingredients);
        }
    }

    @FXML
    public void onSearchButtonClick() {

        ObservableList<Ingredient> chosenIngredients = chosenIngredientsComboBox.getItems();

        if (chosenIngredients.size() > 0) {

            ArrayList<Integer> ingredientsIDs = new ArrayList<>();

            for (Ingredient ingredient : chosenIngredients) {
                ingredientsIDs.add(ingredient.getIngredientID());
            }

            ObservableList<Recipe> recipes = recipeRepository.getRecipesByIngredients(ingredientsIDs);
            recipeComboBox.setItems(recipes);
            setFirstItem(recipeComboBox);
        } else {
            recipeComboBox.setItems(null);
        }
    }

}