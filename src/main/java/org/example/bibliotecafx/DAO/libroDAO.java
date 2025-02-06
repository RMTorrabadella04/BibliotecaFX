package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.libro;

import java.util.List;

public interface libroDAO {

    // Crear un libro
    void create(libro libro);

    // Modicar un libro
    void update(libro libro);

    // Eliminar un libro
    void delete(libro libro);

    // Buscar libros por ISBN
    libro findByISBN(Integer ISBN);

    // Buscar libros por titulo
    libro findByTitulo(String titulo);

    // Buscar libros por autor
    libro findByAutor(String autor);

    // Listar todos los libros
    List<libro> findAll();
}
