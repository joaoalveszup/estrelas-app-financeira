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
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.service.DespesaService;


@RestController
@RequestMapping("/usuarios/{idUsuario}/despesas")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @PostMapping
    public Despesa insereDespesa(@RequestBody Despesa despesa) {
        return this.despesaService.insereDespesa(despesa);
    }

    @GetMapping(path = "/{idDespesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Despesa buscaDespesa(@PathVariable Long idDespesa) {
        return despesaService.buscaDespesa(idDespesa);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Despesa> listaDespesas() {
        return (List<Despesa>) despesaService.listaDespesas();
    }

    @DeleteMapping(path = "/{idDespesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaDespesa(@PathVariable Long idDespesa) {
        this.despesaService.deletaDespesa(idDespesa);
    }

    @PutMapping(path = "/{idDespesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Despesa atualizaDespesa(@PathVariable Long idDespesa, @RequestBody Despesa despesa) {

        return this.despesaService.atualizaDespesa(idDespesa, despesa);

    }
}
