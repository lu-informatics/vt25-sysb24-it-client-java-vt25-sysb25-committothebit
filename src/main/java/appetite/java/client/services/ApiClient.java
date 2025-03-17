// services/ApiClient.java
package appetite.java.client.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;

public class ApiClient {
    private final HttpClient httpClient;
    private final String baseUrl;
    
    public ApiClient(String baseUrl) {
        this.httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
        this.baseUrl = baseUrl;
    }
    
    public String get(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(baseUrl + endpoint))
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    
    public String post(String endpoint, String json) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        
        // Log the raw response for debugging
        System.out.println("Raw response: " + body);
        
        // If the response appears to be a JSON string wrapped in quotes,
        // remove the wrapping quotes and unescape any internal quotes.
        if (body != null && body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
            body = body.replace("\\\"", "\"");
        }
        return body;
    }
    
    
    public String put(String endpoint, String json) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .uri(URI.create(baseUrl + endpoint))
            .header("Content-Type", "application/json")
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    
    public String delete(String endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .DELETE()
            .uri(URI.create(baseUrl + endpoint))
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
