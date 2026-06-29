package com.github.elkataDev.bytebeat.dto;

import com.github.elkataDev.bytebeat.model.Album;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AlbumDTO {
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private String coverImageUrl;
    private Long artistId;      // Simplificado a ID plano
    private String artistName;  // Simplificado a String plano

    public static AlbumDTO fromEntity(Album album) {
        if (album == null) return null;

        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setReleaseDate(album.getReleaseDate());
        dto.setCoverImageUrl(album.getCoverImageUrl());
        
        if (album.getArtist() != null) {
            dto.setArtistId(album.getArtist().getId());
            dto.setArtistName(album.getArtist().getName());
        }
        
        return dto;
    }
}