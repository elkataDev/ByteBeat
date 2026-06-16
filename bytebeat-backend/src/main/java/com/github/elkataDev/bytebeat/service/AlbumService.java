package com.github.elkataDev.bytebeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.elkataDev.bytebeat.model.Album;
import com.github.elkataDev.bytebeat.repository.AlbumRepository;

@Service
public class AlbumService {

    // Implemento el repositorio para poder usar sus metodos
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    // obtener todos los albumes
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    // obtener album por ID
    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    // obtener album por su titulo
    public Optional<Album> getAlbumBytitle(String title) {
        return albumRepository.findByTitle(title);
    }
}
