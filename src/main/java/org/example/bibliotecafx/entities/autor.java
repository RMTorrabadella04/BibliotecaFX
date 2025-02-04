package org.example.bibliotecafx.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "autor")
public class autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String nacionalidad;

    public autor() {}

    public autor(Integer id, String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Integer getId() {return id;}

    public String getNombre() {return nombre;}

    public String getNacionalidad() {return nacionalidad;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setNacionalidad(String nacionalidad) {this.nacionalidad = nacionalidad;}

    @Override
    public String toString() {
        return "Autor:" + "\n\tId: " + id + "\n\tNombre: " + nombre + "\n\tNacionalidad: " + nacionalidad;
    }

}
