package com.example.films.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.films.domain.Director;
import com.example.films.repository.DirectorRepository;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public List<Director> listarDirectores() {
        return directorRepository.findAll();
    }

    public Optional<Director> buscarDirector(Integer id) {
        return directorRepository.findById(id);
    }

    public Director guardarDirector(Director director) {
        return directorRepository.save(director);
    }

    public void eliminarDirector(Integer id) {
        directorRepository.deleteById(id);
    }
}

