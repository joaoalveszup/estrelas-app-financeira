package br.com.zup.estrelas.financas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.repository.InvestimentoRepository;

@Service
public class InvestimentoService {

    @Autowired
    InvestimentoRepository investimentoRepository;

    // método provavelmente não será mais utilizado
    public Investimento insereInvestimento(Investimento investimento) {
        return this.investimentoRepository.save(investimento);
    }

    public Investimento buscaInvestimento(Long idInvestimento) {
        return this.investimentoRepository.findById(idInvestimento).get();
    }

    public List<Investimento> buscaInvestimentos() {
        return this.investimentoRepository.findAll();
    }

    // método provavelmente não será mais utilizado
    public Investimento atualizaInvestimento(Long idInvestimento, Investimento investimento) {
        Investimento investimentoAtual = investimentoRepository.findById(idInvestimento).get();
        investimentoAtual.setDataVencimento(investimentoAtual.getDataVencimento());
        investimentoAtual.setValor(investimentoAtual.getValor());
        return this.investimentoRepository.save(investimentoAtual);
    }

    // método provavelmente não será mais utilizado
    public void deleteInvestimento(Long idInvestimento) {
        investimentoRepository.deleteById(idInvestimento);
    }

    public List<Investimento> criaInvestimentos(Objetivo objetivoRecebido, Objetivo objetivoAtual) {

        Investimento investimentoAtual = new Investimento();
        float valorParcela = gerarValorASerPago(objetivoRecebido, objetivoAtual)
                / objetivoRecebido.getNumeroInvestimento(); // anterior
        investimentoAtual.setValor(valorParcela);

        List<Investimento> listaInvestimentos = new ArrayList<Investimento>();

        for (int i = 1; i <= objetivoRecebido.getNumeroInvestimento(); i++) {
            investimentoAtual.setDataVencimento(LocalDate.now().plusMonths(i));
            listaInvestimentos.add(investimentoAtual);
        }
        return listaInvestimentos;
    }

    public float gerarValorASerPago(Objetivo objetivo, Objetivo objetivoAtual) {

        float valorASerPago = objetivo.getValorTotal();
        List<Investimento> investimentos = objetivo.getInvestimentos();
        if (investimentos.get(1) == null) {
            valorASerPago = objetivo.getValorTotal() - (investimentos.get(1).getValor()
                    * investimentoRepository.countByPagoAndIdObjetivo(investimentos.get(1).isPago(),
                            objetivo.getIdObjetivo()));
        }

        return valorASerPago;
    }
}
