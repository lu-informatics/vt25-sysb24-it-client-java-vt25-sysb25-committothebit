package appetite.java.client.controllers;

import appetite.java.client.models.Ingredient;
import appetite.java.client.services.IngredientService;
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

public class IngredientViewController {

    private IngredientService ingredientService;
    private ObservableList<Ingredient> ingredientList;
    
    @FXML private TableView<Ingredient> tableViewIngredients;
    @FXML private TableColumn<Ingredient, Integer> columnId;
    @FXML private TableColumn<Ingredient, String> columnName;
    @FXML private TableColumn<Ingredient, String> columnCategory;
    @FXML private TableColumn<Ingredient, String> columnUnit;
    @FXML private TableColumn<Ingredient, String> columnDietTag;
    @FXML private Button btnAdd;
    @FXML private Button btnRefresh;
    
    public void initialize() {
        ingredientService = new IngredientService("http://localhost:5156");
        ingredientList = FXCollections.observableArrayList();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        columnDietTag.setCellValueFactory(new PropertyValueFactory<>("dietTag"));
        tableViewIngredients.setItems(ingredientList);
        loadIngredients();
    }
    
    @FXML
    private void loadIngredients() {
        try {
            List<Ingredient> ingredients = ingredientService.getIngredients();
            ingredientList.setAll(ingredients);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleAdd(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddIngredientView.fxml"));
        Parent root = loader.load();

        // Retrieve the controller instance and set the IngredientService
        AddIngredientViewController controller = loader.getController();
        controller.setIngredientService(ingredientService);

        Stage stage = new Stage();
        stage.setTitle("Add Ingredient");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        
        loadIngredients();
    }

    @FXML
    private void handleEdit(ActionEvent event) throws Exception {
        Ingredient selected = tableViewIngredients.getSelectionModel().getSelectedItem();
        if (selected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditIngredientView.fxml"));
            Parent root = loader.load();
            // Use the correct controller name here
            EditIngredientViewController controller = loader.getController();
            controller.setIngredient(selected);
            controller.setIngredientService(ingredientService);
            Stage stage = new Stage();
            stage.setTitle("Edit Ingredient");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            loadIngredients();
        }
    }
    
    @FXML
    private void handleDelete(ActionEvent event) {
    Ingredient selected = tableViewIngredients.getSelectionModel().getSelectedItem();
    if (selected != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this ingredient?");
        alert.setContentText("Ingredient: " + selected.getName());
        
        // Show the confirmation dialog and wait for a user response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ingredientService.deleteIngredient(selected.getId());
                loadIngredients();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
    
    @FXML
    private void handleRefresh(ActionEvent event) {
        loadIngredients();
    }
}
