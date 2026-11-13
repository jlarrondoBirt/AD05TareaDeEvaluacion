package com.example.films.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.films.domain.Director;
import com.example.films.domain.Pelicula;
import com.example.films.service.DirectorService;
import com.example.films.service.PeliculaService;

@Controller
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private DirectorService directorService;

    // Mostrar todas las películas
    @GetMapping("/peliculas")
    public String listarPeliculas(Model model) {

        model.addAttribute("peliculas", peliculaService.listarPeliculas());

        return "peliculas/lista";
    }

    // Mostrar formulario para crear una película
    @GetMapping("/peliculas/nuevo")
    public String nuevaPelicula(Model model) {

        model.addAttribute("pelicula", new Pelicula());

        model.addAttribute("directores", directorService.listarDirectores());

        return "peliculas/formulario";
    }

    // Guardar una película
    @PostMapping("/peliculas/guardar")
    public String guardarPelicula(
            @ModelAttribute Pelicula pelicula,
            @RequestParam Integer directorId) {

        Director director = directorService.buscarDirector(directorId).orElse(null);

        if (director == null) {
            return "redirect:/peliculas/nuevo";
        }

        pelicula.setDirector(director);
        peliculaService.guardarPelicula(pelicula);

        return "redirect:/peliculas";
    }

    // Mostrar formulario para editar una película
    @GetMapping("/peliculas/editar/{id}")
    public String editarPelicula(
            @PathVariable Integer id, Model model) {

        Pelicula pelicula = peliculaService.buscarPelicula(id).orElse(null);

        if (pelicula == null) {
            return "redirect:/peliculas";
        }

        model.addAttribute("pelicula", pelicula);

        model.addAttribute("directores", directorService.listarDirectores());

        return "peliculas/formulario";
    }

    // Eliminar una película
    @PostMapping("/peliculas/eliminar/{id}")
    public String eliminarPelicula(@PathVariable Integer id) {

        peliculaService.eliminarPelicula(id);

        return "redirect:/peliculas";
    }
}
