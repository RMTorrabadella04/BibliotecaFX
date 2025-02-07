package org.example.bibliotecafx.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.bibliotecafx.DAO.autorDAOImpl;
import org.example.bibliotecafx.DAO.libroDAOImpl;
import org.example.bibliotecafx.DAO.prestamosDAOImpl;
import org.example.bibliotecafx.entities.autor;
import org.example.bibliotecafx.entities.libro;
import org.example.bibliotecafx.entities.prestamo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class prestamoControlador {


    prestamosDAOImpl prestamoDAO = new prestamosDAOImpl();
    libroDAOImpl libroDAO = new libroDAOImpl();
    autorDAOImpl autorDAO = new autorDAOImpl();

    @FXML
    private TableView<prestamo> tablaPrestamos;


    List<prestamo> prestamos= new ArrayList<>();

    @FXML
    private void initialize() {
        // Configuración de las columnas
        TableColumn<prestamo, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<prestamo, libro> colISBN = new TableColumn<>("ISBN Libro");
        colISBN.setCellValueFactory(new PropertyValueFactory<>("libro"));

        TableColumn<prestamo, autor> colAutor = new TableColumn<>("Id Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

        TableColumn<prestamo, String> colFechaP = new TableColumn<>("Fecha Prestamo");
        colFechaP.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));

        TableColumn<prestamo, String> colFechaD = new TableColumn<>("Fecha Devolucion");
        colFechaD.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));

        // Añadir las columnas al TableView
        tablaPrestamos.getColumns().addAll(colId, colISBN, colAutor, colFechaP, colFechaD);

    }


    @FXML
    public void buscarLibro() {

    }

    private void mostrarAlerta(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private int obtenerDatoNumerico(String campo, String mensaje) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Entrada de Datos");
        dialogo.setHeaderText(campo);
        dialogo.setContentText(mensaje);

        Optional<String> respuesta = dialogo.showAndWait();
        if (respuesta.isPresent()) {
            try {
                return Integer.parseInt(respuesta.get());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", campo + " debe ser un número válido.");
            }
        } else {
            mostrarAlerta("Error", "Por favor, ingresa un " + campo);
        }
        return -1;
    }

    private String obtenerDatoTexto(String campo, String mensaje) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Entrada de Datos");
        dialogo.setHeaderText(campo);
        dialogo.setContentText(mensaje);

        Optional<String> respuesta = dialogo.showAndWait();
        if (respuesta.isPresent() && !respuesta.get().trim().isEmpty()) {
            return respuesta.get();
        } else {
            mostrarAlerta("Error", "Por favor, ingresa un " + campo);
        }
        return null;
    }


    public void crearLibro(ActionEvent actionEvent) {
        int isbn = obtenerDatoNumerico("ISBN", "Dime el ISBN para crear un libro:");
        if (isbn == -1) return;

        String titulo = obtenerDatoTexto("Título", "Introduce el título del libro:");
        if (titulo == null) return;

        String nombreAutor = obtenerDatoTexto("Autor", "Introduce el nombre del autor:");
        if (nombreAutor == null) return;

        autor autor = autorDAO.findByNombre(nombreAutor);
        if (autor == null) return;

        String editorial = obtenerDatoTexto("Editorial", "Introduce la editorial del libro:");
        if (editorial == null) return;

        int anoPublicacion = obtenerDatoNumerico("Año de Publicación", "Introduce el año de publicación:");
        if (anoPublicacion == -1) return;

        libro libro = new libro(isbn, anoPublicacion, autor.getNombre(), editorial, titulo);
        libroDAO.create(libro);

    }


    public void irAInicio(ActionEvent actionEvent) throws IOException {

        // Cargar el nuevo archivo FXML para la ventana de libros
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/inicio.fxml"));

        // Crear una nueva escena
        Scene scene = new Scene(loader.load());

        // Obtener la ventana principal (Stage)
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

        // Cambiar la escena a la nueva ventana
        stage.setScene(scene);
        stage.show();

    }

}
