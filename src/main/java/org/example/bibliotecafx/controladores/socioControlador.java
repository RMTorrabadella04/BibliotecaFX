package org.example.bibliotecafx.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.bibliotecafx.DAO.socioDAOImpl;
import org.example.bibliotecafx.entities.autor;
import org.example.bibliotecafx.entities.libro;
import org.example.bibliotecafx.entities.socio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class socioControlador {

    socioDAOImpl socioDAO = new socioDAOImpl();

    @FXML
    private TableView<socio> tablaSocios;

    @FXML
    private ComboBox<String> menuSeleccionMetodoBusqueda;

    @FXML
    private TextField busquedaTextField;

    List<socio> socios= new ArrayList<socio>();

    @FXML
    private void initialize() {
        // Configuración de las columnas
        TableColumn<socio, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<socio, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<socio, String> colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<socio, Integer> colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        // Añadir las columnas al TableView
        tablaSocios.getColumns().addAll(colId, colNombre, colDireccion, colTelefono);

        // Llenar el TableView con datos de ejemplo (puedes cargar los datos de una base de datos o una lista)
        socios = socioDAO.findAll();
        tablaSocios.getItems().setAll(socios);
    }


    @FXML
    public void buscarSocio() {
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
            case "Nombre":
                socio sociesito = socioDAO.findByNombre(busquedaTexto);
                socios.clear();
                socios.add(sociesito);
                tablaSocios.getItems().setAll(socios);
                break;
            case "NumTelefono":
                socio sociesito2 = socioDAO.findByNumeroTelefono(Integer.parseInt(busquedaTexto));
                socios.clear();
                socios.add(sociesito2);
                tablaSocios.getItems().setAll(socios);
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

    public void modificarSocio() {
        int id = obtenerDatoNumerico("Id", "Dime el Id del socio para modificar:");
        if (id == -1) return;

        String nombre = obtenerDatoTexto("Nombre", "Introduce el nombre del socio:");
        if (nombre == null) return;

        String direccion = obtenerDatoTexto("Direccion", "Introduce la direccion del socio:");
        if (direccion == null) return;

        Integer NTelefono = obtenerDatoNumerico("Número de Teléfono", "Introduce el Número de Teléfono del socio:");
        if (NTelefono == -1) return;

        socio socio = new socio(id, nombre, direccion, NTelefono);
        socioDAO.update(socio);
        listarSocio();
    }

    public void eliminarSocio() {

        String nombre = obtenerDatoTexto("Nombre", "Introduce el nombre del socio:");
        if (nombre == null) return;
        socio socio = socioDAO.findByNombre(nombre);

        if (socio != null) {
            try {
                // Si el libro existe, eliminarlo
                socioDAO.delete(socio);
                mostrarAlerta("Éxito", "El socio ha sido eliminado con éxito.");
            } catch (Exception e) {
                // Si ocurre un error al eliminar
                mostrarAlerta("Error", "Hubo un problema al eliminar el socio: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "No se encontró el socio con ese nombre.");
        }

        listarSocio();
    }

    public void crearSocio() {
        String nombre = obtenerDatoTexto("Nombre", "Introduce el nombre del socio:");
        if (nombre == null) return;

        String direccion = obtenerDatoTexto("Direccion", "Introduce la direccion del socio:");
        if (direccion == null) return;

        Integer NTelefono = obtenerDatoNumerico("Número de Teléfono", "Introduce el Número de Teléfono del socio:");
        if (NTelefono == -1) return;

        socio socio = new socio(nombre, direccion, NTelefono);
        socioDAO.create(socio);
        listarSocio();
    }

    public void listarSocio() {
        socios.clear();
        socios = socioDAO.findAll();
        tablaSocios.getItems().setAll(socios);
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
