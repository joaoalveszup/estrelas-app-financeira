package br.com.zup.estrelas.financas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.repository.SugestaoService;



@RequestMapping("/sugestoes")
public class SugestaoController {

    @Autowired
    SugestaoService services;

    @PostMapping
    public Sugestao insereSugestao(@RequestBody Sugestao sugestao) {
        return this.services.save(sugestao);
    }

    @GetMapping(path = "/{sugestao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sugestao buscaSugestao(@PathVariable Long sugestao) {
        return services.findById(sugestao).get();

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Sugestao> buscaSugestao() {
        return (List<Sugestao>) services.findAll();

    }

    @DeleteMapping("/idSugestao")
    public void delete(@PathVariable Long Sugestao) {
        services.deleteById(null);
    }



}

