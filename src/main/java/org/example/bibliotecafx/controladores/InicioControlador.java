package org.example.bibliotecafx.controladores;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.bibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class InicioControlador {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }


    public void irALibros(ActionEvent actionEvent) throws IOException {
        // Cargar el nuevo archivo FXML para la ventana de libros
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/libros.fxml"));

        // Crear una nueva escena
        Scene scene = new Scene(loader.load());

        // Obtener la ventana principal (Stage)
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

        // Cambiar la escena a la nueva ventana
        stage.setScene(scene);
        stage.show();
    }

    public void irAAutores(ActionEvent actionEvent) throws IOException {
        // Cargar el nuevo archivo FXML para la ventana de libros
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/autor.fxml"));

        // Crear una nueva escena
        Scene scene = new Scene(loader.load());

        // Obtener la ventana principal (Stage)
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

        // Cambiar la escena a la nueva ventana
        stage.setScene(scene);
        stage.show();
    }
}
