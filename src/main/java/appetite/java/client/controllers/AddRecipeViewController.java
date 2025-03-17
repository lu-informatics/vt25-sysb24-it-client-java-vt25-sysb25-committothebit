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
    
    Recipe recipe = new Recipe();
    recipe.setName(txtName.getText());

    // Ensure the data field is valid JSON; if empty, default to "{}"
    String dataInput = txtData.getText().trim();
    if (dataInput.isEmpty()) {
        dataInput = "{}";
    }
    recipe.setData(dataInput);
    
    recipe.setCookingTime(Integer.parseInt(cookingTimeText));
    recipe.setServings(Integer.parseInt(servingsText));
    recipe.setDifficultyLevel(txtDifficulty.getText());
    
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
