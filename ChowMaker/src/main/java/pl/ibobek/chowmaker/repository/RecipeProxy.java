package pl.ibobek.chowmaker.repository;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.text.WordUtils;

public class RecipeProxy implements RecipeService {

    RecipeRepository recipeRepository;

    public RecipeProxy(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Pair<Boolean, String> addRecipe(ArrayList<Integer> ingredientsID, String name, String description) {

        String nameRegex = "^[A-Za-z]+[A-Za-z,\\-()'\" ]{2,100}";

        if (ingredientsID.size() == 0)
            return new Pair<>(false, "None ingredients selected!");

        if (!Pattern.compile(nameRegex).matcher(name).matches())
            return new Pair<>(false, "Recipe name has been inserted wrongly!");

        name = WordUtils.capitalizeFully(name);

        return recipeRepository.addRecipe(ingredientsID, name, description);
    }
}
