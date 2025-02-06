package org.example.bibliotecafx.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table (name = "libro")
public class libro implements Serializable {

    @Id
    private Integer ISBN;
    private String titulo;

    @Column(nullable = false)
    private org.example.bibliotecafx.entities.autor autor;
    private String editorial;
    private Integer anioPublicacion;

    public libro() {}

    public libro(Integer ISBN, String titulo, org.example.bibliotecafx.entities.autor autor, String editorial, Integer anioPublicacion) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
    }

    public Integer getISBN() {return ISBN;}
    public void setISBN(Integer ISBN) {this.ISBN = ISBN;}
    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public String getEditorial() {return editorial;}
    public void setEditorial(String editorial) {this.editorial = editorial;}
    public Integer getAnioPublicacion() {return anioPublicacion;}
    public void setAnioPublicacion(Integer anioPublicacion) {this.anioPublicacion = anioPublicacion;}
    public org.example.bibliotecafx.entities.autor getAutor() {return autor;}
    public void setAutor(org.example.bibliotecafx.entities.autor autor) {this.autor = autor;}

    @Override
    public String toString() {
        return "Libro: " + "\n\tISBN: " + ISBN + "\n\tTitulo: " + titulo + "\n\tautor.java: " + autor + "\n\tEditorial: " + editorial + "\n\tAnyo de Publicacion: " + anioPublicacion;
    }
}
