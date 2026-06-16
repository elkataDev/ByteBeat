package com.github.elkataDev.bytebeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.elkataDev.bytebeat.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
