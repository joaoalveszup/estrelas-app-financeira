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

        List<Investimento> listaInvestimentos = new ArrayList<Investimento>();

        LocalDate dataAtual = LocalDate.now();
        for (int i = 1; i <= objetivoRecebido.getNumeroInvestimentos(); i++) {
            Investimento investimento = new Investimento();
            investimento.setValor(gerarValorASerPago(objetivoRecebido, objetivoAtual));
            investimento.setDataVencimento(dataAtual.plusMonths(i));
            listaInvestimentos.add(investimento);
        }

        return listaInvestimentos;
    }

    public float gerarValorASerPago(Objetivo objetivoRecebido, Objetivo objetivoAtual) {

        float valortotalASerPago = objetivoRecebido.getValorTotal();
        List<Investimento> investimentos;
        try {
            investimentos = objetivoAtual.getInvestimentos();
            float valorCadaInvestimento = investimentos.get(0).getValor();
            long quantidadeParcelasPagas = investimentoRepository.countByPagoAndIdObjetivo(true,
                    objetivoRecebido.getIdObjetivo());
            valortotalASerPago -= (valorCadaInvestimento * quantidadeParcelasPagas);


        } catch (Exception e) {
            investimentos = null;
        }

        float valorParcela = valortotalASerPago / objetivoRecebido.getNumeroInvestimentos(); // anterior
        return valorParcela;
    }
}
