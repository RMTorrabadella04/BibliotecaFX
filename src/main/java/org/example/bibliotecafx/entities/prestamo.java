package org.example.bibliotecafx.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "prestamo")
public class prestamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "isbn_libro")
    private org.example.bibliotecafx.entities.libro libro;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    private org.example.bibliotecafx.entities.socio socio;

    private String fechaPrestamo;

    private String fechaDevolucion;

    public prestamo() {}

    public prestamo(org.example.bibliotecafx.entities.libro libro, org.example.bibliotecafx.entities.socio socio, String fechaPrestamo, String fechaDevolucion) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }



    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public org.example.bibliotecafx.entities.libro getLibro() {return libro;}

    public void setLibro(org.example.bibliotecafx.entities.libro libro) {this.libro = libro;}

    public org.example.bibliotecafx.entities.socio getSocio() {return socio;}

    public void setSocio(org.example.bibliotecafx.entities.socio socio) {this.socio = socio;}

    public String getFechaPrestamo() {return fechaPrestamo;}

    public void setFechaPrestamo(String fechaPrestamo) {this.fechaPrestamo = fechaPrestamo;}

    public String getFechaDevolucion() {return fechaDevolucion;}

    public void setFechaDevolucion(String fechaDevolucion) {this.fechaDevolucion = fechaDevolucion;}

    @Override
    public String toString() {
        return "Prestamo:" + "\n\tId: " + id + "\n\tLibro: " + libro + "\n\tSocio: " + socio + "\n\tFecha Prestamo: " + fechaPrestamo + "\n\tFecha Devolucion: " + fechaDevolucion;
    }
}
