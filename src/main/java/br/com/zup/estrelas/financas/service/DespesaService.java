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

    private static final int PRIMEIRO_DIA_DO_MES = 1;
    private static final String ESTE_TIPO_DE_DESPESA_JÁ_EXISTE =
            "Este tipo de Despesa já existe. Para inseri-la mude o tipo para 'OUTRO'.";
    private static final String DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA =
            "Despesa não corresponde ao usuario inserido, ou despesa ja foi deletada. Por favor inserir um usuario valido ou insira outra despesa.";

    @Autowired
    DespesaRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public Despesa insereDespesa(CriaDespesaDTO criaDespesaDto, Long idUsuario)
            throws DespesaOuUsuarioNullException {

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        for (Despesa despesaUsuario : usuario.getDespesas()) {
            despesaUsuario.getTipoDeDespesa();
            if (criaDespesaDto.getTipoDespesa().equals(despesaUsuario.getTipoDeDespesa())
                    && !(criaDespesaDto.getTipoDespesa().equals(TipoDespesa.OUTRO))) {
                throw new DespesaOuUsuarioNullException(ESTE_TIPO_DE_DESPESA_JÁ_EXISTE);
            }
        }

        return this.repository.save(Despesa.fromCriacaoDto(criaDespesaDto, idUsuario));
    }

    public DespesaDTO buscaDespesa(Long idUsuario, Long idDespesa) throws DespesaOuUsuarioNullException {

        Despesa despesa = repository.findByIdUsuarioAndIdDespesa(idUsuario, idDespesa)
                .orElseThrow(() -> new DespesaOuUsuarioNullException(
                        DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA));

        return DespesaDTO.fromDespesa(despesa);
    }

    public List<DespesaDTO> listaDespesas(Long idUsuario) {

        List<Despesa> listaDespesa = repository.findAllByIdUsuario(idUsuario);
        List<DespesaDTO> listaDespesaDto = new ArrayList<DespesaDTO>();

        for (Despesa despesa : listaDespesa) {
            listaDespesaDto.add(DespesaDTO.fromDespesa(despesa));
        }
        return listaDespesaDto;
    }

    public void deletaDespesa(Long idUsuario, Long idDespesa) throws DespesaOuUsuarioNullException {

        repository.findByIdUsuarioAndIdDespesa(idUsuario, idDespesa)
                .orElseThrow(() -> new DespesaOuUsuarioNullException(
                        DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA));
        this.repository.deleteById(idDespesa);
    }

    public Despesa atualizaDespesa(Long idUsuario, Long idDespesa,
            AtualizaDespesaDto atualizaDespesaDto) throws DespesaOuUsuarioNullException {

        Despesa despesaDoBanco = repository.findByIdUsuarioAndIdDespesa(idUsuario, idDespesa)
                .orElseThrow(() -> new DespesaOuUsuarioNullException(
                        DESPESA_NÃO_CORRESPONDE_AO_USUARIO_INSERIDO_OU_DESPESA_JA_FOI_DELETADA));

        return this.repository.save(Despesa.fromAtualizaDespesa(atualizaDespesaDto,
                despesaDoBanco.getTipoDeDespesa(), idUsuario));
    }

    public List<Despesa> despesasAVencerNoMes(Long idUsuario) {


        YearMonth month = YearMonth.now();
        LocalDate inicioMes = month.atDay(PRIMEIRO_DIA_DO_MES);
        LocalDate fimMes = month.atEndOfMonth();

        return this.repository.findAllByIdUsuarioAndVencimentoBetween(idUsuario, inicioMes, fimMes);
    }

    public List<DespesaDTO> buscaPorTipoDespesa(Long idUsuario, Optional<TipoDespesa> tipoDeDespesa) {
        
        if(tipoDeDespesa.isPresent()) {
            List<Despesa> listaDespesa = repository.findAllByIdUsuarioAndTipoDeDespesa(idUsuario, tipoDeDespesa);
            List<DespesaDTO> listaDespesaDtoFiltrada = new ArrayList<DespesaDTO>();

            for (Despesa despesa : listaDespesa) {
                listaDespesaDtoFiltrada.add(DespesaDTO.fromDespesa(despesa));
            }
            return listaDespesaDtoFiltrada;
        }
        
        List<Despesa> listaDeDespesa = this.repository.findAll();
        List<DespesaDTO> listaDespesaDto = new ArrayList<DespesaDTO>();

        for (Despesa despesa : listaDeDespesa) {
            listaDespesaDto.add(DespesaDTO.fromDespesa(despesa));
        }
        return listaDespesaDto;
    }
}
