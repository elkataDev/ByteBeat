package com.github.elkataDev.bytebeat.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.elkataDev.bytebeat.model.Song;
import com.github.elkataDev.bytebeat.service.SongService;

@RestController
@RequestMapping("/api/song")
public class SongController  {
    
    private final SongService songService;

    public SongController (SongService songService){
        this.songService = songService;
    }

    /**
     * Endpoint híbrido para obtener canciones.
     * 1. URL: "/api/songs" -> Devuelve la lista completa de canciones.
     * 2. URL: "/api/songs?title=Despechá" -> Busca la canción por su título exacto.
     */
    @GetMapping
    public ResponseEntity<?> getSongs(@RequestParam(required = false) String title) {
        
        // Caso A: Si buscan por el parámetro '?title=...'
        if (title != null && !title.isEmpty()) {
            Optional<Song> songOpt = songService.getSongByTitle(title);
            
            if (songOpt.isPresent()) {
                return ResponseEntity.ok(songOpt.get()); // 200 OK + la canción
            } else {
                return ResponseEntity.status(404).body("Error: Canción no encontrada con el título: " + title); // 404
            }
        }

        // Caso B: URL limpia, devolvemos todo
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs); // 200 OK + lista
    }

    /**
     * Endpoint para obtener una canción específica usando su ID directo en la URL.
     * URL: "/api/songs/5"
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getSongById(@PathVariable Long id) {
        Optional<Song> songOpt = songService.getSongById(id);
        
        if (songOpt.isPresent()) {
            return ResponseEntity.ok(songOpt.get());
        } else {
            return ResponseEntity.status(404).body("Error: No existe ninguna canción con el ID: " + id);
        }
    }

    /**
     * LÓGICA DE NEGOCIO: Incrementar el contador de reproducciones (+1).
     * URL: "/api/songs/5/play"
     * Usamos @PostMapping porque estamos generando un cambio/acción en el servidor.
     */
    @PostMapping("/{id}/play")
    public ResponseEntity<String> playSong(@PathVariable Long id) {
        try {
            // suma +1 a la canción y al artista
            songService.incrementPlaysCount(id);
            return ResponseEntity.ok("Reproducción registrada. Contador +1 incrementado con éxito.");
        } catch (RuntimeException e) {
            //  el servicio lanza el error porque el ID no existe
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}