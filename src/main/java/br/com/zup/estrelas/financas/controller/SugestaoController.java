package br.com.zup.estrelas.financas.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.dto.MensagemDto;
import br.com.zup.estrelas.financas.dto.SugestaoRequestDto;
import br.com.zup.estrelas.financas.dto.SugestaoResponseDto;
import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.enums.TipoSugestao;
import br.com.zup.estrelas.financas.exceptions.ValidaCampoECaratereException;
import br.com.zup.estrelas.financas.service.SugestaoService;


@RestController
@RequestMapping("/sugestoes")
public class SugestaoController {

    @Autowired
    SugestaoService sugestaoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SugestaoResponseDto insereSugestao(@Valid @RequestBody SugestaoRequestDto sugestaoRequestDto)
            throws ValidaCampoECaratereException {
        return this.sugestaoService.insereSugestao(sugestaoRequestDto);
    }

    @GetMapping(path = "/{idSugestao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sugestao buscaSugestao(@PathVariable Long idSugestao) {
        return this.sugestaoService.buscaSugestao(idSugestao);
    }

    @DeleteMapping(path = "/{idSugestao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDto deleteSugestao(@PathVariable Long idSugestao) {
        return this.sugestaoService.deleteSugestao(idSugestao);
    }

    @PutMapping(path = "/{idSugestao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SugestaoResponseDto alteraSugestao(@PathVariable Long idSugestao,
            @RequestBody SugestaoRequestDto sugestaoRequestDto)
            throws ValidaCampoECaratereException {
        return this.sugestaoService.alteraSugestao(idSugestao, sugestaoRequestDto);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SugestaoResponseDto> listaSugestao(
            @RequestParam(value = "tipo-sugestao") Optional<TipoSugestao> tipoSugestao) {
        return sugestaoService.listaSugestao(tipoSugestao);
    }
}
