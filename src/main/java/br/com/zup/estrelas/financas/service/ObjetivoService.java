package br.com.zup.estrelas.financas.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.ObjetivoDto;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.repository.ObjetivoRepository;

@Service
public class ObjetivoService {

    @Autowired
    ObjetivoRepository objetivoRepository;
    @Autowired
    InvestimentoService investimentoService;

    public Objetivo insereObjetivo(Long idUsuario, ObjetivoDto objetivoDtoRecebido) {

        Objetivo objetivoRecebido = Objetivo.fromObjetivo(idUsuario, objetivoDtoRecebido);
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

    public ObjetivoDto buscaObjetivo(Long idUsuario, Long idObjetivo) {
        Objetivo objetivo =
                this.objetivoRepository.findByIdUsuarioAndIdObjetivo(idUsuario, idObjetivo).get();

        return ObjetivoDto.fromEntity(objetivo);
    }

    public List<ObjetivoDto> listaObjetivos(Long idUsuario) {
        List<Objetivo> listaObjetivo = this.objetivoRepository.findAllByIdUsuario(idUsuario);
        List<ObjetivoDto> listaObjetivoDto = new ArrayList<ObjetivoDto>();

        for (Objetivo objetivo : listaObjetivo) {
            listaObjetivoDto.add(ObjetivoDto.fromEntity(objetivo));
        }
        return listaObjetivoDto;
    }

    public Objetivo atualizarObjetivo(Long idUsuario, Long idObjetivo, ObjetivoDto objetivoDto) {

        Objetivo objetivo = Objetivo.fromObjetivo(idUsuario, objetivoDto);
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

    public void deletaObjetivo(Long idUsuario, Long idObjetivo) {
        try {
            if(this.objetivoRepository.findByIdUsuarioAndIdObjetivo(idUsuario, idObjetivo).orElseThrow() != null) {
                this.objetivoRepository.deleteById(idObjetivo);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
