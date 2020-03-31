package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Investimento;
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
                investimentoService.criaInvestimentos(objetivoRecebido, objetivoAtual));
        return this.objetivoRepository.save(objetivoRecebido);
    }

    public Objetivo buscaObjetivo(Long idObjetivo) {
        return this.objetivoRepository.findById(idObjetivo).get();
    }

    public List<Objetivo> listaObjetivos() {
        return this.objetivoRepository.findAll();
    }

    public Objetivo atualizarObjetivo(Long idObjetivo, Objetivo objetivo) {

        if (!(objetivo.getNumeroInvestimentos() > 0)) {
            return null;
        }
        Objetivo objetivoSalvoBanco = objetivoRepository.findById(idObjetivo).get();
        List<Investimento> listaInvestimentos =
                investimentoService.criaInvestimentos(objetivo, objetivoSalvoBanco);

        objetivoSalvoBanco.setNome(objetivo.getNome());
        objetivoSalvoBanco.setNumeroInvestimentos(objetivo.getNumeroInvestimentos());

        objetivoSalvoBanco.setValorTotal(objetivo.getValorTotal());
        objetivoSalvoBanco.setInvestimentos(listaInvestimentos);

        return this.objetivoRepository.save(objetivoSalvoBanco);
    }

    public void deletaObjetivo(Long idObjetivo) {
        this.objetivoRepository.deleteById(idObjetivo);
    }

}
