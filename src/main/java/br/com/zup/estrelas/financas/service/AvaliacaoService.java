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
        AvaliacaoDto avaliacaoDto = new AvaliacaoDto();
        avaliacaoDto.setIdAvaliacao(avaliacao.getIdAvaliacao());
        avaliacaoDto.setComentario(avaliacao.getComentario());
        avaliacaoDto.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
        avaliacaoDto.setNomeUsuario(avaliacao.getUsuario().getNome());
        return avaliacaoDto;

    }

    public AvaliacaoDto buscaAvaliacaoPorIdUsuario(Long idUsuario, CriaAvaliacaoDto avaliacao) {
        Avaliacao avaliacaoBuscada = avaliacaoRepository.findFirstByIdUsuario(idUsuario);
        AvaliacaoDto avaliacaoDto = new AvaliacaoDto();
        avaliacaoDto.setIdAvaliacao(avaliacaoBuscada.getIdAvaliacao());
        avaliacaoDto.setComentario(avaliacaoBuscada.getComentario());
        avaliacaoDto.setNotaAvaliacao(avaliacaoBuscada.getNotaAvaliacao());
        avaliacaoDto.setNomeUsuario(avaliacaoBuscada.getUsuario().getNome());
        return avaliacaoDto;

    }

    public List<AvaliacaoDto> buscaAvaliacoes() {
        List<Avaliacao> listaDeAvaliacao = avaliacaoRepository.findAll();
        List<AvaliacaoDto> listadeAvaliacaoDto = new ArrayList<AvaliacaoDto>();
        for (Avaliacao avaliacao : listaDeAvaliacao) {
            AvaliacaoDto avaliacaoDto = new AvaliacaoDto();
            avaliacaoDto.setIdAvaliacao(avaliacao.getIdAvaliacao());
            avaliacaoDto.setComentario(avaliacao.getComentario());
            avaliacaoDto.setNomeUsuario(avaliacaoDto.getNomeUsuario());
            avaliacaoDto.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
            listadeAvaliacaoDto.add(avaliacaoDto);
        }
        return listadeAvaliacaoDto;


    }

    public void deletaAvaliacao(Long idAvaliacao) {
        this.avaliacaoRepository.deleteById(idAvaliacao);

    }

    public Avaliacao alteraAvaliacao(Long idAvaliacao, CriaAvaliacaoDto avaliacao) {
        if (this.validaAvaliacao(avaliacao)) {
            Avaliacao avaliacaoBanco = avaliacaoRepository.findById(idAvaliacao).get();
            avaliacaoBanco.setComentario(avaliacao.getComentario());
            avaliacaoBanco.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
            return avaliacaoRepository.save(avaliacaoBanco);

        }
        return null;
    }

    public Avaliacao insereAvaliacao(CriaAvaliacaoDto avaliacao) {
        Usuario usuario = usuarioRepository.findById(avaliacao.getIdUsuario()).get();
        if (this.checaExistenciaAvaliacao(avaliacao)) {
            return null;
        }

        if (this.validaAvaliacao(avaliacao)) {
            Avaliacao avaliacaoPersistida = new Avaliacao();
            avaliacaoPersistida.setComentario(avaliacao.getComentario());
            avaliacaoPersistida.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
            avaliacaoPersistida.setIdUsuario(usuario.getIdUsuario());

            return avaliacaoRepository.save(avaliacaoPersistida);
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

    public boolean checaExistenciaAvaliacao(CriaAvaliacaoDto avaliacao) {
        Usuario usuario = usuarioRepository.findById(avaliacao.getIdUsuario()).get();
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


