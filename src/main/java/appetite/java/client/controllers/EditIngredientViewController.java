// controllers/EditIngredientController.java
package appetite.java.client.controllers;

import appetite.java.client.models.Ingredient;
import appetite.java.client.services.IngredientService;
import javafx.fxml.FXML;
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
        ingredient.setName(txtName.getText());
        ingredient.setCategory(txtCategory.getText());
        ingredient.setUnit(txtUnit.getText());
        ingredient.setDietTag(txtDietTag.getText());
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
