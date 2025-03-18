// controllers/AddRecipeController.java
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

public class AddRecipeViewController {
    private RecipeService recipeService;
    
    @FXML private TextField txtName;
    @FXML private TextField txtData;
    @FXML private TextField txtCookingTime;
    @FXML private TextField txtServings;
    @FXML private TextField txtDifficulty;
    @FXML private TextField txtIngredientNames;
    
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    
    @FXML
private void handleSave() {
    // Validate numeric fields
    String cookingTimeText = txtCookingTime.getText().trim();
    String servingsText = txtServings.getText().trim();
    
    if (!isNumeric(cookingTimeText) || !isNumeric(servingsText)) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid numbers for Cooking Time and Servings.");
        alert.showAndWait();
        return;
    }
    
    // Validate recipe name to only allow letters and spaces
    String recipeName = txtName.getText().trim();
    if (!recipeName.matches("[a-zA-Z\\s]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid recipe name (letters and spaces only).");
        alert.showAndWait();
        return;
    }
    
    // Validate difficulty level
    String difficulty = txtDifficulty.getText().trim();
    if (!isValidDifficulty(difficulty)) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a valid difficulty level: Easy, Medium, or Hard.");
        alert.showAndWait();
        return;
    }
    
    Recipe recipe = new Recipe();
    recipe.setName(recipeName);
    
    // Process the data field: wrap plain text in a JSON object if needed.
    String dataInput = txtData.getText().trim();
    Gson gson = new Gson();
    if (dataInput.isEmpty()) {
        dataInput = "{}";
    } else {
        // If the input does not start with '{' or '[', assume it's plain text and wrap it.
        if (!dataInput.startsWith("{") && !dataInput.startsWith("[")) {
            dataInput = "{\"description\":" + gson.toJson(dataInput) + "}";
        }
    }
    recipe.setData(dataInput);
    
    recipe.setCookingTime(Integer.parseInt(cookingTimeText));
    recipe.setServings(Integer.parseInt(servingsText));
    recipe.setDifficultyLevel(difficulty);
    
    List<String> ingredients = Arrays.stream(txtIngredientNames.getText().split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    recipe.setIngredientNames(ingredients);
    
    try {
        recipeService.createRecipe(recipe);
    } catch (Exception e) {
        e.printStackTrace();
    }
    closeWindow();
}

// Helper method to validate difficulty level input
private boolean isValidDifficulty(String difficulty) {
    return difficulty.equals("Easy") || difficulty.equals("Medium") || difficulty.equals("Hard");
}


private boolean isNumeric(String str) {
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
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
