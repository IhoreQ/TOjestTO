package pl.ibobek.chowmaker.models;

public class Ingredient {

    private int ingredientID;
    private String name;
    private String type;

    public Ingredient(int ingredientID, String name, String type) {
        this.ingredientID = ingredientID;
        this.name = name;
        this.type = type;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
