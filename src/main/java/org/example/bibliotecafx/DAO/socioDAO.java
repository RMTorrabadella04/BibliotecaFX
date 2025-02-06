package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.socio;

import java.util.List;

public interface socioDAO {

    // CRUD
    void create(socio socio);
    void update(socio socio);
    void delete(socio socio);
    socio findByNumeroTelefono(Integer telefono);
    socio findByNombre(String nombre);
    List<socio> findAll();

}
