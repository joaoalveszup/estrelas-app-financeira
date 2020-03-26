package br.com.zup.estrelas.financas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired

    AvaliacaoService service;

    @PostMapping
    public Avaliacao insereAvaliacao(@RequestBody Avaliacao avaliacao) {
        return this.service.insereAvaliacao(avaliacao);

    }

    @GetMapping(path = "/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Avaliacao buscaAvaliacao(@PathVariable Long idAvaliacao) {
        return this.service.buscaAvaliacao(idAvaliacao);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Avaliacao> buscaAvaliacoes() {
        return (List<Avaliacao>) service.buscaAvaliacoes();

    }

    @DeleteMapping(path = "/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaAvaliacao(@PathVariable Long idAvaliacao) {
        this.service.deletaAvaliacao(idAvaliacao);
    }

    @PutMapping(path = "/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Avaliacao alteraAvaliacao(@PathVariable Long idAvaliacao,
            @RequestBody Avaliacao avaliacao) {
        return this.service.alteraAvaliacao(idAvaliacao, avaliacao);

    }

}
