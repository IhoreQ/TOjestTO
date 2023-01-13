package pl.ibobek.chowmaker.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.ibobek.chowmaker.models.Ingredient;

import java.sql.*;

public class IngredientRepository extends Repository {

    public ObservableList<Ingredient> getIngredientsByType(String typeName) {
        Connection connection = database.getConnection();

        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients JOIN ingredients_types it on it.id_ingredient_type = ingredients.id_ingredient_type WHERE type_name = ?");
            statement.setString(1, typeName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id_ingredient = resultSet.getInt("id_ingredient");
                String name = resultSet.getString("name");
                String type_name = resultSet.getString("type_name");

                ingredients.add(new Ingredient(id_ingredient, name, type_name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
