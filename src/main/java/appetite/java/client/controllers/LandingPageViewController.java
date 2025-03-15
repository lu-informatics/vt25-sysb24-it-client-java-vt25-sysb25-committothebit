package appetite.java.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import appetite.java.client.services.ApiService;

public class LandingPageViewController {
    @FXML private Button btnIngredients;
    @FXML private Button btnRecipes;
    
    private ApiService apiService;
    
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }
    
    @FXML
    private void handleIngredients(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/IngredientView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Ingredients");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    private void handleRecipes(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Recipes");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
