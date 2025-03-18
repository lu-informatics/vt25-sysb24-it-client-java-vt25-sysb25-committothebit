// controllers/EditIngredientController.java
package appetite.java.client.controllers;

import java.util.Arrays;
import java.util.List;

import appetite.java.client.models.Ingredient;
import appetite.java.client.services.IngredientService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditIngredientViewController {
    private IngredientService ingredientService;
    private Ingredient ingredient;
    
    @FXML private TextField txtName;
    @FXML private TextField txtCategory;
    @FXML private TextField txtUnit;
    @FXML private TextField txtDietTag;
    
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        txtName.setText(ingredient.getName());
        txtCategory.setText(ingredient.getCategory());
        txtUnit.setText(ingredient.getUnit());
        txtDietTag.setText(ingredient.getDietTag());
    }
    
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    
    @FXML
private void handleSave() {
    // Validate ingredient name: allow only letters and spaces.
    String ingredientName = txtName.getText().trim();
    if (!ingredientName.matches("[a-zA-Z\\s]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid ingredient name (letters and spaces only).");
        alert.showAndWait();
        return;
    }
    
    // Validate category: allow only letters and spaces.
    String category = txtCategory.getText().trim();
    if (!category.matches("[a-zA-Z\\s]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid category (letters and spaces only).");
        alert.showAndWait();
        return;
    }
    
    // Validate unit: allow only letters and spaces.
    String unit = txtUnit.getText().trim();
    if (!unit.matches("[a-zA-Z\\s]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid unit (letters and spaces only).");
        alert.showAndWait();
        return;
    }
    
    // Validate diet tag: allow only letters and spaces.
     String dietTag = txtDietTag.getText().trim();
        List<String> validDietTags = Arrays.asList("Non-Vegetarian", "Pescatarian", "Vegetarian", "Vegan");
        if (!validDietTags.contains(dietTag)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid diet tag (Non-Vegetarian, Pescatarian, Vegetarian, or Vegan).");
            alert.showAndWait();
            return;
        }
    // After validations, update the ingredient.
    ingredient.setName(ingredientName);
    ingredient.setCategory(category);
    ingredient.setUnit(unit);
    ingredient.setDietTag(dietTag);
    
    try {
        ingredientService.updateIngredient(ingredient);
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
