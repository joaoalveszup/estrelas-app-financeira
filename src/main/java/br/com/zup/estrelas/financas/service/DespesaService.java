package br.com.zup.estrelas.financas.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.AtualizaDespesaDto;
import br.com.zup.estrelas.financas.dto.CriaDespesaDTO;
import br.com.zup.estrelas.financas.dto.DespesaDTO;
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.enums.TipoDespesa;
import br.com.zup.estrelas.financas.exceptions.DespesaOuUsuarioNullException;
import br.com.zup.estrelas.financas.repository.DespesaRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;


@Service
public class DespesaService {

    private static final String MENSAGEM_NUMERO_INVALIDO =
            "Por favor, inserir número válido de recorrências, ou digite uma despesa diferete do tipo 'OUTRO'.";
    private static final int PRIMEIRO_DIA_DO_MES = 1;
    private static final String ESTE_TIPO_DE_DESPESA_JÁ_EXISTE =
            "Este tipo de Despesa já existe. Para inseri-la mude o tipo para 'OUTRO'.";
    private static final String DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA =
            "Despesa não corresponde ao usuario inserido, ou despesa ja foi deletada. Por favor inserir um usuario valido ou insira outra despesa.";

    @Autowired
    DespesaRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public List<DespesaDTO> insereDespesa(CriaDespesaDTO criaDespesaDto, Long idUsuario)
            throws DespesaOuUsuarioNullException {

        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        List<Despesa> despesas = new ArrayList<Despesa>();

        verificaTipoDeDespesa(criaDespesaDto, usuario);
        despesas = manipulaDespesas(criaDespesaDto, idUsuario, despesas);
        return criaListaDespesaDTO(this.repository.saveAll(despesas));
    }

    public DespesaDTO buscaDespesa(Long idUsuario, Long idDespesa)
            throws DespesaOuUsuarioNullException {

        Despesa despesa = repository.findByIdUsuarioAndIdDespesa(idUsuario, idDespesa)
                .orElseThrow(() -> new DespesaOuUsuarioNullException(
                        DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA));

        return DespesaDTO.fromDespesa(despesa);
    }

    public void deletaDespesa(Long idUsuario, Long idDespesa) throws DespesaOuUsuarioNullException {


        Despesa despesaDoBanco = repository.findByIdUsuarioAndIdDespesa(idUsuario, idDespesa)
                .orElseThrow(() -> new DespesaOuUsuarioNullException(
                        DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA));

        if (despesaDoBanco.isRecorrente()) {
            this.repository.deleteInBatch(
                    repository.findAllByIdUsuarioAndTipoDeDespesaAndVencimentoIsAfter(idUsuario,
                            despesaDoBanco.getTipoDeDespesa(), despesaDoBanco.getVencimento()));
        } else {

            this.repository.delete(despesaDoBanco);

        }


    }

    public Despesa atualizaDespesa(Long idUsuario, Long idDespesa,
            AtualizaDespesaDto atualizaDespesaDto) throws DespesaOuUsuarioNullException {

        Despesa despesaDoBanco = repository.findByIdUsuarioAndIdDespesa(idUsuario, idDespesa)
                .orElseThrow(() -> new DespesaOuUsuarioNullException(
                        DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA));

        if (despesaDoBanco.isRecorrente()) {
            atualizaSubsequentes(atualizaDespesaDto, idUsuario, despesaDoBanco.getTipoDeDespesa(),
                    despesaDoBanco.getVencimento());
        }

        despesaDoBanco.setValor(atualizaDespesaDto.getValor());
        return this.repository.save(despesaDoBanco);
    }

    public List<Despesa> despesasAVencerNoMes(Long idUsuario) {


        YearMonth month = YearMonth.now();
        LocalDate inicioMes = month.atDay(PRIMEIRO_DIA_DO_MES);
        LocalDate fimMes = month.atEndOfMonth();

        return this.repository.findAllByIdUsuarioAndVencimentoBetween(idUsuario, inicioMes, fimMes);
    }

    public List<DespesaDTO> listarDespesas(Long idUsuario, Optional<TipoDespesa> tipoDeDespesa) {

        if (tipoDeDespesa.isPresent()) {
            List<Despesa> listaDespesa =
                    repository.findAllByIdUsuarioAndTipoDeDespesa(idUsuario, tipoDeDespesa);

            return criaListaDespesaDTO(listaDespesa);
        }

        List<Despesa> listaDeDespesa = this.repository.findAllByIdUsuario(idUsuario);
        return criaListaDespesaDTO(listaDeDespesa);
    }

    private List<DespesaDTO> criaListaDespesaDTO(List<Despesa> listaDespesa) {
        List<DespesaDTO> listaDespesaDtoFiltrada = new ArrayList<DespesaDTO>();

        for (Despesa despesa : listaDespesa) {
            listaDespesaDtoFiltrada.add(DespesaDTO.fromDespesa(despesa));
        }
        return listaDespesaDtoFiltrada;
    }

    private void verificaTipoDeDespesa(CriaDespesaDTO criaDespesaDto, Usuario usuario)
            throws DespesaOuUsuarioNullException {
        for (Despesa despesaUsuario : usuario.getDespesas()) {
            despesaUsuario.getTipoDeDespesa();

            if (criaDespesaDto.getTipoDespesa().equals(despesaUsuario.getTipoDeDespesa())
                    && !(criaDespesaDto.getTipoDespesa().equals(TipoDespesa.OUTRO))) {
                throw new DespesaOuUsuarioNullException(ESTE_TIPO_DE_DESPESA_JÁ_EXISTE);
            }
        }
    }

    private List<Despesa> manipulaDespesas(CriaDespesaDTO criaDespesaDto, Long idUsuario,
            List<Despesa> despesas) throws DespesaOuUsuarioNullException {
        if (criaDespesaDto.isRecorrente()) {
            if (criaDespesaDto.getNumeroRecorrencias() <= 0
                    || criaDespesaDto.getTipoDespesa().equals(TipoDespesa.OUTRO)) {
                throw new DespesaOuUsuarioNullException(MENSAGEM_NUMERO_INVALIDO);
            }
            LocalDate data = LocalDate.now();
            for (int i = 0; i < criaDespesaDto.getNumeroRecorrencias(); i++) {

                Despesa despesa = Despesa.fromCriacaoDto(criaDespesaDto, idUsuario);
                despesa.setVencimento(data.plusMonths(i));
                despesas.add(despesa);
            }
        } else {
            despesas.add(Despesa.fromCriacaoDto(criaDespesaDto, idUsuario));
        }

        return despesas;
    }

    private void atualizaSubsequentes(AtualizaDespesaDto atualizaDespesaDto, Long idUsuario,
            TipoDespesa tipoDeDespesa, LocalDate vencimento) {

        List<Despesa> despesasSubsequentes =
                this.repository.findAllByIdUsuarioAndTipoDeDespesaAndVencimentoIsAfter(idUsuario,
                        tipoDeDespesa, vencimento);

        if (despesasSubsequentes.isEmpty()) {
            return;
        }

        for (Despesa despesa : despesasSubsequentes) {

            despesa.setValor(atualizaDespesaDto.getValor());
        }

        repository.saveAll(despesasSubsequentes);
    }
}

