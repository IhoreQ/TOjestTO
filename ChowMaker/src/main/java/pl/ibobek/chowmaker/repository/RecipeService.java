package pl.ibobek.chowmaker.repository;

import javafx.util.Pair;
import pl.ibobek.chowmaker.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public interface RecipeService {

    Pair<Boolean, String> addRecipe(ArrayList<Integer> ingredientsID, String name, String description);
}
