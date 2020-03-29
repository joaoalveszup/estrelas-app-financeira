package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Avaliacao buscaAvaliacao(Long idAvaliacao) {
        return avaliacaoRepository.findById(idAvaliacao).get();
    }

    public List<Avaliacao> buscaAvaliacoes() {
        return (List<Avaliacao>) avaliacaoRepository.findAll();
    }

    public void deletaAvaliacao(Long idAvaliacao) {
        this.avaliacaoRepository.deleteById(idAvaliacao);
    }

    public Avaliacao alteraAvaliacao(Long idAvaliacao, Avaliacao avaliacao) {
        if (this.validaAvaliacao(avaliacao)) {
            Avaliacao avaliacaoBanco = avaliacaoRepository.findById(idAvaliacao).get();
            avaliacaoBanco.setComentario(avaliacao.getComentario());
            avaliacaoBanco.setIdUsuario(avaliacao.getIdUsuario());
            avaliacaoBanco.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
            return avaliacaoRepository.save(avaliacaoBanco);

        }
        return null;
    }

    public Avaliacao insereAvaliacao(Avaliacao avaliacao) {
        Usuario usuario = usuarioRepository.findById(avaliacao.getIdUsuario()).get();
        if(this.checaExistenciaAvaliacao(avaliacao)) {
            return null;
        }
        
        if (this.validaAvaliacao(avaliacao)) {
            avaliacao.setUsuario(usuario);
            usuario.setAvaliacao(avaliacao);
            return avaliacaoRepository.save(avaliacao);
        }

        return null;

    }

    private boolean validaAvaliacao(Avaliacao avaliacao) {
        if (avaliacao.getNotaAvaliacao() < NOTA_MIN || avaliacao.getNotaAvaliacao() > NOTA_MAX) {
            return false;
        }
        if (avaliacao.getComentario().length() > MAX_CARACTERE) {

            return false;
        }
        return true;
    }
    public boolean checaExistenciaAvaliacao(Avaliacao avaliacao) {
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


