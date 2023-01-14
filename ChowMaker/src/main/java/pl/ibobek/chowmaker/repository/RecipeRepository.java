package pl.ibobek.chowmaker.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import pl.ibobek.chowmaker.models.Ingredient;
import pl.ibobek.chowmaker.models.Recipe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeRepository extends Repository implements RecipeService {

    public ObservableList<Recipe> getRecipesByIngredients(ArrayList<Integer> ingredientsIDs) {

        ObservableList<Recipe> recipes = FXCollections.observableArrayList();

        Connection connection = database.getConnection();

        try {
            String query = String.format(
                    "SELECT DISTINCT recipe_name, description FROM recipes JOIN recipes_ingredients ri on recipes.id_recipe = ri.id_recipe WHERE ri.id_recipe IN (SELECT DISTINCT id_recipe FROM recipes_ingredients AS ri WHERE id_recipe NOT IN (SELECT DISTINCT id_recipe FROM recipes_ingredients WHERE id_ingredient NOT IN (%s)))",
                            ingredientsIDs.stream()
                            .map(v -> "?")
                            .collect(Collectors.joining(", ")));

            PreparedStatement statement = connection.prepareStatement(query);

            int index = 1;
            for (Integer id : ingredientsIDs) {
                statement.setInt(index++, id);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String recipeName = resultSet.getString("recipe_name");
                String description = resultSet.getString("description");

                recipes.add(new Recipe(recipeName, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    @Override
    public Pair<Boolean, String> addRecipe(ArrayList<Integer> ingredientsID, String name, String description) {

        Connection connection = database.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM recipes WHERE recipe_name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return new Pair<>(false, "The recipe with this name already exists!");

            statement = connection.prepareStatement("INSERT INTO recipes (recipe_name, description) VALUES (?,?)");
            statement.setString(1, name);
            statement.setString(2, description);
            statement.executeUpdate();

            statement = connection.prepareStatement("SELECT * FROM recipes WHERE recipe_name = ?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            int recipeID = -1;

            if (resultSet.next()) {
                recipeID = resultSet.getInt("id_recipe");
            }

            if (recipeID == -1)
                return new Pair<>(false, "Error during adding process!");

            for (Integer ingredientID : ingredientsID) {
                statement = connection.prepareStatement("INSERT INTO recipes_ingredients (id_recipe, id_ingredient) VALUES (?,?)");
                statement.setInt(1, recipeID);
                statement.setInt(2, ingredientID);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Pair<>(true, "Recipe added.");
    }
}
