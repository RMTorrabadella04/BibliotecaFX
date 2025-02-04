package org.example.bibliotecafx.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "socio")
public class socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String direccion;

    private Integer telefono;

    public socio() {}

    public socio(Integer id, String nombre, String direccion, Integer telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public Integer getTelefono() {return telefono;}
    public void setTelefono(Integer telefono) {this.telefono = telefono;}

    @Override
    public String toString() {
        return "Socio:" + "\n\tId: " + id + "\n\tNombre: " + nombre + "\n\tDireccion: " + direccion + "\n\tTelefono: " + telefono;
    }


}
