package com.application.onu.controller;

import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.model.dto.VendaDTO;
import com.application.onu.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<List<VendaDTO>> retornarTodasVendasPorUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        try {
            List<VendaDTO> vendas = vendaService.retornarTodasVendasPorUsuario(nomeUsuario);
            return ResponseEntity.ok(vendas);
        } catch (UsuarioNaoEncontradoException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<VendaDTO> salvarVenda(@RequestBody VendaDTO vendaDTO) {
        VendaDTO dto = vendaService.salvarVenda(vendaDTO);
        return ResponseEntity.ok(dto);
    }
}
