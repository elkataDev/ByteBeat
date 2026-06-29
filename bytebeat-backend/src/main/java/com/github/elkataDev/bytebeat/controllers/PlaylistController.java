package com.github.elkataDev.bytebeat.controllers;

import com.github.elkataDev.bytebeat.model.Playlist;
import com.github.elkataDev.bytebeat.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    /**
     * URL: "/api/playlists" o "/api/playlists?name=Mi+Mix"
     * Obtener todas las playlists o filtrar una por nombre.
     */
    @GetMapping
    public ResponseEntity<?> getPlaylists(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            Optional<Playlist> playlistOpt = playlistService.getPlaylistByName(name);
            if (playlistOpt.isPresent()) {
                return ResponseEntity.ok(playlistOpt.get());
            } else {
                return ResponseEntity.status(404).body("Error: Playlist no encontrada con el nombre: " + name);
            }
        }
        List<Playlist> playlists = playlistService.getAllPlaylist();
        return ResponseEntity.ok(playlists);
    }

    /**
     * URL: "/api/playlists/3"
     * Obtener una playlist específica por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long id) {
        Optional<Playlist> playlistOpt = playlistService.getPlaylistById(id);
        if (playlistOpt.isPresent()) {
            return ResponseEntity.ok(playlistOpt.get());
        } else {
            return ResponseEntity.status(404).body("Error: No existe ninguna playlist con el ID: " + id);
        }
    }

    /**
     * URL: "/api/playlists/3/songs/12?userId=5"
     * Añade la canción 12 a la playlist 3. El userId comprueba los permisos mediante tu guardián.
     */
    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> addSongToPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long songId,
            @RequestParam Long userId) {
        try {
            Playlist updatedPlaylist = playlistService.addSongPlaylist(songId, playlistId, userId);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage()); // 403 Forbidden (Permiso denegado) o 404
        }
    }

    /**
     * URL: "/api/playlists/3/songs/12?userId=5"
     * Quita la canción 12 de la playlist 3.
     */
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> removeSongFromPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long songId,
            @RequestParam Long userId) {
        try {
            Playlist updatedPlaylist = playlistService.removeSongPlaylist(songId, playlistId, userId);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    /**
     * URL: "/api/playlists/3/collaborators/id/8?ownerId=1"
     * Invita al usuario 8 a colaborar en la playlist 3. Solo el ownerId=1 (dueño) puede hacerlo.
     */
    @PostMapping("/{playlistId}/collaborators/id/{newCollaboratorId}")
    public ResponseEntity<?> addCollaboratorById(
            @PathVariable Long playlistId,
            @PathVariable Long newCollaboratorId,
            @RequestParam Long ownerId) {
        try {
            Playlist updatedPlaylist = playlistService.addCollaboratorWithId(playlistId, newCollaboratorId, ownerId);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * URL: "/api/playlists/3/collaborators/id/8?ownerId=1"
     * Elimina al colaborador 8 de la playlist 3.
     */
    @DeleteMapping("/{playlistId}/collaborators/id/{collaboratorId}")
    public ResponseEntity<?> removeCollaboratorById(
            @PathVariable Long playlistId,
            @PathVariable Long collaboratorId,
            @RequestParam Long ownerId) {
        try {
            Playlist updatedPlaylist = playlistService.removeCollaboratorWithId(playlistId, collaboratorId, ownerId);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * URL: "/api/playlists/3/collaborators/email/amigo@gmail.com?ownerEmail=dueno@gmail.com"
     * Invita a colaborar usando strings (emails) en lugar de IDs numéricos.
     */
    @PostMapping("/{playlistId}/collaborators/email/{newCollaboratorEmail}")
    public ResponseEntity<?> addCollaboratorByEmail(
            @PathVariable Long playlistId,
            @PathVariable String newCollaboratorEmail,
            @RequestParam String ownerEmail) {
        try {
            Playlist updatedPlaylist = playlistService.addCollaboratorWithEmail(playlistId, newCollaboratorEmail, ownerEmail);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    /**
     * URL: "/api/playlists/3/collaborators/email/amigo@gmail.com?ownerEmail=dueno@gmail.com"
     * Expulsa a un colaborador usando su email.
     */
    @DeleteMapping("/{playlistId}/collaborators/email/{collaboratorEmail}")
    public ResponseEntity<?> removeCollaboratorByEmail(
            @PathVariable Long playlistId,
            @PathVariable String collaboratorEmail,
            @RequestParam String ownerEmail) {
        try {
            Playlist updatedPlaylist = playlistService.removeCollaboratorWithEmail(playlistId, collaboratorEmail, ownerEmail);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}