package com.github.elkataDev.bytebeat.service;

import com.github.elkataDev.bytebeat.model.Artist;
import com.github.elkataDev.bytebeat.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    // Implemento el repositorio para poder usar sus metodos 
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // Obtener todos los artistas
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    // Obtener un artista por su ID
    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }

    // Obtener un artista por su nombre exacto
    public Optional<Artist> getArtistByName(String name) {
        return artistRepository.findByName(name); // Metodo del repositorio ArtistRepository
    }
}