// models/Recipe.java
package appetite.java.client.models;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private String data;
    private int cookingTime;
    private int servings;
    private String difficultyLevel;
    private List<String> ingredientNames;
    
    public Recipe() { }
    
    public Recipe(int id, String name, String data, int cookingTime, int servings, String difficultyLevel, List<String> ingredientNames) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.cookingTime = cookingTime;
        this.servings = servings;
        this.difficultyLevel = difficultyLevel;
        this.ingredientNames = ingredientNames;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    
    public int getCookingTime() { return cookingTime; }
    public void setCookingTime(int cookingTime) { this.cookingTime = cookingTime; }
    
    public int getServings() { return servings; }
    public void setServings(int servings) { this.servings = servings; }
    
    public String getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(String difficultyLevel) { this.difficultyLevel = difficultyLevel; }
    
    public List<String> getIngredientNames() { return ingredientNames; }
    public void setIngredientNames(List<String> ingredientNames) { this.ingredientNames = ingredientNames; }
}
