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
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.service.DependenteService;

@RestController
@RequestMapping ("/usuarios/{idUsuario}/dependentes")
public class DependenteController {

    @Autowired
    DependenteService dependenteService;

    @PostMapping
    public Dependente insereDependente(@RequestBody Dependente dependente) {
        return this.dependenteService.insereDependente(dependente);
    }

    @GetMapping(path = "{idDependente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Dependente buscaDependente(@PathVariable Long idDependente) {
        return this.dependenteService.buscaDependente(idDependente);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Dependente> buscaDependentes() {
        return this.dependenteService.buscaDependentes();
    }

    @PutMapping(path = "{idDependente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Dependente modificaDependente(@PathVariable Long idDependente,
            @RequestBody Dependente dependente) {

        return this.dependenteService.modificaDependente(idDependente, dependente);
    }

    @DeleteMapping(path = "{idDependente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaDependente(@PathVariable Long idDependente) {
        this.dependenteService.deletaDependente(idDependente);
    }

}


