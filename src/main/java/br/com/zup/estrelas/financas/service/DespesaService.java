package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.repository.DespesaRepository;


@Service
public class DespesaService {

    @Autowired
    DespesaRepository repository;

    public Despesa insereDespesa(Despesa despesa) {


        return repository.save(despesa);
    }

    public Despesa buscaDespesa(Long idDespesa) {

        return repository.findById(idDespesa).get();
    }

    public List<Despesa> listaDespesas() {

        return (List<Despesa>) this.repository.findAll();
    }

    public void deletaDespesa(Long idDespesa) {

        this.repository.deleteById(idDespesa);
    }

    public Despesa atualizaDespesa(Long idDespesa, Despesa despesa) {

        Despesa despesaBanco = repository.findById(idDespesa).get();

        despesaBanco.setTipoDeDespesa(despesa.getTipoDeDespesa());
        despesaBanco.setValor(despesa.getValor());
        despesaBanco.setVencimento(despesa.getVencimento());

        return this.repository.save(despesaBanco);
    }
}
