package com.github.elkataDev.bytebeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.elkataDev.bytebeat.model.User;
import com.github.elkataDev.bytebeat.repository.UserRepository;

@Service
public class UserService {

    // Implemento el repositorio para poder usar sus metodos
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Obtener un usuario por su username
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
