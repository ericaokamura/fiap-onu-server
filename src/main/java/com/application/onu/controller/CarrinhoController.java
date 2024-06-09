package com.application.onu.controller;

import com.application.onu.model.dto.CarrinhoDTO;
import com.application.onu.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/{nomeUsuario}")
    public ResponseEntity<CarrinhoDTO> retornarCarrinhoPorUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        return ResponseEntity.ok(carrinhoService.retornarCarrinhoPorUsuario(nomeUsuario));
    }

    @GetMapping("/{id}/id")
    public ResponseEntity<CarrinhoDTO> retornarCarrinhoPorUsuario(@PathVariable("id") Long id) {
        return ResponseEntity.ok(carrinhoService.retornarCarrinhoPorId(id));
    }
}
