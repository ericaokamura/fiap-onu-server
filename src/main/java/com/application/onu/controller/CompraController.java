package com.application.onu.controller;


import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.model.dto.CompraDTO;
import com.application.onu.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<List<CompraDTO>> retornarComprasPorUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        List<CompraDTO> compras = compraService.retornarComprasPorUsuario(nomeUsuario);
        return ResponseEntity.ok(compras);
    }

    @PostMapping("/")
    public ResponseEntity<CompraDTO> salvarCompra(@RequestBody CompraDTO compraDTO) {
        CompraDTO dto = compraService.salvarCompra(compraDTO);
        return ResponseEntity.ok(dto);
    }
}
