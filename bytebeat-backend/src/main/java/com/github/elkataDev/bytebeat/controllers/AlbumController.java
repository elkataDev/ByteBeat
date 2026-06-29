package com.github.elkataDev.bytebeat.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elkataDev.bytebeat.model.Album;
import com.github.elkataDev.bytebeat.service.AlbumService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/album")
public class AlbumController  {
    

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    /**
     * Endpoint híbrido para leer álbumes de la plataforma.
     * 1. URL limpia: "/api/albums" -> Devuelve la lista completa de álbumes cargados.
     * 2. URL con parámetro: "/api/albums?title=MOTOMAMI" -> Busca un álbum por su título exacto.
     */
    @GetMapping
    public ResponseEntity<?> getAlbums(@RequestParam(required = false) String title) {
        
        // Caso A: Si el cliente decide filtrar usando '?title=...' en la barra de direcciones
        if (title != null && !title.isEmpty()) {
            Optional<Album> albumOpt = albumService.getAlbumBytitle(title);
            
            if (albumOpt.isPresent()) {
                return ResponseEntity.ok(albumOpt.get()); // Retorna 200 OK junto al objeto JSON del álbum
            } else {
                return ResponseEntity.status(404).body("Error: Álbum no encontrado con el título: " + title); // Retorna 404 Not Found
            }
        }

        // Caso B: Si la petición va directa a la ruta raíz sin parámetros
        List<Album> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums); // Retorna la lista de todos los álbumes
    }

    /**
     * Endpoint para obtener un álbum en particular mediante su ID numérico.
     * URL de acceso: "/api/albums/2"
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlbumById(@PathVariable Long id) {
        Optional<Album> albumOpt = albumService.getAlbumById(id);
        
        if (albumOpt.isPresent()) {
            return ResponseEntity.ok(albumOpt.get()); // Envía el álbum encontrado en formato JSON
        } else {
            return ResponseEntity.status(404).body("Error: No existe ningún álbum registrado con el ID: " + id);
        }
    }
}