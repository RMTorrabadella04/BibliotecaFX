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
import org.example.bibliotecafx.entities.autor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class autorControlador {

    autorDAOImpl autorDAO = new autorDAOImpl();

    @FXML
    private TableView<autor> tablaAutores;


    @FXML
    private TextField busquedaTextField;

    List<autor> autores = new ArrayList<>();

    @FXML
    private void initialize() {
        // Configuración de las columnas
        TableColumn<autor, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<autor, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<autor, String> colNacionalidad = new TableColumn<>("Nacionalidad");
        colNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));

        // Añadir las columnas al TableView
        tablaAutores.getColumns().addAll(colId, colNombre, colNacionalidad);

        // Llenar el TableView con datos de ejemplo (puedes cargar los datos de una base de datos o una lista)
        autores = autorDAO.findAll();
        tablaAutores.getItems().setAll(autores);
    }


    @FXML
    public void buscarAutor() {
        String busquedaTexto = busquedaTextField.getText().trim();


        if (busquedaTexto.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingresa un valor para buscar.");
            return;
        }

        autor autorsito = autorDAO.findByNombre(busquedaTexto);
        autores.clear();
        autores.add(autorsito);
        tablaAutores.getItems().setAll(autores);
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

    public void modificarAutor() {
        int id = obtenerDatoNumerico("Id", "Introduce el id del autor que quieres modificar:");
        if (id == -1) return;

        String nombre = obtenerDatoTexto("Nombre", "Introduce el nombre del autor:");
        if (nombre == null) return;

        String nacionalidad = obtenerDatoTexto("Nacionalidad", "Introduce la nacionalidad del autor:");
        if (nacionalidad == null) return;

        autor autor = new autor(id, nombre, nacionalidad);
        autorDAO.update(autor);
        listarAutores();
    }

    public void eliminarAutor() {
        String nombre = obtenerDatoTexto("Nombre", "Introduce el nombre del autor:");
        if (nombre == null) return;

        // Buscar el libro usando el método findByISBN
        autor autor = autorDAO.findByNombre(nombre);

        if (autor != null) {
            try {
                // Si el libro existe, eliminarlo
                autorDAO.delete(autor);
                mostrarAlerta("Éxito", "El autor ha sido eliminado con éxito.");
            } catch (Exception e) {
                // Si ocurre un error al eliminar
                mostrarAlerta("Error", "Hubo un problema al eliminar el autor: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "No se encontró el autor con ese nombre.");
        }

        listarAutores();
    }

    public void crearAutor() {
        String nombre = obtenerDatoTexto("Nombre", "Introduce el nombre del autor:");
        if (nombre == null) return;

        String nacionalidad = obtenerDatoTexto("Nacionalidad", "Introduce la nacionalidad del autor:");
        if (nacionalidad == null) return;

        autor autor = new autor(nombre, nacionalidad);
        autorDAO.create(autor);
        listarAutores();
    }

    public void listarAutores() {
        autores.clear();
        autores = autorDAO.findAll();
        tablaAutores.getItems().setAll(autores);
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

