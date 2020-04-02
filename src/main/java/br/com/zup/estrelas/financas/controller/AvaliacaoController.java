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
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.dto.AvaliacaoDto;
import br.com.zup.estrelas.financas.dto.CriaAvaliacaoDto;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.exceptions.AvaliacaoRegraDeNegocioExeption;
import br.com.zup.estrelas.financas.service.AvaliacaoService;

@RestController
public class AvaliacaoController {

    @Autowired
    AvaliacaoService service;

    @PostMapping(path = "/usuarios/{idUsuario}/avaliacoes")
    public Avaliacao insereAvaliacao(@PathVariable Long idUsuario,
            @RequestBody CriaAvaliacaoDto avaliacao) throws AvaliacaoRegraDeNegocioExeption {
        return this.service.insereAvaliacao(avaliacao, idUsuario);

    }

    @GetMapping(path = "/usuarios/{idUsuario}/avaliacoes",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public AvaliacaoDto buscaAvaliacaoPorIdUsuario(@PathVariable Long idUsuario) {
        return this.service.buscaAvaliacaoPorIdUsuario(idUsuario);

    }

    @GetMapping(path = "/usuarios/{idUsuario}/avaliacoes/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AvaliacaoDto buscaAvaliacao(@PathVariable Long idAvaliacao) {
        return this.service.buscaAvaliacao(idAvaliacao);
    }

    @GetMapping(path = "/usuarios/avaliacoes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<AvaliacaoDto> buscaAvaliacoes() {
        return this.service.buscaAvaliacoes();

    }

    @DeleteMapping(path = "/usuarios/{idUsuario}/avaliacoes/{idAvaliacao}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaAvaliacao(@PathVariable Long idAvaliacao, @PathVariable Long idUsuario)
            throws AvaliacaoRegraDeNegocioExeption {
        this.service.deletaAvaliacao(idAvaliacao, idUsuario);
    }

    @PutMapping(path = "/avaliacoes/{idAvaliacao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Avaliacao alteraAvaliacao(@PathVariable Long idAvaliacao,
            @RequestBody CriaAvaliacaoDto avaliacao) throws AvaliacaoRegraDeNegocioExeption {
        return this.service.alteraAvaliacao(idAvaliacao, avaliacao);

    }

}
