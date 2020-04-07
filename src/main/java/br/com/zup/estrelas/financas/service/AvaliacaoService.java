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

    private static final String ERRO_AO_INSERRIR_UMA_NOTA_DE_BUSCA =
            "OCORREU ERRO AO BUSCAR A NOTA!, VOCÊ DIGITOU UMA NOTA MENOR QUE 0 OU MAIOR QUE 5!";
    private static final String ERRO_ID_INCORRETO =
            "ERRO!  O ID-USUÁRIO OU ID-AVALIAÇÃO ESTÁ INCORRETO!";
    private static final String MSG_ERRO_CARACTERE_MAX_OU_NOTA_INVALIADA =
            "OPS! OCORREU UM ERRO! VOCÊ EXCEDEU O MÁXIMO DE"
                    + " CARACTERE NO COMENTARIO, OU INSERIU UMA NOTA INVALIDA!";
    private static final String MSG_ERRO_AVALIACAO_EXISTENTE =
            "OCORREU UM ERRO JÁ EXISTE UMA AVALIAÇÃO PARA ESTE USUARIO, OU O USUÁRIO NÃO EXISTE";
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
            throw new AvaliacaoRegraDeNegocioExeption(MSG_ERRO_AVALIACAO_EXISTENTE);

        }

        if (!this.validaNotaVerificaMaxCaractere(criaAvaliacaoDto)) {
            throw new AvaliacaoRegraDeNegocioExeption(MSG_ERRO_CARACTERE_MAX_OU_NOTA_INVALIADA);
        }

        return avaliacaoRepository.save(Avaliacao.fromCriacaoDto(criaAvaliacaoDto, idUsuario));

    }


    public List<AvaliacaoDto> buscaAvaliacoes(Optional<Integer> notaAvaliacao)
            throws AvaliacaoRegraDeNegocioExeption {
        if (notaAvaliacao.isPresent()) {
            Integer valorNotaAvaliacao = notaAvaliacao.get();
            if (valorNotaAvaliacao < NOTA_MIN || valorNotaAvaliacao > NOTA_MAX) {
                throw new AvaliacaoRegraDeNegocioExeption(ERRO_AO_INSERRIR_UMA_NOTA_DE_BUSCA);
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
        Avaliacao avaliacaoBanco =
                avaliacaoRepository.findByIdUsuarioAndIdAvaliacao(idUsuario, idAvaliacao)
                        .orElseThrow(() -> new AvaliacaoRegraDeNegocioExeption(ERRO_ID_INCORRETO));
        if (!this.validaNotaVerificaMaxCaractere(criaAvaliacaoDto)) {
            throw new AvaliacaoRegraDeNegocioExeption(MSG_ERRO_CARACTERE_MAX_OU_NOTA_INVALIADA);
        }

        avaliacaoBanco.setComentario(criaAvaliacaoDto.getComentario());
        avaliacaoBanco.setNotaAvaliacao(criaAvaliacaoDto.getNotaAvaliacao());
        return avaliacaoRepository.save(avaliacaoBanco);
    }


    private boolean validaNotaVerificaMaxCaractere(CriaAvaliacaoDto avaliacao) {

        if (avaliacao.getNotaAvaliacao() < NOTA_MIN || avaliacao.getNotaAvaliacao() > NOTA_MAX) {
            return false;
        }
        if (avaliacao.getComentario().length() > MAX_CARACTERE) {

            return false;
        }
        return true;
    }

    public boolean verificaSeAvaliacaoExiste(CriaAvaliacaoDto avaliacao, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        if (usuario == null) {
            return true;
        }

        Avaliacao avaliacaoBuscada = avaliacaoRepository.findByUsuario(usuario);
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


