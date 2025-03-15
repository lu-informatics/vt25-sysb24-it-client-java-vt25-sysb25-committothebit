package appetite.java.client.services;

import appetite.java.client.models.Ingredient;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class IngredientService {
    private final ApiService apiService;

    public IngredientService(String baseUrl) {
        this.apiService = new ApiService(baseUrl);
    }

    public List<Ingredient> getIngredients() throws IOException, InterruptedException {
        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return apiService.get("/api/ingredient", listType);
    }

    public Ingredient getIngredient(int id) throws IOException, InterruptedException {
        return apiService.get("/api/ingredient/" + id, Ingredient.class);
    }

    public Ingredient createIngredient(Ingredient ingredient) throws IOException, InterruptedException {
        return apiService.post("/api/ingredient", ingredient, Ingredient.class);
    }

    public boolean updateIngredient(Ingredient ingredient) throws IOException, InterruptedException {
        return apiService.put("/api/ingredient/" + ingredient.getId(), ingredient);
    }

    public boolean deleteIngredient(int id) throws IOException, InterruptedException {
        return apiService.delete("/api/ingredient/" + id);
    }
}
