package br.com.zup.estrelas.financas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.repository.ObjetivoRepository;

@RestController
@RequestMapping("/objetivo")
public class ObjetivoController {

    @Autowired
    ObjetivoRepository objetivoRepository;

    @PostMapping
    public Objetivo insere(@RequestBody Objetivo objetivo) {
        return this.objetivoRepository.save(objetivo);
    }

    @GetMapping(path = "/{idObjetivo}")
    public Objetivo buscaObjetivo(@PathVariable Long idObjetivo) {
        return this.objetivoRepository.findById(idObjetivo).get();
    }
    
    @GetMapping
    public List<Objetivo> buscaObjetivo() {
        return this.objetivoRepository.findAll();
    }

    @PutMapping("/{idObjetivo}")
    public Objetivo atualizarObjetivo(@PathVariable(value = "idObjetivo") Long idObjetivo, @RequestBody Objetivo objetivo) {
        Objetivo objetivoAtualizado = objetivoRepository.findById(idObjetivo).get();

        objetivoAtualizado.setNome(objetivo.getNome());

        return this.objetivoRepository.save(objetivoAtualizado);
    }
    
    @DeleteMapping("/{idObjetivo}")
    public void deletaObjetivo(@PathVariable Long idObjetivo) {
        this.objetivoRepository.deleteById(idObjetivo);
    }

}
