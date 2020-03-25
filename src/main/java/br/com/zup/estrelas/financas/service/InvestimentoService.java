package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.repository.InvestimentoRepository;

@Service
public class InvestimentoService {

    @Autowired
    InvestimentoRepository investimentoRepository;

    public Investimento insereInvestimento(Investimento investimento) {
        return this.investimentoRepository.save(investimento);
    }

    public Investimento buscaInvestimento(Long idInvestimento) {
        return this.investimentoRepository.findById(idInvestimento).get();
    }

    public List<Investimento> buscaInvestimentos() {
        return this.investimentoRepository.findAll();
    }

    public Investimento atualizaInvestimento(Long idInvestimento, Investimento investimento) {
        Investimento investimentoAtual = investimentoRepository.findById(idInvestimento).get();
        investimentoAtual.setDataVencimento(investimentoAtual.getDataVencimento());
        investimentoAtual.setValor(investimentoAtual.getValor());
        return this.investimentoRepository.save(investimentoAtual);
    }

    public void deleteInvestimento(Long idInvestimento) {
        investimentoRepository.deleteById(idInvestimento);
    }
}