package com.application.onu.controller;

import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.model.dto.DoacaoDTO;
import com.application.onu.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<List<DoacaoDTO>> retornarDoacoesPorUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        List<DoacaoDTO> doacoes = doacaoService.retornarTodasDoacoesPorUsuario(nomeUsuario);
        return ResponseEntity.ok(doacoes);
    }

    @PostMapping()
    public ResponseEntity<DoacaoDTO> salvarDoacao(@RequestBody DoacaoDTO doacaoDTO) {
        DoacaoDTO dto = doacaoService.salvarDoacao(doacaoDTO);
        return ResponseEntity.created(URI.create("")).build();
    }
}
