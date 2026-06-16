package com.github.elkataDev.bytebeat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.elkataDev.bytebeat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Genera un 'SELECT * FROM artists WHERE name = ?'
    // Usamos Optional por si alguien busca un usuario que NO existe en la base de
    // datos (ej: "pepito garcia")
    Optional<User> findByUsername(String username);
}
