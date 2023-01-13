package pl.ibobek.chowmaker.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.ibobek.chowmaker.models.Ingredient;
import pl.ibobek.chowmaker.models.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RecipeRepository extends Repository {

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
}
