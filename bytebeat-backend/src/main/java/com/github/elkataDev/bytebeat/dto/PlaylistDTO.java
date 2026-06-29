package com.github.elkataDev.bytebeat.dto;

import com.github.elkataDev.bytebeat.model.Playlist;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PlaylistDTO {
    private Long id;
    private String name;
    private UserDTO owner; // El propietario mapeado a su DTO seguro
    private List<UserDTO> collaborators = new ArrayList<>(); // Lista de colaboradores segura
    private List<SongDTO> songs = new ArrayList<>(); // Lista de canciones segura

    public static PlaylistDTO fromEntity(Playlist playlist) {
        if (playlist == null) return null;

        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(playlist.getId());
        dto.setName(playlist.getName());
        dto.setOwner(UserDTO.fromEntity(playlist.getOwner()));
        
        if (playlist.getCollaborators() != null) {
            dto.setCollaborators(playlist.getCollaborators().stream()
                    .map(UserDTO::fromEntity)
                    .collect(Collectors.toList()));
        }
        
        if (playlist.getSongs() != null) {
            dto.setSongs(playlist.getSongs().stream()
                    .map(SongDTO::fromEntity)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
}