package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.repository.ObjetivoRepository;

@Service
public class ObjetivoService {

    @Autowired
    ObjetivoRepository objetivoRepository;
    @Autowired
    InvestimentoService investimentoService;

    public Objetivo insereObjetivo(Objetivo objetivoRecebido) {

        Objetivo objetivoAtual;
        try {
            objetivoAtual = objetivoRepository.findById(objetivoRecebido.getIdObjetivo()).get();
        } catch (Exception e) {
            objetivoAtual = null;
        }

        objetivoRecebido.setInvestimentos(
                investimentoService.criaInvestimentos(objetivoRecebido, objetivoAtual));// em teste
        return this.objetivoRepository.save(objetivoRecebido);
    }

    public Objetivo buscaObjetivo(Long idObjetivo) {
        return this.objetivoRepository.findById(idObjetivo).get();
    }

    public List<Objetivo> listaObjetivos() {
        return this.objetivoRepository.findAll();
    }

    public Objetivo atualizarObjetivo(Long idObjetivo, Objetivo objetivo) {
        Objetivo objetivoAtual = objetivoRepository.findById(idObjetivo).get();

        objetivoAtual.setNome(objetivo.getNome());
        if (garanteNumeroInvestimentoValido(objetivo)) {
            objetivoAtual.setNumeroInvestimentos(objetivo.getNumeroInvestimentos());
        }

        objetivoAtual.setValorTotal(objetivo.getValorTotal());
        objetivoAtual
                .setInvestimentos(investimentoService.criaInvestimentos(objetivo, objetivoAtual));

        return this.objetivoRepository.save(objetivoAtual);
    }

    public void deletaObjetivo(@PathVariable Long idObjetivo) {
        this.objetivoRepository.deleteById(idObjetivo);
    }


    private boolean garanteNumeroInvestimentoValido(Objetivo objetivo) {
        if (objetivo.getNumeroInvestimentos() > 0) {
            return true;
        }
        return false;
    }
}
