package com.github.elkataDev.bytebeat.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elkataDev.bytebeat.model.Artist;
import com.github.elkataDev.bytebeat.service.UserService;

@RestController // Hace que devuelva JSON automáticamente
@RequestMapping("/api/users") // La URL base
public class UserController {

    private final UserService userService; // Su conexión con el cerebro

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/follow/{artistId}") // URL final: /api/users/5/follow/2
    public ResponseEntity<?> followArtist(@PathVariable Long userId, @PathVariable Long artistId) {
        // Llama al servicio 
        Artist artist = userService.toggleArtistFollowWithId(userId, artistId);
        
        // Devuelve el JSON al navegador con un código 200 OK
        return ResponseEntity.ok(artist); 
    }
}