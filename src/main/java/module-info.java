module java.rest.client {
    exports appetite.java.client.models;
    exports appetite.java.client.controllers;
    exports appetite.java.client.services;
    exports appetite.java.client;
    
    opens appetite.java.client.controllers to javafx.fxml;
    opens appetite.java.client.models to com.google.gson, javafx.base;
    
    requires com.google.gson;
    requires java.net.http;
    requires javafx.fxml;
    requires javafx.controls;
    requires transitive javafx.graphics;
}