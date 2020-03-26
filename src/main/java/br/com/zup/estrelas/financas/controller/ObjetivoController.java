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
import br.com.zup.estrelas.financas.service.ObjetivoService;

@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {

    @Autowired
    ObjetivoService objetivoService;

    @PostMapping
    public Objetivo insereObjetivo(@RequestBody Objetivo objetivo) {
        return this.objetivoService.insereObjetivo(objetivo);
    }

    @GetMapping(path = "/{idObjetivo}")
    public Objetivo buscaObjetivo(@PathVariable Long idObjetivo) {
        return buscaObjetivo(idObjetivo);
    }

    @GetMapping
    public List<Objetivo> listaObjetivos() {
        return objetivoService.listaObjetivos();
    }

    @PutMapping("/{idObjetivo}")
    public Objetivo atualizaObjetivo(@PathVariable(value = "idObjetivo") Long idObjetivo,
            @RequestBody Objetivo objetivo) {
        return this.objetivoService.atualizarObjetivo(idObjetivo, objetivo);
    }

    @DeleteMapping("/{idObjetivo}")
    public void deletaObjetivo(@PathVariable Long idObjetivo) {
        this.objetivoService.deletaObjetivo(idObjetivo);
    }

}
