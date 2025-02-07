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
import org.example.bibliotecafx.entities.autor;
import org.example.bibliotecafx.entities.libro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class librosControlador {

    libroDAOImpl libroDAO = new libroDAOImpl();
    autorDAOImpl autorDAO = new autorDAOImpl();

    @FXML
    private TableView<libro> tablaLibros;

    @FXML
    private ComboBox<String> menuSeleccionMetodoBusqueda;

    @FXML
    private TextField busquedaTextField;

    List<libro> libros= new ArrayList<>();

    @FXML
    private void initialize() {
        // Configuración de las columnas
        TableColumn<libro, Integer> colIsbn = new TableColumn<>("ISBN");
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<libro, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<libro, String> colAutor = new TableColumn<>("Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

        TableColumn<libro, String> colEditorial = new TableColumn<>("Editorial");
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));

        TableColumn<libro, Integer> colAnoPublicacion = new TableColumn<>("Año de Publicación");
        colAnoPublicacion.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));

        // Añadir las columnas al TableView
        tablaLibros.getColumns().addAll(colIsbn, colTitulo, colAutor, colEditorial, colAnoPublicacion);

        // Llenar el TableView con datos de ejemplo (puedes cargar los datos de una base de datos o una lista)
       libros = libroDAO.findAll();
        tablaLibros.getItems().setAll(libros);
    }


    @FXML
    public void buscarLibro() {
        String metodoSeleccionado = menuSeleccionMetodoBusqueda.getSelectionModel().getSelectedItem();
        String busquedaTexto = busquedaTextField.getText().trim();

        if (metodoSeleccionado == null || metodoSeleccionado.isEmpty()) {
            mostrarAlerta("Error", "Selecciona un método de búsqueda.");
            return;
        }

        if (busquedaTexto.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingresa un valor para buscar.");
            return;
        }
        switch (metodoSeleccionado) {
            case "ISBN":
                libro librito = libroDAO.findByISBN(Integer.parseInt(busquedaTexto));
                libros.clear();
                libros.add(librito);
                tablaLibros.getItems().setAll(libros);
                break;
            case "Título":
                libro librito2 = libroDAO.findByTitulo(busquedaTexto);
                libros.clear();
                libros.add(librito2);
                tablaLibros.getItems().setAll(libros);
                break;
            case "Autor":
                libros = libroDAO.findByAutor(busquedaTexto);
                tablaLibros.getItems().setAll(libros);
                break;
            default:
                mostrarAlerta("Error", "Método de búsqueda no reconocido.");
        }
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

    public void modificarLibro(ActionEvent actionEvent) {
        int isbn = obtenerDatoNumerico("ISBN", "Dime el ISBN del libro para modificar:");
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
        libroDAO.update(libro);
        listarLibros();
    }

    public void eliminarLibro(ActionEvent actionEvent) {
        int isbn = obtenerDatoNumerico("ISBN","Dime el ISBN del libro para eliminar:");
        if (isbn == -1) return;

        // Buscar el libro usando el método findByISBN
        libro libro = libroDAO.findByISBN(isbn);

        if (libro != null) {
            try {
                // Si el libro existe, eliminarlo
                libroDAO.delete(libro);
                mostrarAlerta("Éxito", "El libro ha sido eliminado con éxito.");
            } catch (Exception e) {
                // Si ocurre un error al eliminar
                mostrarAlerta("Error", "Hubo un problema al eliminar el libro: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "No se encontró el libro con ese ISBN.");
        }

        listarLibros();
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
        listarLibros();
    }

    public void listarLibros() {
        libros.clear();
        libros = libroDAO.findAll();
        tablaLibros.getItems().setAll(libros);
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
