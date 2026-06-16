package com.github.elkataDev.bytebeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.elkataDev.bytebeat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
