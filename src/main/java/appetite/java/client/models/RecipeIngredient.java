// models/RecipeIngredient.java
package appetite.java.client.models;

public class RecipeIngredient {
    private int recipeId;
    private int ingredientId;
    private double amount;
    
    public RecipeIngredient() { }
    
    public RecipeIngredient(int recipeId, int ingredientId, double amount) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.amount = amount;
    }
    
    public int getRecipeId() { return recipeId; }
    public void setRecipeId(int recipeId) { this.recipeId = recipeId; }
    
    public int getIngredientId() { return ingredientId; }
    public void setIngredientId(int ingredientId) { this.ingredientId = ingredientId; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
