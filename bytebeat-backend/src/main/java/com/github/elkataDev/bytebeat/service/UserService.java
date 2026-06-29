package com.github.elkataDev.bytebeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.elkataDev.bytebeat.model.Artist;
import com.github.elkataDev.bytebeat.model.User;
import com.github.elkataDev.bytebeat.repository.ArtistRepository;
import com.github.elkataDev.bytebeat.repository.UserRepository;

@Service
public class UserService {

    // Implemento el repositorio para poder usar sus metodos
    public final UserRepository userRepository;
    public final ArtistRepository artistRepository;

    public UserService(UserRepository userRepository, ArtistRepository artistRepository) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
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

    /**
     * Logica de negocio (Tipo Interruptor / Toggle)
     * Si el usuario no sigue al artista, lo añade. Si ya lo seguia, le da unfollow.
     */

    public Artist toggleArtistFollowWithId(Long userId, Long artistId){

        // Validar el usuario y guardarlo
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Error: El usuario no existe"));

        // Validar el artista y guardarlo
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new RuntimeException("Error: El artista no existe"));

        // Bloque condicional limpio (Toggle)
        if (user.getFollowedArtists().contains(artist)) {
            // Ya lo seguía -> Procedemos a realizar un UNFOLLOW
            user.getFollowedArtists().remove(artist); // Lo quitamos de la lista del usuario
            artist.setTotalFollowers(artist.getTotalFollowers() - 1); // Restamos 1 al contador global del artista
        } else {
            // No lo seguía -> Procedemos a realizar un FOLLOW
            user.getFollowedArtists().add(artist); // Lo añadimos a la lista del usuario
            artist.setTotalFollowers(artist.getTotalFollowers() + 1); // Sumamos 1 al contador global del artista
        }

        // Actualizo la BD
        artistRepository.save(artist);
        userRepository.save(user);
        
        return artist;
    }
}
