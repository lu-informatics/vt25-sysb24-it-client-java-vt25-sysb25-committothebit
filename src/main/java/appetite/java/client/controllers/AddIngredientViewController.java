// controllers/AddIngredientController.java
package appetite.java.client.controllers;

import appetite.java.client.models.Ingredient;
import appetite.java.client.services.IngredientService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddIngredientViewController {
    private IngredientService ingredientService;
    
    @FXML private TextField txtName;
    @FXML private TextField txtCategory;
    @FXML private TextField txtUnit;
    @FXML private TextField txtDietTag;
    
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    
    @FXML
    private void handleSave() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(txtName.getText());
        ingredient.setCategory(txtCategory.getText());
        ingredient.setUnit(txtUnit.getText());
        ingredient.setDietTag(txtDietTag.getText());
        try {
            ingredientService.createIngredient(ingredient);
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
