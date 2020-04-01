package br.com.zup.estrelas.financas.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.AvaliacaoDto;
import br.com.zup.estrelas.financas.dto.CriaAvaliacaoDto;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.repository.AvaliacaoRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;


@Service
public class AvaliacaoService {

    private static final int MAX_CARACTERE = 400;
    private static final int NOTA_MIN = 0;
    private static final int NOTA_MAX = 5;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public AvaliacaoDto buscaAvaliacao(Long idAvaliacao) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao).get();
        return AvaliacaoDto.fromAvaliacao(avaliacao);

    }

    public AvaliacaoDto buscaAvaliacaoPorIdUsuario(Long idUsuario) {
        Avaliacao avaliacaoBuscada = avaliacaoRepository.findFirstByIdUsuario(idUsuario);
        return AvaliacaoDto.fromAvaliacao(avaliacaoBuscada);



    }

    public List<AvaliacaoDto> buscaAvaliacoes() {
        List<Avaliacao> listaDeAvaliacao = avaliacaoRepository.findAll();
        List<AvaliacaoDto> listadeAvaliacaoDto = new ArrayList<AvaliacaoDto>();
        for (Avaliacao avaliacao : listaDeAvaliacao) {
            listadeAvaliacaoDto.add(AvaliacaoDto.fromAvaliacao(avaliacao));
        }
        return listadeAvaliacaoDto;


    }

    public void deletaAvaliacao(Long idAvaliacao) {
        this.avaliacaoRepository.deleteById(idAvaliacao);

    }

    public Avaliacao alteraAvaliacao(Long idAvaliacao, CriaAvaliacaoDto criaAvaliacaoDto) {
        if (this.validaAvaliacao(criaAvaliacaoDto)) {
            Avaliacao avaliacaoBanco = avaliacaoRepository.findById(idAvaliacao).get();
            avaliacaoBanco.setComentario(criaAvaliacaoDto.getComentario());
            avaliacaoBanco.setNotaAvaliacao(criaAvaliacaoDto.getNotaAvaliacao());
            return avaliacaoRepository.save(avaliacaoBanco);

        }
        return null;
    }

    public Avaliacao insereAvaliacao(CriaAvaliacaoDto criaAvaliacaoDto, Long idUsuario) {

        if (this.checaExistenciaAvaliacao(criaAvaliacaoDto, idUsuario)) {
            return null;
        }

        if (this.validaAvaliacao(criaAvaliacaoDto)) {
            return avaliacaoRepository.save(Avaliacao.fromCriacaoDto(criaAvaliacaoDto, idUsuario));
        }

        return null;

    }

    private boolean validaAvaliacao(CriaAvaliacaoDto avaliacao) {

        if (avaliacao.getNotaAvaliacao() < NOTA_MIN || avaliacao.getNotaAvaliacao() > NOTA_MAX) {
            return false;
        }
        if (avaliacao.getComentario().length() > MAX_CARACTERE) {

            return false;
        }
        return true;
    }

    public boolean checaExistenciaAvaliacao(CriaAvaliacaoDto avaliacao, Long idUsuario) {
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
}


