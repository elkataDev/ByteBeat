package com.github.elkataDev.bytebeat.dto;

import com.github.elkataDev.bytebeat.model.Song;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {
    private Long id;
    private String title;
    private int duration;
    private int playsCount;
    private String albumTitle; 
    
    public static SongDTO fromEntity(Song song) {
        if (song == null) return null;

        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setDuration(song.getDuration());
        dto.setPlaysCount(song.getPlaysCount());
        
        if (song.getAlbum() != null) {
            dto.setAlbumTitle(song.getAlbum().getTitle());
        }
        
        return dto;
    }
}