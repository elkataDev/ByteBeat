package com.github.elkataDev.bytebeat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.elkataDev.bytebeat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Genera un 'SELECT * FROM users WHERE username = ?'
    // Usamos Optional por si alguien busca un usuario que NO existe en la base de
    // datos (ej: "pepito garcia")
    Optional<User> findByUsername(String username);

    // Genera un 'SELECT * FROM users WHERE email = ?'
    // Usamos Optional por si alguien busca un usuario que NO existe en la base de
    // datos (ej: "pepitogarcia@gmail.com")
    Optional<User> findByEmail(String email);
}
