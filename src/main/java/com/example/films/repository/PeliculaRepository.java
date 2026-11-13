package com.example.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.films.domain.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

}
