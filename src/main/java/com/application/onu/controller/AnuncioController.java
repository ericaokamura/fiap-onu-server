package com.application.onu.controller;

import com.application.onu.model.dto.AnuncioDTO;
import com.application.onu.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("anuncio")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:8081", maxAge = 3600)
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping()
    public ResponseEntity<AnuncioDTO> salvarAnuncio(@RequestBody AnuncioDTO anuncioDTO) {
        AnuncioDTO retorno = anuncioService.salvarAnuncio(anuncioDTO);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping()
    public ResponseEntity<AnuncioDTO> atualizarAnuncio(@RequestBody AnuncioDTO anuncioDTO) {
        AnuncioDTO retorno = anuncioService.atualizarAnuncio(anuncioDTO);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/")
    public ResponseEntity<List<AnuncioDTO>> retornarAnuncios() {
        List<AnuncioDTO> anuncios = anuncioService.retornarTodosAnuncios();
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/{nomeUsuario}/nomeUsuario")
    public ResponseEntity<List<AnuncioDTO>> retornarAnunciosPorUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        List<AnuncioDTO> anuncios = anuncioService.retornarTodosAnunciosPorUsuario(nomeUsuario);
        return ResponseEntity.ok(anuncios);
    }

    @CrossOrigin
    @GetMapping("/disponibilidade")
    public ResponseEntity<List<AnuncioDTO>> retornarAnunciosPorDisponibilidade() {
        List<AnuncioDTO> anuncios = anuncioService.retornarAnunciosPorDisponibilidade();
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/titulo/{titulo}")
    @CrossOrigin
    public ResponseEntity<List<AnuncioDTO>> retornarAnunciosPorTitulo(@PathVariable("titulo") String titulo) {
        List<AnuncioDTO> anuncios = anuncioService.retornarAnunciosPorTitulo(titulo);
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/preco")
    @CrossOrigin
    public ResponseEntity<List<AnuncioDTO>> ordenarAnunciosPorPreco() {
        List<AnuncioDTO> anuncios = anuncioService.ordenarAnunciosPorPreco();
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/dataHoraPublicacao")
    @CrossOrigin
    public ResponseEntity<List<AnuncioDTO>> ordenarAnunciosPorDataHoraPublicacao() {
        List<AnuncioDTO> anuncios = anuncioService.ordenarAnunciosPorDataHoraPublicacao();
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AnuncioDTO> retornarAnuncioPorId(@PathVariable("id") Long id) {
        AnuncioDTO anuncio = anuncioService.retornarAnuncioPorId(id);
        return ResponseEntity.ok(anuncio);
    }
}
