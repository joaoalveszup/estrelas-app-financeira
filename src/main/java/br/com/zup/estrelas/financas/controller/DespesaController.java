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
import br.com.zup.estrelas.financas.entity.DespesaEntity;
import br.com.zup.estrelas.financas.repository.DespesaRepository;


@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    DespesaRepository repository;

    @PostMapping
    public DespesaEntity insereDespesa(@RequestBody DespesaEntity despesa) {
        return this.repository.save(despesa);
    }

    @GetMapping(path = "/{id_despesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DespesaEntity buscaPorDespesa(@PathVariable Long idDespesa) {
        return repository.findById(idDespesa).get();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<DespesaEntity> buscaDespesas() {
        return (List<DespesaEntity>) repository.findAll();
    }

    @DeleteMapping(path = "/{id_despesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void deletaDespesa(@PathVariable Long idDespesa) {
        this.repository.deleteById(idDespesa);
    }

    @PutMapping(path = "/{id_despesa}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DespesaEntity atualizaDespesa(@PathVariable Long idDespesa,
            @RequestBody DespesaEntity despesa) {

        DespesaEntity despesaBanco = repository.findById(idDespesa).get();

        despesaBanco.setTipoDeDespesa(despesa.getTipoDeDespesa());
        despesaBanco.setValor(despesa.getValor());
        despesaBanco.setVencimento(despesa.getVencimento());

        return this.repository.save(despesaBanco);
    }
}
