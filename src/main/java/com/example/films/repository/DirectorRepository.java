package com.example.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.films.domain.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer> {

}
