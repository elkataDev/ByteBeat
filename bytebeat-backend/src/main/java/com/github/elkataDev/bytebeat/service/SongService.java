package com.github.elkataDev.bytebeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.elkataDev.bytebeat.model.Song;
import com.github.elkataDev.bytebeat.repository.SongRepository;

@Service
public class SongService {

    // Implemento el repositorio para poder usar sus metodos
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    // obtener todas las canciones
    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    // obtener una cancion por su ID
    public Optional<Song> getSongById(Long id){
        return songRepository.findById(id);
    }

    // obtener una cancion por su title
    public Optional<Song> getSongByTitle(String title){
        return songRepository.findByTitle(title);
    }
    
    /**
     * Logica de negocio
     * Contador de reproducciones "plays_count"
     */

    public void incrementPlaysCount(Long songId){

        // validamos que la cancion exista y si existe la guardamos
        Song song = songRepository.findById(songId).orElseThrow(()-> new RuntimeException("Error: La cancion no existe"));

        song.setPlaysCount(song.getPlaysCount() + 1);
    }
}
