package com.example.resourcesapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/resources")
public class ResourceController {

    @GetMapping
    public ResponseEntity<String[]> getMovies() {
        return new ResponseEntity<>(new String[]{"Tarô do jongas", "Zezin el buezo"}, HttpStatus.OK);
    }

    @GetMapping(value = "/error")
    public ResponseEntity getMoviesError() {
        return new ResponseEntity<>("Error test", HttpStatus.BAD_REQUEST);
    }
}
