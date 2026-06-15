package com.github.elkataDev.bytebeat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Song {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura el campo como AUTO_INCREMENT en MySQL
    private Long id; // Siempre usamos objeto (Long) por si el ID es null antes de persistir

    @Column(name = "title", nullable = false, length = 50) // Campo obligatorio (NOT NULL), varchar(50)
    private String title;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "audio_url", nullable = false) // Campo obligatorio (NOT NULL)
    private String audioUrl;

    @Column(name = "track_number", nullable = false) // Campo obligatorio (NOT NULL)
    private int trackNumber;

    @Column(name = "plays_count")
    private int playsCount;

    // Muchas canciones pertenecen a un solo Objeto Álbum
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false) // Une esta tabla con 'albums' mediante clave foránea album_id
    private Album album;

    // Constructor opcional para crear álbumes a mano en tu código (POST / Seeds)
    public Song(String title, int duration, String audioUrl, int trackNumber, int playsCount, Album album) {
        this.title = title;
        this.duration = duration;
        this.audioUrl = audioUrl;
        this.trackNumber = trackNumber;
        this.playsCount = playsCount;
        this.album = album;
    }
}
