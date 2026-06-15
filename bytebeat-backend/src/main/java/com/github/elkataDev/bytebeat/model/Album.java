package com.github.elkataDev.bytebeat.model;

import java.util.List;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "albums")
@NoArgsConstructor // OBLIGATORIO para Hibernate (Constructor vacio)
@Setter
@Getter
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT en MySQL
    private Long id;

    @Column(name = "title", nullable = false, length = 50) // NOT NULL, varchar(50)
    private String title;

    @Column(name = "release_date", nullable = false) // Mapea a tipo DATE en MySQL
    private LocalDate releaseDate;

    @Column(name = "cover_image_url", nullable = false)
    private String coverImageUrl;

    // Relación de clave foránea con la tabla de artistas ( Muchos álbumes
    // pertenecen a un Artista 1-M )
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false) // artist_id será la columna FK en MySQL
    private Artist artist;

    @OneToMany(mappedBy = "album") // "mappedBy" indica que la relación ya la manda el atributo "album" en la clase song
    private List<Song> songs;

    // Constructor opcional para crear álbumes a mano en tu código (POST / Seeds)
    public Album(String title, LocalDate releaseDate, String coverImageUrl, Artist artist) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.coverImageUrl = coverImageUrl;
        this.artist = artist;
    }
}