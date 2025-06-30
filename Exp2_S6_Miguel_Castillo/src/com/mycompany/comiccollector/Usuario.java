package com.mycompany.comiccollector;

import java.util.ArrayList; 
import java.util.List;     

public class Usuario {
    private String nombreUsuario; // Nombre único para identificar al usuario
    private String contrasena; // Contraseña del usuario 
    private String email; // Correo electrónico del usuario
    private List<String> comicsReservados; // Una lista para guardar los títulos de los cómics que el usuario ha reservado

    
    public Usuario(String nombreUsuario, String contrasena, String email) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.email = email;
        this.comicsReservados = new ArrayList<>(); // Inicializamos la lista de cómics reservados vacía al crear un usuario
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getComicsReservados() {
        return comicsReservados;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addComicReservado(String tituloComic) {
        this.comicsReservados.add(tituloComic);
    }

    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + ", Email: " + email + ", Comics Reservados: " + comicsReservados.size();
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombreUsuario).append(",");
        sb.append(contrasena).append(",");
        sb.append(email);
        // Añade cada cómic reservado a la cadena
        for (String comic : comicsReservados) {
            sb.append(",").append(comic);
        }
        return sb.toString();
    }
}
