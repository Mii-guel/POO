package com.mycompany.comiccollector; 

public class Comic {
    private int id; // Identificador único del cómic
    private String titulo; // Título del cómic
    private String editorial; // Editorial que publicó el cómic
    private String tipo; // Tipo
    private String autor; // Autor principal del cómic
    private int anoPublicacion; // Año en que se publicó el cómic

    
    public Comic(int id, String titulo, String editorial, String tipo, String autor, int anoPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.editorial = editorial;
        this.tipo = tipo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
    }

    // Métodos Getters para obtener la información de un Comic

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    // Métodos Setters para modificar la información de un Comic

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Titulo: " + titulo + ", Editorial: " + editorial +
               ", Tipo: " + tipo + ", Autor: " + autor + ", Año: " + anoPublicacion;
    }
}