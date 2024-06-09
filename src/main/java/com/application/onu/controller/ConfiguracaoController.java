package com.application.onu.controller;

import com.application.onu.model.dto.ConfiguracaoDTO;
import com.application.onu.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;


    @PutMapping("/")
    public ResponseEntity<ConfiguracaoDTO> atualizarConfiguracao(@RequestBody ConfiguracaoDTO configuracaoDTO) {
        ConfiguracaoDTO dto = configuracaoService.atualizarConfiguracao(configuracaoDTO);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/nomeUsuario/{nomeUsuario}")
    public ResponseEntity<ConfiguracaoDTO> retornarConfiguracao(@PathVariable("nomeUsuario") String nomeUsuario) {
        ConfiguracaoDTO dto = configuracaoService.retornarConfiguracao(nomeUsuario);
        return ResponseEntity.ok(dto);
    }
}
