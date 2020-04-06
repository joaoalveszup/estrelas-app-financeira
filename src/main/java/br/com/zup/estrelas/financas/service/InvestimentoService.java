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

    public List<Investimento> criaInvestimentos(Objetivo objetivoRecebido, Objetivo objetivoAtual) {

        List<Investimento> listaInvestimentos = new ArrayList<Investimento>();

        float valorASerPago = gerarValorInvestimentoASerPago(objetivoRecebido, objetivoAtual);
        long quantidadeInvestimentosPagos;
        if (objetivoAtual != null) {
            quantidadeInvestimentosPagos = investimentoRepository.countByPagoAndIdObjetivo(true,
                    objetivoAtual.getIdObjetivo());
        } else {
            quantidadeInvestimentosPagos = 0;
        }
        LocalDate dataAtual = LocalDate.now();

        if (quantidadeInvestimentosPagos > 0) {
            List<Investimento> listaInvesimentoPago = investimentoRepository
                    .findByPagoAndIdObjetivo(true, objetivoAtual.getIdObjetivo());
            for (Investimento investimentoPago : listaInvesimentoPago) {
                Investimento investimentoAnterior = new Investimento();
                investimentoAnterior.setValor(investimentoPago.getValor());
                investimentoAnterior.setDataVencimento(investimentoPago.getDataVencimento());
                investimentoAnterior.setPago(true);
                listaInvestimentos.add(investimentoAnterior);
            }
        }

        for (int i = 1; i <= objetivoRecebido.getNumeroInvestimentos(); i++) {
            Investimento investimento = new Investimento();
            investimento.setValor(valorASerPago);
            investimento.setDataVencimento(dataAtual.plusMonths(i));
            listaInvestimentos.add(investimento);
        }

        return listaInvestimentos;
    }

    private float gerarValorInvestimentoASerPago(Objetivo objetivoRecebido,
            Objetivo objetivoAtual) {

        float valorTotalASerPago = objetivoRecebido.getValorTotal();
        if (objetivoAtual != null) {
            float valorCadaInvestimento = objetivoAtual.getInvestimentos().get(0).getValor();
            valorTotalASerPago -=
                    geraInvestimentosPagos(objetivoRecebido, objetivoAtual, valorCadaInvestimento);
        }

        float valorInvestimento = valorTotalASerPago / objetivoRecebido.getNumeroInvestimentos();
        return valorInvestimento;
    }

    private float geraInvestimentosPagos(Objetivo objetivoRecebido, Objetivo objetivoAtual,
            float valorCadaInvestimento) {

        float valorInvestimentosPago = valorCadaInvestimento;

        if (objetivoAtual == null) {
            valorInvestimentosPago = 0;
        } else {
            long quantidadeInvestimentosPagos = investimentoRepository
                    .countByPagoAndIdObjetivo(true, objetivoAtual.getIdObjetivo());
            valorInvestimentosPago = valorCadaInvestimento * quantidadeInvestimentosPagos;
        }

        return valorInvestimentosPago;
    }

    public List<Investimento> alteraStatusParcela(Long idInvestimento, Long idObjetivo,
            boolean statusParcela) {

        Investimento investimento = investimentoRepository
                .findByIdInvestimentoAndIdObjetivo(idInvestimento, idObjetivo);
        investimento.setPago(statusParcela);
        investimentoRepository.save(investimento);
        return investimentoRepository.findAllByIdObjetivo(idObjetivo);
    }
}
