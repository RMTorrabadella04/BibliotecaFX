package org.example.bibliotecafx.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prestamo")
public class prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private libro libro;

    @Column(nullable = false)
    private socio socio;

    private String fechaPrestamo;

    private String fechaDevolucion;

    public prestamo() {}

    public prestamo(libro libro, socio socio, String fechaPrestamo, String fechaDevolucion) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }



    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public libro getLibro() {return libro;}

    public void setLibro(libro libro) {this.libro = libro;}

    public socio getSocio() {return socio;}

    public void setSocio(socio socio) {this.socio = socio;}

    public String getFechaPrestamo() {return fechaPrestamo;}

    public void setFechaPrestamo(String fechaPrestamo) {this.fechaPrestamo = fechaPrestamo;}

    public String getFechaDevolucion() {return fechaDevolucion;}

    public void setFechaDevolucion(String fechaDevolucion) {this.fechaDevolucion = fechaDevolucion;}

    @Override
    public String toString() {
        return "Prestamo:" + "\n\tId: " + id + "\n\tLibro: " + libro + "\n\tSocio: " + socio + "\n\tFecha Prestamo: " + fechaPrestamo + "\n\tFecha Devolucion: " + fechaDevolucion;
    }
}
