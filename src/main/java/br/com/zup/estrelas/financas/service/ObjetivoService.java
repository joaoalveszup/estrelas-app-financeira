package br.com.zup.estrelas.financas.service;

import br.com.zup.estrelas.financas.dto.MensagemDto;
import br.com.zup.estrelas.financas.dto.ObjetivoDto;
import br.com.zup.estrelas.financas.dto.SomaObjetivosMesDto;
import br.com.zup.estrelas.financas.entity.Investimento;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.exceptions.UsuarioOuObjetivoNuloException;
import br.com.zup.estrelas.financas.repository.ObjetivoRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetivoService {

    private static final String USUÁRIO_OU_OJETIVO_NÃO_CORRESPONDEM =
            "Erro! Usuário ou objetivo não correspondem. Por favor, insira um usuário ou objetivo válido";
    
    @Autowired
    ObjetivoRepository objetivoRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    InvestimentoService investimentoService;

    public Objetivo insereObjetivo(Long idUsuario, ObjetivoDto objetivoDtoRecebido) {

        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        Objetivo objetivoRecebido = Objetivo.fromDto(usuario, objetivoDtoRecebido);
        objetivoRecebido.setIdUsuario(usuario.getIdUsuario());
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

    public ObjetivoDto buscaObjetivo(Long idUsuario, Long idObjetivo)
            throws UsuarioOuObjetivoNuloException {
        Objetivo objetivo =
                this.objetivoRepository.findByIdUsuarioAndIdObjetivo(idUsuario, idObjetivo)
                        .orElseThrow(() -> new UsuarioOuObjetivoNuloException(
                                USUÁRIO_OU_OJETIVO_NÃO_CORRESPONDEM));

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

        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        Objetivo objetivo = Objetivo.fromDto(usuario, objetivoDto);
        objetivo.setIdUsuario(idUsuario);
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

    public MensagemDto deletaObjetivo(Long idUsuario, Long idObjetivo)
            throws UsuarioOuObjetivoNuloException {

        this.objetivoRepository.findByIdUsuarioAndIdObjetivo(idUsuario, idObjetivo).orElseThrow(
                () -> new UsuarioOuObjetivoNuloException(USUÁRIO_OU_OJETIVO_NÃO_CORRESPONDEM));
        this.objetivoRepository.deleteById(idObjetivo);

        return new MensagemDto("Objetivo deletado com sucesso!");
    }

    public SomaObjetivosMesDto recuperaValorMensalObjetivos(Long idUsuario) {
        List<Long> objetivosDoUsuario = this.objetivoRepository.findAllIdsFromUser(idUsuario);

        return new SomaObjetivosMesDto(this.investimentoService.retornaValoresInvestimentosDoMesCorrente(objetivosDoUsuario));
    }

}
