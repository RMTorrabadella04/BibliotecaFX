package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.autor;

import java.util.List;

public interface autorDAO {

        // Crear un autor
       void create(autor autor);

       //Modicar un autor
       void update(autor autor);

       //Eliminar un autor
       void delete(autor autor);

       //Buscar autores por nombre
       autor findByNombre(String nombre);

       //Listar todos los autores
       List<autor> findAll();
}
