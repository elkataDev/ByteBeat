package com.github.elkataDev.bytebeat.repository;

import com.github.elkataDev.bytebeat.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}