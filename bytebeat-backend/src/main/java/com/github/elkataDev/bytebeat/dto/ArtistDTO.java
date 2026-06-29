package com.github.elkataDev.bytebeat.dto;

import com.github.elkataDev.bytebeat.model.Artist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDTO {
    private Long id;
    private String name;
    private String biography;
    private String profilePictureUrl;
    private int totalPlays;
    private int totalFollowers;

    // Método estático para transformar una entidad Artist en un ArtistDTO
    public static ArtistDTO fromEntity(Artist artist) {
        if (artist == null) return null;

        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setName(artist.getName());
        dto.setBiography(artist.getBiography());
        dto.setProfilePictureUrl(artist.getProfilePictureUrl());
        dto.setTotalPlays(artist.getTotalPlays());
        dto.setTotalFollowers(artist.getTotalFollowers());
        return dto;
    }
}