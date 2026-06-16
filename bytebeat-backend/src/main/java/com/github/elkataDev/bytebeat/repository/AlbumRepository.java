package com.github.elkataDev.bytebeat.repository;

import com.github.elkataDev.bytebeat.model.Album;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    // Genera un 'SELECT * FROM albums WHERE name = ?'
    // Usamos Optional por si alguien busca un album que NO existe en la base de
    // datos (ej: "albumInventado")
    Optional<Album> findByTitle(String title);

}
