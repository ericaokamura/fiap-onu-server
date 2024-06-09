package com.application.onu.controller;

import com.application.onu.model.dto.SolicitacaoAmizadeDTO;
import com.application.onu.service.SolicitacaoAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacaoAmizade")
public class SolicitacaoAmizadeController {

    @Autowired
    private SolicitacaoAmizadeService solicitacaoAmizadeService;

    @PostMapping()
    public ResponseEntity<SolicitacaoAmizadeDTO> salvarSolicitacaoAmizade(@RequestBody SolicitacaoAmizadeDTO solicitacaoAmizadeDTO) {
        SolicitacaoAmizadeDTO retorno = solicitacaoAmizadeService.salvarSolicitacaoAmizade(solicitacaoAmizadeDTO);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<SolicitacaoAmizadeDTO> atualizarSolicitacaoAmizade(@RequestBody SolicitacaoAmizadeDTO solicitacaoDTO) {
        SolicitacaoAmizadeDTO retorno = solicitacaoAmizadeService.atualizarSolicitacaoAmizade(solicitacaoDTO);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping()
    public ResponseEntity<SolicitacaoAmizadeDTO> aprovarSolicitacaoAmizade(@RequestBody SolicitacaoAmizadeDTO solicitacaoDTO) {
        SolicitacaoAmizadeDTO retorno = solicitacaoAmizadeService.aprovarSolicitacaoAmizade(solicitacaoDTO);
        return ResponseEntity.ok(retorno);
    }

    @DeleteMapping("/solicitante/{solicitanteUsuario}/recebe/{recebeUsuario}")
    public ResponseEntity deletarSolicitacaoAmizade(@PathVariable("solicitanteUsuario") String solicitanteUsuario, @PathVariable("recebeUsuario") String recebeUsuario) {
        solicitacaoAmizadeService.deletarSolicitacaoAmizade(solicitanteUsuario, recebeUsuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nomeUsuario/{nomeUsuario}")
    public ResponseEntity<List<SolicitacaoAmizadeDTO>> retornarSolicitacoesAmizadePorUsuario(@PathVariable("nomeUsuario") String nomeUsuario) {
        List<SolicitacaoAmizadeDTO> retorno = solicitacaoAmizadeService.retornarTodasSolicitacoesAmizadePorUsuario(nomeUsuario);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/solicitante/{solicitanteUsuario}/recebe/{recebeUsuario}")
    public ResponseEntity<SolicitacaoAmizadeDTO> retornarSolicitacaoAmizade(@PathVariable("solicitanteUsuario") String solicitanteUsuario, @PathVariable("recebeUsuario") String recebeUsuario) {
        SolicitacaoAmizadeDTO retorno = solicitacaoAmizadeService.retornarSolicitacaoAmizade(solicitanteUsuario, recebeUsuario);
        return ResponseEntity.ok(retorno);
    }
}
