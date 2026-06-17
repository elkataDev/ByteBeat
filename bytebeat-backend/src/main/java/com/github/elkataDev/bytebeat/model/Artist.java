package com.github.elkataDev.bytebeat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity // Mapea esta clase como una tabla de base de datos
@Table(name = "artists") // Fuerza el nombre de la tabla en MySQL
@Setter // Genera los setter automaticamente
@Getter // Genera los getters automaticamente
@NoArgsConstructor // Genera un constructor vacio (OBLIGATORIO para Hibernate)
public class Artist {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura el campo como AUTO_INCREMENT en MySQL
    private Long id; // Siempre usamos objeto (Long) por si el ID es null antes de persistir

    @Column(name = "name", nullable = false, length = 100) // Campo obligatorio (NOT NULL), varchar(100)
    private String name;

    @Column(name = "biography", columnDefinition = "TEXT") // Cambia el tipo varchar por TEXT para descripciones largas
    private String biography;

    @Column(name = "profile_picture_url") // Mapea camelCase de Java a snake_case en la DB por defecto
    private String profilePictureUrl;

    @Column(name = "total_plays", nullable = false)  // Contador para las reproducciones totales del artista
    private int totalPlays = 0; // Lo inicializamos en 0 por defecto

    // OPCIONAL: Sirve para que puedas instanciar objetos nuevos rápidamente antes de meterlos en la base de datos por ejemplo un POST
    // En este caso crear artistas nuevos a mano (ej. en un POST) sin escribir IDs ya que son incrementados
    // Constructor sobrecargado: Para instanciar y persistir rápido en código
    
    public Artist(String name, String biography, String profilePictureUrl) {
        this.name = name;
        this.biography = biography;
        this.profilePictureUrl = profilePictureUrl;
        this.totalPlays = 0; // Inicializado a 0
    }
}