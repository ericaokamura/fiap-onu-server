package com.application.onu.controller;

import com.application.onu.model.dto.AmizadeDTO;
import com.application.onu.service.AmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amizade")
public class AmizadeController {

    @Autowired
    private AmizadeService amizadeService;

    @PostMapping("/")
    public ResponseEntity<AmizadeDTO> salvarAmizade(@RequestBody AmizadeDTO amizadeDto) {
        return ResponseEntity.ok(amizadeService.salvarAmizade(amizadeDto));
    }

    @PutMapping("/")
    public ResponseEntity<AmizadeDTO> removerAmizade(@RequestBody AmizadeDTO amizadeDto) {
        return ResponseEntity.ok(amizadeService.removerAmizade(amizadeDto));
    }

    @GetMapping("/{seguidor}/seguidor/{seguido}/seguido")
    public ResponseEntity<AmizadeDTO> verificarExistenciaAmizade(@PathVariable("seguidor") String seguidor, @PathVariable("seguido") String seguido) {
        return ResponseEntity.ok(amizadeService.verificarExistenciaAmizade(seguidor, seguido));
    }

}
