package com.github.elkataDev.bytebeat.repository;

import com.github.elkataDev.bytebeat.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
