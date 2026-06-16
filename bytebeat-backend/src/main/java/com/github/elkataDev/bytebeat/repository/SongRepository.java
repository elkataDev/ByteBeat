package com.github.elkataDev.bytebeat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.elkataDev.bytebeat.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

    // Genera un 'SELECT * FROM songs WHERE name = ?'
    // Usamos Optional por si alguien busca una cancion que NO existe en la base de
    // datos (ej: "cancionInventada")
    Optional<Song> findByTitle(String title);
}
