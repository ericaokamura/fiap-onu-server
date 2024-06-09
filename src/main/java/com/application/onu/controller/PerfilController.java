package com.application.onu.controller;


import com.application.onu.model.dto.PerfilDTO;
import com.application.onu.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PutMapping("/")
    public ResponseEntity<PerfilDTO> atualizarPerfilDeUsuario(@RequestBody PerfilDTO perfilDTO) {
        return ResponseEntity.ok(perfilService.atualizarPerfil(perfilDTO));
    }

    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<PerfilDTO> retornarPerfilDeUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        return ResponseEntity.ok(perfilService.retornarPerfil(nomeUsuario));
    }
}
