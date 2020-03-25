package com.example.recipes;

public class RecipeItem {
    private int recipeImage;
    private String recipeTitle;
    private String recipeDesc;
    private String recipeFullDesc;

    public RecipeItem(int recipeImage, String recipeTitle, String recipeDesc, String recipeFullDesc) {
        this.recipeImage = recipeImage;
        this.recipeTitle = recipeTitle;
        this.recipeDesc = recipeDesc;
    }

    public String getRecipeFullDesc() {
        return recipeFullDesc;
    }

    public int getRecipeImage() {
        return recipeImage;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }
}
