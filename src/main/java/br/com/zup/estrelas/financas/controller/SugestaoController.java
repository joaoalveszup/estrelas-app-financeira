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
import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.service.SugestaoService;


@RestController
@RequestMapping("/sugestoes")
public class SugestaoController {

    @Autowired
    SugestaoService sugestaoService;

    @PostMapping
    public Sugestao insereSugestao(@RequestBody Sugestao sugestao) {
        return this.sugestaoService.insereSugestao(sugestao);
    }

    @GetMapping(path = "/{idSugestao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sugestao buscaSugestao(@PathVariable Long idSugestao) {
        return this.sugestaoService.buscaSugestao(idSugestao);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Sugestao> buscaSugestoes() {
        return this.sugestaoService.buscaSugestoes();
    }

    @DeleteMapping(path = "/{idSugestao}", produces = {MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    public void deleteSugestao(@PathVariable Long idSugestao) {
        this.sugestaoService.deleteSugestao(idSugestao);
    }

    @PutMapping(path = "/{idSugestao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sugestao alteraSugestao(@PathVariable Long idSugestao, @RequestBody Sugestao sugestao) {
        return this.sugestaoService.alteraSugestao(idSugestao, sugestao);
    }

}
