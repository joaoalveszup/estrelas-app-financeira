package br.com.zup.estrelas.financas.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.dto.CriaDependenteDto;
import br.com.zup.estrelas.financas.dto.DependenteDto;
import br.com.zup.estrelas.financas.dto.MensagemDto;
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.enums.Parentesco;
import br.com.zup.estrelas.financas.exceptions.DependenteException;
import br.com.zup.estrelas.financas.service.DependenteService;

@RestController

public class DependenteController {

    @Autowired
    DependenteService dependenteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/usuarios/{idUsuario}/dependentes")
    public Dependente insereDependente(@RequestBody CriaDependenteDto criaDependenteDto,
            @PathVariable Long idUsuario) throws DependenteException {
        return this.dependenteService.insereDependente(criaDependenteDto, idUsuario);
    }

    @PutMapping(path = "/usuarios/{idUsuario}/dependentes/{idDependente}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Dependente modificaDependente(@RequestBody DependenteDto dependenteDto,
            @PathVariable Long idUsuario, @PathVariable Long idDependente) {
        return this.dependenteService.modificaDependente(dependenteDto, idUsuario, idDependente);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/dependentes/{idDependente}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public DependenteDto buscaDependentePorId(@PathVariable Long idUsuario,
            @PathVariable Long idDependente) throws DependenteException {
        return this.dependenteService.buscaDependentePorId(idUsuario, idDependente);
    }

    @GetMapping(path = "/usuarios/{idUsuario}/dependentes",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<DependenteDto> buscaListaDeDependentes(@PathVariable Long idUsuario,
            @RequestParam Optional<Parentesco> parentesco) {
        return this.dependenteService.buscaListaDeDependentes(idUsuario, parentesco);
    }

    @DeleteMapping(path = "/usuarios/{idUsuario}/dependentes/{idDependente}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public MensagemDto deletaDependente(@PathVariable Long idUsuario, @PathVariable Long idDependente)
            throws DependenteException {
        return this.dependenteService.deletaDependente(idUsuario, idDependente);
    }

}
