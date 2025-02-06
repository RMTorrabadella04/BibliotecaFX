package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.prestamo;
import org.example.bibliotecafx.entities.socio;

import java.util.List;

public interface prestamosDAO {
    // registrar prestamo
    void registrarPrestamo(prestamo prestamo);

    // Listar los libros prestados actualmente.
    List<prestamo> listarPrestamos();

    //Listar el historial de préstamos de un socio
    List<prestamo> listarHistorialPrestamos(socio socio);
}
