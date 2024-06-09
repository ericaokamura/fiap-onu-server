package com.application.onu.controller;

import com.application.onu.model.dto.UsuarioDTO;
import com.application.onu.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PutMapping("/")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> retornarUsuario(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.retornarUsuarioPorId(id));
    }

    @CrossOrigin
    @GetMapping("/{nomeUsuario}/nomeUsuario")
    public ResponseEntity<UsuarioDTO> retornarUsuarioPorNome(@PathVariable("nomeUsuario") String nomeUsuario) {
        return ResponseEntity.ok(usuarioService.retornarUsuarioPorNome(nomeUsuario));
    }


    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> retornarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.retornarTodosUsuarios());
    }


}
