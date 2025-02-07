package org.example.bibliotecafx.vistas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class autorVista extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML para la vista de inicio
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/autor.fxml"));
            Scene scene = new Scene(loader.load());

            // Configurar la ventana principal
            primaryStage.setTitle("Autores FX");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
