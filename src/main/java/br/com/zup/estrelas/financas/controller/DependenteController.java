package br.com.zup.estrelas.financas.controller;

import java.util.List;
import java.util.Optional;
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
import br.com.zup.estrelas.financas.repository.DependenteRepository;


@RestController
@RequestMapping("/dependentes")
public class DependenteController {
    
    @Autowired
    DependenteRepository repository;
    
    @PostMapping
    public List<Dependente> insereDependente(@RequestBody List<Dependente> dependentes) {
        return (List<Dependente>) this.repository.saveAll(dependentes);
    }
    
    @GetMapping(path = "/{id_dependente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Optional<Dependente> buscaDependente(@PathVariable Long idDependente) {
        return repository.findById(idDependente);
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Dependente> buscaDependentes() {
        return (List<Dependente>) repository.findAll();
    }

    @PutMapping(path = "/{id_dependente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Dependente modificaDependente(@PathVariable Long idDependente, @RequestBody Dependente dependente) {

        Dependente dependenteBanco = repository.findById(idDependente).get();

        dependenteBanco.setNome(dependente.getNome());
        dependente.setParentesco(dependente.getParentesco());
        dependenteBanco.setRenda(dependente.getRenda());

        return this.repository.save(dependenteBanco);
    }
    
    @DeleteMapping(path = "/{id_dependente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaDependente(@PathVariable Long idDependente) {
        this.repository.deleteById(idDependente);
    }

}
