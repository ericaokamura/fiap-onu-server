package com.application.onu.controller;

import com.application.onu.model.dto.ProdutoDTO;
import com.application.onu.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/")
    public ResponseEntity<ProdutoDTO> adicionarProdutoEmCarrinhoDeUsuario(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO retorno = produtoService.adicionarProdutoEmCarrinhoDeUsuario(produtoDTO);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/{carrinhoId}/carrinho")
    public ResponseEntity<List<ProdutoDTO>> retornarProdutosEmCarrinhoDeUsuario(@PathVariable("carrinhoId") String carrinhoId) {
        List<ProdutoDTO> retorno = produtoService.retornarProdutosEmCarrinhoDeUsuario(carrinhoId);
        return ResponseEntity.ok(retorno);
    }

    @DeleteMapping("/{carrinhoId}/carrinho")
    public ResponseEntity esvaziarCarrinhoDeUsuario(@PathVariable("carrinhoId") String carrinhoId) {
        produtoService.esvaziarCarrinhoDeUsuario(carrinhoId);
        return ResponseEntity.ok().build();
    }
}
