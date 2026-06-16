package com.github.elkataDev.bytebeat.repository;

import com.github.elkataDev.bytebeat.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    // Genera un 'SELECT * FROM artists WHERE name = ?'
    // Usamos Optional por si alguien busca un artista que NO existe en la base de
    // datos (ej: "Bad Bunny")
    Optional<Artist> findByName(String name);
}