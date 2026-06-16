package com.github.elkataDev.bytebeat.repository;

import com.github.elkataDev.bytebeat.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
