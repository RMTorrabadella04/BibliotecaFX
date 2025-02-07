package org.example.bibliotecafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "autor")
public class autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String nacionalidad;

    public autor() {}

    public autor( String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public autor(Integer id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    // Getters y setters
    public Integer getId() { return id; }

    public String getNombre() { return nombre; }

    public String getNacionalidad() { return nacionalidad; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    @Override
    public String toString() {
        return "Autor: " + "\n\tId: " + id + "\n\tNombre: " + nombre + "\n\tNacionalidad: " + nacionalidad;
    }
}
