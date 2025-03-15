// controllers/AddRecipeController.java
package appetite.java.client.controllers;

import appetite.java.client.models.Recipe;
import appetite.java.client.services.RecipeService;
import javafx.fxml.FXML;
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
        Recipe recipe = new Recipe();
        recipe.setName(txtName.getText());
        recipe.setData(txtData.getText());
        recipe.setCookingTime(Integer.parseInt(txtCookingTime.getText()));
        recipe.setServings(Integer.parseInt(txtServings.getText()));
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
    
    @FXML
    private void handleCancel() {
        closeWindow();
    }
    
    private void closeWindow() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}
