package com.edsonluiz.controller;

import com.edsonluiz.domain.Anime;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"v1/animes", "v1/animes/"})
public class AnimeController {
    @GetMapping()
    public List<Anime> list(@RequestParam(required = false) String name) {
        List<Anime> animes = Anime.getAnimes();
        if (name == null) {
            return animes;
        }
        return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Anime findById(@PathVariable Long id) {
        if (id == null) {
            return null;
        }

        return Anime.getAnimes().stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
