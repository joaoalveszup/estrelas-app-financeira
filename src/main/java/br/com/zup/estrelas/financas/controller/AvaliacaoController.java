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
import br.com.zup.estrelas.financas.repository.AvaliacaoRepository;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    AvaliacaoRepository repository;

    @PostMapping
    public Avaliacao insereAvaliacao(@RequestBody Avaliacao avaliacao) {
        return this.repository.save(avaliacao);

    }

    @GetMapping(path = "/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Avaliacao buscaAvaliacao(@PathVariable Long idAvaliacao) {
        return this.repository.findById(idAvaliacao).get();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Avaliacao> buscaAvaliacoes() {
        return (List<Avaliacao>) repository.findAll();

    }

    @DeleteMapping(path = "/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaAvaliacao(@PathVariable Long idAvaliacao) {
        this.repository.deleteById(idAvaliacao);
    }

    @PutMapping(path = "/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Avaliacao alteraAvaliacao(@PathVariable Long idAvaliacao,
            @RequestBody Avaliacao avaliacao) {
        Avaliacao avaliacaoBanco = repository.findById(idAvaliacao).get();
        avaliacaoBanco.setComentario(avaliacao.getComentario());
        avaliacaoBanco.setIdUsuario(avaliacao.getIdUsuario());
        avaliacaoBanco.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
        return this.repository.save(avaliacaoBanco);

    }

}
