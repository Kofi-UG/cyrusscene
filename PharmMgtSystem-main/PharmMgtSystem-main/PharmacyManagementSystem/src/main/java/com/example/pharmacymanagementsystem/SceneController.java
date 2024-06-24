package com.example.pharmacymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToDrugs(ActionEvent event) throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("drugs.fxml"));
        root = fxmlLoader.load();
        
        // Get the current stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Create a new scene with the loaded root
        scene = new Scene(root);
        
        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSuppliers(ActionEvent event) throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("suppliers.fxml"));
        root = fxmlLoader.load();
        
        // Get the current stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Create a new scene with the loaded root
        scene = new Scene(root);
        
        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }
}
