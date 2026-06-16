package com.github.elkataDev.bytebeat.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "playlist")
@Setter
@Getter
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT en MySQL
    private Long id;

    @Column(name = "name", nullable = false, length = 60) // NOT NULL, varchar(60)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT") // tipo Text
    private String description;

    @Column(name = "is_private", nullable = false) // Hibernate ya mapea los primitivos boolean a BOOLEAN en MySQL automáticamente
    private boolean isPrivate;

     /**
    * Entidad que representa una lista de reproducción (Playlist) en la plataforma.
    * Implementa un modelo híbrido de permisos basado en Spotify:
    * 1. Propietario Único (ManyToOne): Un único usuario es el dueño absoluto y creador de la lista.
    * 2. Colaboradores Múltiples (ManyToMany): Permite asociar una lista de usuarios invitados con permisos de edición para añadir o eliminar canciones.
    * 3. Contenido (ManyToMany): Relación intermedia que gestiona las canciones incluidas en la playlist.
     */

    // Dueño Playlist: Muchas playlist pertenecen a un solo Objeto Usuario
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // Renombrado a owner_id para dejar claro que es el dueño
    private User owner;

    // Colaborador: Muchos usuarios pueden colaborar en muchas playlists
    @ManyToMany
    @JoinTable(name = "playlist_collaborators", // Tabla intermedia para los invitados
            joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> collaborators; // Lista de usuarios con permisos de edición

    // canciones de la playlist
    @ManyToMany
    @JoinTable(name = "playlist_songs", // Tabla intermedia para el contenido de la lista
            joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;


    // Constructor para crear listas nuevas (nace con un dueño, pero sin colaboradores ni canciones aún)
    public Playlist(String name, String description, boolean isPrivate, User owner) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.owner = owner;
    }
}