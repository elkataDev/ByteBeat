package com.github.elkataDev.bytebeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.elkataDev.bytebeat.model.Playlist;
import com.github.elkataDev.bytebeat.repository.PlaylistRepository;

@Service
public class PlaylistService {

    // Implemento el repositorio para poder usar sus metodos
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    // Obtener todas las playlist
    public List<Playlist> getAllPlaylist() {
        return playlistRepository.findAll();
    }

    // Obtener una playlist por su ID
    public Optional<Playlist> getPlaylistById(Long id) {
        return playlistRepository.findById(id);
    }

    // Obtener una playlist por nombre
    public Optional<Playlist> getPlaylistByName(String name) {
        return playlistRepository.findByName(name);
    }
}
