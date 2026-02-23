package com.example.suavez.controller;

import com.example.suavez.entity.Estabelecimento;
import com.example.suavez.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {


    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @GetMapping
    public List<Estabelecimento> listar() {
        return estabelecimentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Estabelecimento> listarApenasUm(@PathVariable Long id) {
        return estabelecimentoRepository.findById(id);
    }

    @PostMapping
    public Estabelecimento criar(@RequestBody Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    @PutMapping("/{id}")
    public Estabelecimento atualizar(@PathVariable Long id, @RequestBody Estabelecimento dadosAtualizados) {
        return estabelecimentoRepository.findById(id)
                .map(estabelecimento -> {
                    estabelecimento.setCnpj(dadosAtualizados.getCnpj());
                    estabelecimento.setNome_responsavel(dadosAtualizados.getNome_responsavel());
                    estabelecimento.setEmail(dadosAtualizados.getEmail());
                    estabelecimento.setNome_empresa(dadosAtualizados.getNome_empresa());
                    estabelecimento.setEndereco(dadosAtualizados.getEndereco());
                    estabelecimento.setSegmento(dadosAtualizados.getSegmento());
                    return estabelecimentoRepository.save(estabelecimento);
                }).orElseGet(() -> estabelecimentoRepository.save(dadosAtualizados));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        estabelecimentoRepository.deleteById(id);
    }
}

