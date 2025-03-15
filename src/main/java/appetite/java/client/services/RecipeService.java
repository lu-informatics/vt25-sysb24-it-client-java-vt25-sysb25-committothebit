package appetite.java.client.services;

import appetite.java.client.models.Recipe;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class RecipeService {
    private final ApiService apiService;

    public RecipeService(String baseUrl) {
        this.apiService = new ApiService(baseUrl);
    }

    public List<Recipe> getRecipes() throws IOException, InterruptedException {
        Type listType = new TypeToken<List<Recipe>>() {}.getType();
        return apiService.get("/api/recipe", listType);
    }

    public Recipe getRecipe(int id) throws IOException, InterruptedException {
        return apiService.get("/api/recipe/" + id, Recipe.class);
    }

    public Recipe createRecipe(Recipe recipe) throws IOException, InterruptedException {
        return apiService.post("/api/recipe", recipe, Recipe.class);
    }

    public boolean updateRecipe(Recipe recipe) throws IOException, InterruptedException {
        return apiService.put("/api/recipe/" + recipe.getId(), recipe);
    }

    public boolean deleteRecipe(int id) throws IOException, InterruptedException {
        return apiService.delete("/api/recipe/" + id);
    }
}
