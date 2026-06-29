package com.github.elkataDev.bytebeat.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.elkataDev.bytebeat.model.Artist;
import com.github.elkataDev.bytebeat.service.ArtistService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/artists") // URL base
public class ArtistController  {
    
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

   /**
     * Endpoint híbrido para obtener artistas.
     * 1. Si se entra a "/api/artists" -> Devuelve la lista completa (200 OK).
     * 2. Si se entra a "/api/artists?name=Rosalía" -> Filtra y busca por ese nombre exacto.
     * * @param name (Opcional) Nombre del artista a buscar en la URL
     * @return Respuesta HTTP con el JSON correspondiente o un error 404
     */
    @GetMapping // peticiones HTTP GET (Lectura)
    public ResponseEntity<?> getArtists(@RequestParam(required = false) String name) {
        
        // Caso A: El cliente ha pasado el parámetro '?name=...' en la URL
        if (name != null && !name.isEmpty()) {
            Optional<Artist> artistOpt = artistService.getArtistByName(name);
            
            if (artistOpt.isPresent()) {
                // Si el artista existe, devolvemos un estado 200 OK con el artista dentro
                return ResponseEntity.ok(artistOpt.get());
            } else {
                // Si la caja está vacía, devolvemos un estado 404 Not Found con un mensaje descriptivo
                return ResponseEntity.status(404).body("Error: Artista no encontrado con el nombre: " + name);
            }
        }

        // Caso B: El cliente no ha pasado ningún parámetro (URL limpia: /api/artists)
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists); // Devuelve la lista completa con estado 200 OK
    }
}