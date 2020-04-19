package br.com.zup.estrelas.financas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.AvaliacaoDto;
import br.com.zup.estrelas.financas.dto.CriaAvaliacaoDto;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.exceptions.AvaliacaoRegraDeNegocioExeption;
import br.com.zup.estrelas.financas.repository.AvaliacaoRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;


@Service
public class AvaliacaoService {

    private static final String ERRO_AO_INSERRIR_UMA_NOTA_PARA_O_FILTRO =
            "Erro ao buscar a nota! Você digitou uma nota menor que 0 ou maior que 5! Por favor, insira uma nota válida.";
    private static final String ERRO_ID_INCORRETO =
            "Erro! O id-usuário ou id-avaliação está incorreto! Por favor, insira o id correspondente.";
    private static final String MSG_ERRO_CARACTERE_MAX_OU_NOTA_INVALIDA =
            "Erro! Você excedeu o máximo de caractere no comentário ou inseriu uma nota inválida!"
            + " Por favor, insira um comentário ou nota válido ";
    private static final String MSG_ERRO_AVALIACAO_JA_EXISTE_OU_USUARIO_NULL =
            "Erro! Já existe uma avaliação para este usuário ou o ele não existe. Por favor, insira um usuário válido.";
    private static final int MAX_CARACTERE = 400;
    private static final int NOTA_MIN = 0;
    private static final int NOTA_MAX = 5;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Avaliacao insereAvaliacao(CriaAvaliacaoDto criaAvaliacaoDto, Long idUsuario)
            throws AvaliacaoRegraDeNegocioExeption {

        if (this.verificaSeAvaliacaoExiste(criaAvaliacaoDto, idUsuario)) {
            throw new AvaliacaoRegraDeNegocioExeption(MSG_ERRO_AVALIACAO_JA_EXISTE_OU_USUARIO_NULL);

        }

        if (!this.validaNotaEVerificaMaxCaractere(criaAvaliacaoDto)) {
            throw new AvaliacaoRegraDeNegocioExeption(MSG_ERRO_CARACTERE_MAX_OU_NOTA_INVALIDA);
        }

        return avaliacaoRepository.save(Avaliacao.fromCriacaoDto(criaAvaliacaoDto, idUsuario));

    }


    public List<AvaliacaoDto> buscaAvaliacoes(Optional<Integer> notaAvaliacao)
            throws AvaliacaoRegraDeNegocioExeption {
        if (notaAvaliacao.isPresent()) {
            Integer valorNotaAvaliacao = notaAvaliacao.get();
            if (valorNotaAvaliacao < NOTA_MIN || valorNotaAvaliacao > NOTA_MAX) {
                throw new AvaliacaoRegraDeNegocioExeption(ERRO_AO_INSERRIR_UMA_NOTA_PARA_O_FILTRO);
            }
            List<Avaliacao> listaAvaliacao =
                    this.avaliacaoRepository.findAllByNotaAvaliacao(valorNotaAvaliacao);
            return criaListaAvaliacao(listaAvaliacao);
        }

        List<Avaliacao> listaDeAvaliacao = avaliacaoRepository.findAll();
        return criaListaAvaliacao(listaDeAvaliacao);

    }

    public AvaliacaoDto buscaAvaliacaoPorIdUsuario(Long idUsuario) {
        Avaliacao avaliacaoBuscada = avaliacaoRepository.findFirstByIdUsuario(idUsuario);
        return AvaliacaoDto.fromAvaliacao(avaliacaoBuscada);
    }


    public void deletaAvaliacao(Long idAvaliacao, Long idUsuario)
            throws AvaliacaoRegraDeNegocioExeption {
        avaliacaoRepository.findByIdUsuarioAndIdAvaliacao(idUsuario, idAvaliacao)
                .orElseThrow(() -> new AvaliacaoRegraDeNegocioExeption(ERRO_ID_INCORRETO));
        this.avaliacaoRepository.deleteById(idAvaliacao);

    }

    public Avaliacao alteraAvaliacao(Long idAvaliacao, CriaAvaliacaoDto criaAvaliacaoDto,
            Long idUsuario) throws AvaliacaoRegraDeNegocioExeption {
        Avaliacao avaliacaoBuscada =
                avaliacaoRepository.findByIdUsuarioAndIdAvaliacao(idUsuario, idAvaliacao)
                        .orElseThrow(() -> new AvaliacaoRegraDeNegocioExeption(ERRO_ID_INCORRETO));
        if (!this.validaNotaEVerificaMaxCaractere(criaAvaliacaoDto)) {
            throw new AvaliacaoRegraDeNegocioExeption(MSG_ERRO_CARACTERE_MAX_OU_NOTA_INVALIDA);
        }

        avaliacaoBuscada.setComentario(criaAvaliacaoDto.getComentario());
        avaliacaoBuscada.setNotaAvaliacao(criaAvaliacaoDto.getNotaAvaliacao());
        return avaliacaoRepository.save(avaliacaoBuscada);
    }


    private boolean validaNotaEVerificaMaxCaractere(CriaAvaliacaoDto avaliacao) {

        if (avaliacao.getNotaAvaliacao() < NOTA_MIN || avaliacao.getNotaAvaliacao() > NOTA_MAX) {
            return false;
        }
        if (avaliacao.getComentario().length() > MAX_CARACTERE) {

            return false;
        }
        return true;
    }

    public boolean verificaSeAvaliacaoExiste(CriaAvaliacaoDto avaliacao, Long idUsuario) {
        Usuario usuarioBuscado = usuarioRepository.findById(idUsuario).get();
        if (usuarioBuscado == null) {
            return true;
        }

        Avaliacao avaliacaoBuscada = avaliacaoRepository.findByUsuario(usuarioBuscado);
        if (avaliacaoBuscada != null) {
            return true;
        }
        return false;
    }

    public List<AvaliacaoDto> criaListaAvaliacao(List<Avaliacao> listadeAvaliacao) {
        List<AvaliacaoDto> listadeAvaliacaoDto = new ArrayList<AvaliacaoDto>();
        for (Avaliacao avaliacao : listadeAvaliacao) {
            listadeAvaliacaoDto.add(AvaliacaoDto.fromAvaliacao(avaliacao));

        }

        return listadeAvaliacaoDto;
    }
}


