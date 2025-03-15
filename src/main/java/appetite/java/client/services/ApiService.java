package appetite.java.client.services;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;

public class ApiService {
    private final ApiClient apiClient;
    private final Gson gson;

    public ApiService(String baseUrl) {
        this.apiClient = new ApiClient(baseUrl);
        this.gson = new Gson();
    }

    public <T> T get(String endpoint, Type typeOfT) throws IOException, InterruptedException {
        String response = apiClient.get(endpoint);
        return gson.fromJson(response, typeOfT);
    }

    public <T> T post(String endpoint, Object payload, Type typeOfT) throws IOException, InterruptedException {
        String json = gson.toJson(payload);
        String response = apiClient.post(endpoint, json);
        return gson.fromJson(response, typeOfT);
    }

    public boolean put(String endpoint, Object payload) throws IOException, InterruptedException {
        String json = gson.toJson(payload);
        apiClient.put(endpoint, json);
        return true;
    }

    public boolean delete(String endpoint) throws IOException, InterruptedException {
        apiClient.delete(endpoint);
        return true;
    }
}
