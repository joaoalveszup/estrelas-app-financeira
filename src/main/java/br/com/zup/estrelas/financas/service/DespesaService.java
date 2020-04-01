package br.com.zup.estrelas.financas.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.CriaDespesaDTO;
import br.com.zup.estrelas.financas.dto.DespesaDTO;
import br.com.zup.estrelas.financas.entity.Despesa;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.enums.TipoDespesa;
import br.com.zup.estrelas.financas.repository.DespesaRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;


@Service
public class DespesaService {

    @Autowired
    DespesaRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;


    public Despesa insereDespesa(CriaDespesaDTO criaDespesaDto, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        for (Despesa despesaUsuario : usuario.getDespesas()) {
            despesaUsuario.getTipoDeDespesa();
            if (criaDespesaDto.getTipoDespesa().equals(despesaUsuario.getTipoDeDespesa())
                    && !(criaDespesaDto.getTipoDespesa().equals(TipoDespesa.OUTRO))) {
                return null;
            }
        }
        return this.repository.save(Despesa.fromCriacaoDto(criaDespesaDto, idUsuario));
    }

    public DespesaDTO buscaDespesa(Long idUsuario, Long idDespesa) {
        Despesa despesa = repository.findByIdUsuarioAndIdDespesa(idUsuario, idDespesa);

        return DespesaDTO.fromDespesa(despesa);
    }

    public List<DespesaDTO> listaDespesas(Long idUsuario) {
        
        List<Despesa> listaDespesa = repository.findAllByIdUsuario(idUsuario);
        List<DespesaDTO> listaDespesaDto = new ArrayList<DespesaDTO>();
        
        for(Despesa despesa : listaDespesa) {
           listaDespesaDto.add(DespesaDTO.fromDespesa(despesa));
        }
        return listaDespesaDto; 
    }

    public void deletaDespesa(Long idDespesa) {

        this.repository.deleteById(idDespesa);
    }

    public Despesa atualizaDespesa(Long idDespesa, DespesaDTO despesaDto) {

        Despesa despesaBanco = repository.findById(idDespesa).get();

        // TODO: tartar setValor e setVencimento.
        despesaBanco.setValor(despesaDto.getValor());
        despesaBanco.setVencimento(despesaDto.getVencimento());

        return this.repository.save(despesaBanco);
    }

    public List<Despesa> despesasAVencer(Long idUsuario) {


        YearMonth month = YearMonth.now();
        LocalDate inicioData = month.atDay(1);
        LocalDate fimData = month.atEndOfMonth();

        return this.repository.findAllByIdUsuarioAndVencimentoBetween(idUsuario, inicioData,
                fimData);
    }
}
