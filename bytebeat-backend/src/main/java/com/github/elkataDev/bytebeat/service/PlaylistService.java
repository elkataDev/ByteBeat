package com.github.elkataDev.bytebeat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.github.elkataDev.bytebeat.model.User;
import com.github.elkataDev.bytebeat.model.Playlist;
import com.github.elkataDev.bytebeat.model.Song;
import com.github.elkataDev.bytebeat.repository.PlaylistRepository;
import com.github.elkataDev.bytebeat.repository.SongRepository;
import com.github.elkataDev.bytebeat.repository.UserRepository;

@Service
public class PlaylistService {

    // Implemento el repositorio para poder usar sus metodos
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository,SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
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

    /**
     * Logica de Negocio
     * Añadir un colaborador a una playlist
     * Solo el propietario (owner) puede invitar a otros usuario como colaboradores
     */

    public Playlist addCollaborator(Long playlistId, Long newCollaboratorId, Long ownerId) {

        // Buscamos la playlist donde se va a añadir el colaborador
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Error: Playlist no encontrada"));

        // Validamos que quien invita es el dueño de la playlist
        if (!playlist.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Error: solo el propietario puede añadir colaboradores");
        }

        // Validadamos que el colaborador/usuario que queremos añadir exista
        User newCollaborator = userRepository.findById(newCollaboratorId).orElseThrow(() -> new RuntimeException("El usuario que quieres añadir no existe"));

        // Evitar que el dueño se añada a sí mismo como colaborador
        if (playlist.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("Error: El dueño no se puede añadir a si mismo, ya tiene el control de la playlist");
        }

        // Si ya es colaborador, no lo añadimos
        if (!playlist.getCollaborators().contains(newCollaborator)) {
            playlist.getCollaborators().add(newCollaborator); // Se añade el nuevo colaborador
        }

        // Guardamos/actualizamos la BD
        return playlistRepository.save(playlist);
    }

    /**
     * Validacion de Permisos
     * Comprueba si un usuario tiene permisos para editar una playlist
     */

    public boolean hasWritePermission(Long playlistId, Long userId){
        
        // Buscamos la playlist 
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Error: Playlist no encontrada"));

        // validamos que ese usuario sea o dueño o colaborador
        if (playlist.getOwner().getId().equals(userId)) {
            return true;
        }

        // stream y 'anyMatch' devuelve true en el primer colaborador que coincida con el userId.
        return playlist.getCollaborators().stream().anyMatch(collaborator -> collaborator.getId().equals(userId));
    }

    /**
     * Logica de negocio
     * Añadir una cancion a la playList
     * Solo el dueño y los colaboradores podran añadir canciones
     */
    public Playlist addSongPlaylist (Long songId, Long playlistId, Long userId){

        // Validamos que el usuario tenga permisos de edicion
        if (!hasWritePermission(playlistId, userId)) {
            throw new RuntimeException("Error: No tienes permisos de edicion");
        }

        // Playlist donde se añadirá la cancion
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Error: Playlist no encontrada"));

        // Validamos que la cancion existe y la guardamos
        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Error: La cancion no existe"));

        // Añadimos la cancion a la playlist
        playlist.getSongs().add(song);

        return playlistRepository.save(playlist);
    }
}
