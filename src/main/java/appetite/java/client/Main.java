package appetite.java.client;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import appetite.java.client.controllers.LandingPageViewController;
import appetite.java.client.services.IngredientService;
import appetite.java.client.services.RecipeService;
import appetite.java.client.services.ApiService;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApiService apiService = new ApiService("http://localhost:5156");
        URL location = getClass().getResource("/LandingPageView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();


        LandingPageViewController landingPageViewController = fxmlLoader.getController();
        landingPageViewController.setApiService(apiService);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("REST API Client");
        primaryStage.setResizable(true);
        primaryStage.show();

    }
}
