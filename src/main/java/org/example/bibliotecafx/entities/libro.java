package org.example.bibliotecafx.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "libro")
public class libro implements Serializable {

    @Id
    private Integer isbn;

    private String titulo;

    private String autor;

    private String editorial;

    private int anoPublicacion;

    // Constructor

    public libro() {}

    public libro(Integer isbn, int anoPublicacion, String autor, String editorial, String titulo) {
        this.isbn = isbn;
        this.anoPublicacion = anoPublicacion;
        this.autor = autor;
        this.editorial = editorial;
        this.titulo = titulo;
    }

    // Getters y setters
    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    @Override
    public String toString() {
        return "Libro [ISBN=" + isbn + ", Título=" + titulo + ", Autor=" + autor + ", Editorial=" + editorial + ", Año de Publicación=" + anoPublicacion + "]";
    }
}
