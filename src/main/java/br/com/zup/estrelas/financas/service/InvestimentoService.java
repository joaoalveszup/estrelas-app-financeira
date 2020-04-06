package br.com.zup.estrelas.financas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.exceptions.UsuarioOuObjetivoNuloException;
import br.com.zup.estrelas.financas.repository.InvestimentoRepository;
import br.com.zup.estrelas.financas.repository.ObjetivoRepository;

@Service
public class InvestimentoService {

    private static final int QUANTIDADE_INVESTIMENTOS_PAGOS = 0;

    private static final String USUÁRIO_OU_OJETIVO_NÃO_CORRESPONDEM =
            "Usuário ou ojetivo não correspondem.";

    @Autowired
    InvestimentoRepository investimentoRepository;

    @Autowired
    ObjetivoRepository objetivoRepository;

    public List<Investimento> criaInvestimentos(Objetivo objetivoRecebido, Objetivo objetivoAtual) {

        List<Investimento> listaInvestimentos = new ArrayList<Investimento>();

        float valorASerPago = gerarValorInvestimentoASerPago(objetivoRecebido, objetivoAtual);
        long quantidadeInvestimentosPagos;

        if (objetivoAtual != null) {
            quantidadeInvestimentosPagos = investimentoRepository.countByPagoAndIdObjetivo(true,
                    objetivoAtual.getIdObjetivo());
        } else {
            quantidadeInvestimentosPagos = QUANTIDADE_INVESTIMENTOS_PAGOS;
        }

        LocalDate dataAtual = LocalDate.now();

        if (quantidadeInvestimentosPagos > QUANTIDADE_INVESTIMENTOS_PAGOS) {
            List<Investimento> listaInvesimentoPago = investimentoRepository
                    .findByPagoAndIdObjetivo(true, objetivoAtual.getIdObjetivo());
            listaInvestimentos =
                    criaListaInvestimentoPagos(listaInvestimentos, listaInvesimentoPago);
        }

        return  criaNovosInvestimentos(objetivoRecebido, listaInvestimentos,
                valorASerPago, dataAtual);
    }

    public List<Investimento> alteraStatusParcela(Long idUsuario, Long idObjetivo,
            Long idInvestimento, boolean statusParcela) throws UsuarioOuObjetivoNuloException {

        this.objetivoRepository.findByIdUsuarioAndIdObjetivo(idUsuario, idObjetivo).orElseThrow(
                () -> new UsuarioOuObjetivoNuloException(USUÁRIO_OU_OJETIVO_NÃO_CORRESPONDEM));

        Investimento investimento = investimentoRepository
                .findByIdInvestimentoAndIdObjetivo(idInvestimento, idObjetivo);
        investimento.setPago(statusParcela);
        investimentoRepository.save(investimento);
        return investimentoRepository.findAllByIdObjetivo(idObjetivo);
    }

    private List<Investimento> criaNovosInvestimentos(Objetivo objetivoRecebido,
            List<Investimento> listaInvestimentos, float valorASerPago, LocalDate dataAtual) {
        for (int i = 1; i <= objetivoRecebido.getNumeroInvestimentos(); i++) {
            Investimento investimento = new Investimento();
            investimento.setValor(valorASerPago);
            investimento.setDataVencimento(dataAtual.plusMonths(i));
            listaInvestimentos.add(investimento);
        }
        return listaInvestimentos;
    }

    private List<Investimento> criaListaInvestimentoPagos(List<Investimento> listaInvestimentos,
            List<Investimento> listaInvesimentoPago) {
        for (Investimento investimentoPago : listaInvesimentoPago) {
            Investimento investimentoAnterior = new Investimento();
            investimentoAnterior.setValor(investimentoPago.getValor());
            investimentoAnterior.setDataVencimento(investimentoPago.getDataVencimento());
            investimentoAnterior.setPago(true);
            listaInvestimentos.add(investimentoAnterior);

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

}
