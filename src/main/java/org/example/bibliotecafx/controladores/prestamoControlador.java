package org.example.bibliotecafx.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.bibliotecafx.DAO.libroDAOImpl;
import org.example.bibliotecafx.DAO.socioDAOImpl;
import org.example.bibliotecafx.DAO.prestamosDAOImpl;
import org.example.bibliotecafx.entities.libro;
import org.example.bibliotecafx.entities.prestamo;
import org.example.bibliotecafx.entities.socio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class prestamoControlador {


    prestamosDAOImpl prestamoDAO = new prestamosDAOImpl();
    libroDAOImpl libroDAO = new libroDAOImpl();
    socioDAOImpl socioDAO = new socioDAOImpl();

    @FXML
    private TableView<Object> tablaPrestamos;


    List<prestamo> prestamos= new ArrayList<>();
    List<libro> libros= new ArrayList<>();

    @FXML
    private void initialize() {
        configurarColumnasParaPrestamos();
    }


    @FXML
    public void buscarPrestamos() {

        libros.clear();
        prestamos.clear();

        prestamos =prestamoDAO.listarPrestamos();

        for (prestamo prestamo : prestamos) {

            libros.add(prestamo.getLibro());

        }

        configurarColumnasParaLibros();

    }

    public void listarPrestamos() {

        prestamos.clear();

        String nombre = obtenerDatoTexto("Nombre", "Introduce el nombre del socio:");
        if (nombre == null) return;

        socio socio = socioDAO.findByNombre(nombre);

        prestamos = prestamoDAO.listarHistorialPrestamos(socio);

        tablaPrestamos.getItems().setAll(prestamos);

        configurarColumnasParaPrestamos();
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


    public void crearPrestamo(ActionEvent actionEvent) {

        int isbn = obtenerDatoNumerico("ISBN", "Dime el ISBN del libro el cual quieres hacer prestamo:");
        if (isbn == -1) return;

        libro libro = libroDAO.findByISBN(isbn);

        String nombreSocio = obtenerDatoTexto("Socio", "Introduce el nombre del socio:");
        if (nombreSocio == null) return;

        socio socio = socioDAO.findByNombre(nombreSocio);

        String fechaP = obtenerDatoTexto("Fecha Prestamo", "Introduce la fecha de prestamo:");
        if (fechaP == null) return;

        String fechaD = obtenerDatoTexto("Fecha Devolucion", "Introduce la fecha de devolucion:");
        if (fechaD == null) return;

        if (libro == null || socio == null) {
            mostrarAlerta("Error", "Libro o socio no encontrado.");
        }else{
            prestamo presta = new prestamo(libro, socio, fechaP, fechaD);
            prestamoDAO.registrarPrestamo(presta);
            configurarColumnasParaPrestamos();
        }

    }

    private void configurarColumnasParaPrestamos() {
        tablaPrestamos.getColumns().clear();
        tablaPrestamos.getItems().clear();

        TableColumn<Object, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Object, libro> colLibro = new TableColumn<>("Libro");
        colLibro.setCellValueFactory(new PropertyValueFactory<>("libro"));

        TableColumn<Object, socio> colSocio = new TableColumn<>("Socio");
        colSocio.setCellValueFactory(new PropertyValueFactory<>("socio"));

        TableColumn<Object, String> colFechaP = new TableColumn<>("Fecha Préstamo");
        colFechaP.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));

        TableColumn<Object, String> colFechaD = new TableColumn<>("Fecha Devolución");
        colFechaD.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));

        tablaPrestamos.getColumns().addAll(colLibro, colId, colSocio, colFechaP, colFechaD);
        tablaPrestamos.getItems().addAll(prestamos);
    }

    private void configurarColumnasParaLibros() {
        tablaPrestamos.getColumns().clear();
        tablaPrestamos.getItems().clear();

        TableColumn<Object, Integer> colId = new TableColumn<>("ISBN");
        colId.setCellValueFactory(new PropertyValueFactory<>("ibn"));

        TableColumn<Object, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Object, String> colAutor = new TableColumn<>("Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

        TableColumn<Object, String> colGenero = new TableColumn<>("Editorial");
        colGenero.setCellValueFactory(new PropertyValueFactory<>("editorial"));

        TableColumn<Object, String> colAno = new TableColumn<>("Año Publicacion");
        colGenero.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));

        tablaPrestamos.getColumns().addAll(colId, colTitulo, colAutor, colGenero);
        tablaPrestamos.getItems().addAll(libros);
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
