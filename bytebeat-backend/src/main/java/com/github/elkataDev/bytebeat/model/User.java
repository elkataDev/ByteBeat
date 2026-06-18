package com.github.elkataDev.bytebeat.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT en MySQL
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 60) // NOT NULL, varchar(60)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100) // NOT NULL, varchar(100)
    private String email;

    @Column(name = "password", nullable = false, length = 60) // NOT NULL, varchar(60)
    private String password;

    @Column(name = "created_at", nullable = false) // Fecha de creación de usuario
    private LocalDateTime createdAt; // LocalDateTime para manejar fechas reales

    @Column(name = "role", nullable = false, length = 20)
    private String role; // Guardará "USER" o "ADMIN"

    /**
     * Relacion M-M 
     * Muchos artistas pueden tener varios seguidores
     * y los seguidores pueden seguir muchos artistas */ 
    @ManyToMany
    @JoinTable(
        name = "user_followed_artists",
        joinColumns = @JoinColumn(name = "user_id"), // Columna para el ID del usuario
        inverseJoinColumns = @JoinColumn(name = "artist_id") // Columna para el ID del artista
    )
    private List<Artist> followedArtists = new ArrayList<>(); // Inicializada para evitar NullPointerException

    // Constructor opcional para crear usuarios nuevos en tu código (ej. Registro)
    public User(String username, String email, String password, LocalDateTime createdAt, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.role = role;
        this.followedArtists = new ArrayList<>(); // Aseguramos inicialización en el constructor
    }
}