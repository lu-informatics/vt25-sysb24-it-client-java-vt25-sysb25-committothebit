// controllers/EditRecipeController.java
package appetite.java.client.controllers;

import appetite.java.client.models.Recipe;
import appetite.java.client.services.RecipeService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class EditRecipeViewController {
    
    private RecipeService recipeService;
    private Recipe recipe;
    
    @FXML private TextField txtName;
    @FXML private TextField txtData;
    @FXML private TextField txtCookingTime;
    @FXML private TextField txtServings;
    @FXML private TextField txtDifficulty;
    @FXML private TextField txtIngredientNames;
    
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        txtName.setText(recipe.getName());
        txtData.setText(recipe.getData());
        txtCookingTime.setText(String.valueOf(recipe.getCookingTime()));
        txtServings.setText(String.valueOf(recipe.getServings()));
        txtDifficulty.setText(recipe.getDifficultyLevel());
        if(recipe.getIngredientNames() != null) {
            txtIngredientNames.setText(String.join(", ", recipe.getIngredientNames()));
        }
    }
    
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    
   @FXML
private void handleSave() {
    // Validate recipe name: allow only letters and spaces.
    String recipeName = txtName.getText().trim();
    if (!recipeName.matches("[a-zA-Z\\s]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid recipe name (letters and spaces only).");
        alert.showAndWait();
        return;
    }
    
    // Validate numeric fields for Cooking Time and Servings.
    String cookingTimeText = txtCookingTime.getText().trim();
    String servingsText = txtServings.getText().trim();
    if (!isNumeric(cookingTimeText) || !isNumeric(servingsText)) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid numbers for Cooking Time and Servings.");
        alert.showAndWait();
        return;
    }
    int cookingTime = Integer.parseInt(cookingTimeText);
    int servings = Integer.parseInt(servingsText);
    
    // Validate difficulty level.
    String difficulty = txtDifficulty.getText().trim();
    if (!isValidDifficulty(difficulty)) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a valid difficulty level: Easy, Medium, or Hard.");
        alert.showAndWait();
        return;
    }
    
    // Set recipe properties.
    recipe.setName(recipeName);
    
    // Process the data field: wrap plain text in a JSON object if needed.
    String dataInput = txtData.getText().trim();
    Gson gson = new Gson();
    if (dataInput.isEmpty()) {
        dataInput = "{}";
    } else {
        // If the input does not start with '{' or '[', assume it's plain text and wrap it.
        if (!dataInput.startsWith("{") && !dataInput.startsWith("[")) {
            dataInput = "{\"steps\":" + gson.toJson(dataInput) + "}";
        }
    }
    recipe.setData(dataInput);
    
    recipe.setCookingTime(cookingTime);
    recipe.setServings(servings);
    recipe.setDifficultyLevel(difficulty);
    
    // Process ingredient names.
    List<String> ingredients = Arrays.stream(txtIngredientNames.getText().split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    recipe.setIngredientNames(ingredients);
    
    try {
        recipeService.updateRecipe(recipe);
    } catch (Exception e) {
        e.printStackTrace();
    }
    closeWindow();
}

// Helper method to check if a string is numeric.
private boolean isNumeric(String text) {
    try {
        Integer.parseInt(text);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

// Helper method to validate the difficulty level.
private boolean isValidDifficulty(String difficulty) {
    return difficulty.equals("Easy") || difficulty.equals("Medium") || difficulty.equals("Hard");
}


    @FXML
    private void handleCancel() {
        closeWindow();
    }
    
    private void closeWindow() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}
