package com.github.elkataDev.bytebeat.repository;

import com.github.elkataDev.bytebeat.model.Playlist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    
    // Genera un 'SELECT * FROM artists WHERE title = ?'
    // Usamos Optional por si alguien busca un usuario que NO existe en la base de
    // datos (ej: "pepito garcia")
    Optional<Playlist> findByName(String name);
}
