package com.example.films.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.films.domain.Director;
import com.example.films.service.DirectorService;

@Controller
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    // Mostrar todos los directores
    @GetMapping("/directores")
    public String listarDirectores(Model model) {
        model.addAttribute("directores", directorService.listarDirectores());

        return "directores/lista";
    }

    // Mostrar formulario para crear un director
    @GetMapping("/directores/nuevo")
    public String nuevoDirector(Model model) {
        model.addAttribute("director", new Director());

        return "directores/formulario";
    }

    // Guardar un director
    @PostMapping("/directores/guardar")
    public String guardarDirector(@ModelAttribute Director director) {

        if (director.getIdDirector() != null) {

            Director existente = directorService.buscarDirector(director.getIdDirector()).orElse(null);

            if (existente == null) {
                return "redirect:/directores";
            }

            existente.setNombre(director.getNombre());
            existente.setApellido(director.getApellido());
            existente.setNacionalidad(director.getNacionalidad());
            existente.setEdad(director.getEdad());

            directorService.guardarDirector(existente);

        } else {
            directorService.guardarDirector(director);
        }

        return "redirect:/directores";
    }

    // Mostrar formulario para editar un director
    @GetMapping("/directores/editar/{id}")
    public String editarDirector(
            @PathVariable Integer id, Model model) {

        Director director = directorService.buscarDirector(id).orElse(null);

        if (director == null) {
            return "redirect:/directores";
        }

        model.addAttribute("director", director);

        return "directores/formulario";
    }

    // Eliminar un director
    @PostMapping("/directores/eliminar/{id}")
    public String eliminarDirector(@PathVariable Integer id) {

        directorService.eliminarDirector(id);

        return "redirect:/directores";
    }
}

