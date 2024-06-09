package com.application.onu.controller;

import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.model.dto.RecebimentoDTO;
import com.application.onu.service.RecebimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recebimento")
public class RecebimentoController {

    @Autowired
    private RecebimentoService recebimentoService;

    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<List<RecebimentoDTO>> retornarRecebimentosPorUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        try {
            List<RecebimentoDTO> recebimentos = recebimentoService.retornarTodosRecebimentosPorUsuario(nomeUsuario);
            return ResponseEntity.ok(recebimentos);
        } catch (UsuarioNaoEncontradoException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    public ResponseEntity<RecebimentoDTO> salvarRecebimento(@RequestBody RecebimentoDTO recebimentoDTO) {
        RecebimentoDTO dto = recebimentoService.salvarRecebimento(recebimentoDTO);
        return ResponseEntity.created(URI.create("")).build();
    }
}
