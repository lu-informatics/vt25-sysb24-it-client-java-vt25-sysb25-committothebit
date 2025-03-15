package appetite.java.client.controllers;

import appetite.java.client.models.Recipe;
import appetite.java.client.services.RecipeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.List;
import java.util.Optional;

public class RecipeViewController {
    
    private RecipeService recipeService;
    private ObservableList<Recipe> recipeList;
    
    @FXML private TableView<Recipe> tableViewRecipes;
    @FXML private TableColumn<Recipe, Integer> columnId;
    @FXML private TableColumn<Recipe, String> columnName;
    @FXML private TableColumn<Recipe, String> columnData;
    @FXML private TableColumn<Recipe, Integer> columnCookingTime;
    @FXML private TableColumn<Recipe, Integer> columnServings;
    @FXML private TableColumn<Recipe, String> columnDifficulty;
    @FXML private TableColumn<Recipe, String> columnIngredientNames;
    @FXML private Button btnAdd;
    @FXML private Button btnRefresh;
    
    public void initialize() {
        recipeService = new RecipeService("http://localhost:5156");
        recipeList = FXCollections.observableArrayList();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnCookingTime.setCellValueFactory(new PropertyValueFactory<>("cookingTime"));
        columnServings.setCellValueFactory(new PropertyValueFactory<>("servings"));
        columnDifficulty.setCellValueFactory(new PropertyValueFactory<>("difficultyLevel"));
        columnIngredientNames.setCellValueFactory(new PropertyValueFactory<>("ingredientNames"));
        tableViewRecipes.setItems(recipeList);
        loadRecipes();
    }
    
    @FXML
    private void loadRecipes() {
        try {
            List<Recipe> recipes = recipeService.getRecipes();
            recipeList.setAll(recipes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleAdd(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRecipeView.fxml"));
        Parent root = loader.load();
        // After the rename, the controller is now AddRecipeViewController
        AddRecipeViewController controller = loader.getController();
        controller.setRecipeService(recipeService);
        Stage stage = new Stage();
        stage.setTitle("Add Recipe");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        loadRecipes();
    }
    
    @FXML
    private void handleEdit(ActionEvent event) throws Exception {
        Recipe selected = tableViewRecipes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditRecipeView.fxml"));
            Parent root = loader.load();
            // Use the correct controller name here
            EditRecipeViewController controller = loader.getController();
            controller.setRecipe(selected);
            controller.setRecipeService(recipeService);
            Stage stage = new Stage();
            stage.setTitle("Edit Recipe");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            loadRecipes();
        }
    }
    
   @FXML
    private void handleDelete(ActionEvent event) {
    Recipe selected = tableViewRecipes.getSelectionModel().getSelectedItem();
    if (selected != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this recipe?");
        alert.setContentText("Recipe: " + selected.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                recipeService.deleteRecipe(selected.getId());
                loadRecipes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

    
    @FXML
    private void handleRefresh(ActionEvent event) {
        loadRecipes();
    }
}
